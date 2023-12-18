import { Component, OnInit, inject } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { take } from 'rxjs';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';
import { PaginationResponse } from 'src/app/models/response/pagination-response';
import { CompetitionService } from 'src/app/services/competition/competition.service';
import { CompetitionFilter, PaginationParams } from 'src/app/types/types';
import { Dropdown, Input, initTE } from 'tw-elements';

@Component({
    selector: 'app-competitions',
    templateUrl: './competitions.component.html',
    styleUrls: ['./competitions.component.css'],
})
export class CompetitionsComponent implements OnInit {
    private competitionService = inject(CompetitionService);
    private toast = inject(NgToastService);
    competitions!: PaginationResponse<CompetitionResponse>;
    competitionsArray!: CompetitionResponse[];
    competitionParams!: PaginationParams;
    search!: string;
    isLoading: boolean = true;
    showPagination: boolean = true;
    size!: number;
    sizeOptions: Record<string, number> = {
        '04': 4,
        '08': 8,
        '12': 12,
        '16': 16,
        '20': 20,
        '24': 24,
    };
    filter: CompetitionFilter[] = ['ALL', 'INCOMING', 'ONGOING', 'DONE'];
    currentFilter: CompetitionFilter = 'ALL';

    searchFn(): void {
        this.isLoading = true;

        if (this.search) {
            this.competitionService
                .getCompetition(this.search)
                .pipe(take(1))
                .subscribe({
                    next: (c) => {
                        this.competitionsArray = [
                            c.data as CompetitionResponse,
                        ];

                        this.showPagination = false;
                    },
                    error: (err) => {
                        if (err.error.status === 404) {
                            this.competitionsArray = [];
                        }

                        this.isLoading = false;
                    },
                    complete: () => {
                        this.isLoading = false;
                    },
                });
        } else {
            this.competitionsArray = [];
            this.getAllCompetitions(this.competitionParams);
        }
    }

    initParams(): void {
        const storedParams: string | null =
            localStorage.getItem('competitionParams');

        if (!storedParams) {
            const params: PaginationParams = {
                size: 12,
                sortBy: 'date',
                sortOrder: 'ASC',
                filter: 'ALL',
            };

            this.competitionParams = params;
            localStorage.setItem('competitionParams', JSON.stringify(params));
        } else {
            this.competitionParams = JSON.parse(storedParams as string);
        }

        this.size = this.competitionParams.size as number;
    }

    goToPage(evt: any): void {
        this.competitionParams = { ...this.competitionParams, page: evt.p };
        this.getAllCompetitions(this.competitionParams);
    }

    setSize(evt: any): void {
        this.competitionParams.size = evt.size;
        const { page, filter, ...params } = { ...this.competitionParams };
        const newParams = { ...params, page: 0, filter: filter };

        localStorage.setItem(
            'competitionParams',
            JSON.stringify({ ...params, filter: 'ALL' })
        );

        this.getAllCompetitions(newParams);
    }

    setFilter(evt: any): void {
        this.currentFilter = evt.item;
        this.competitionParams = {
            ...this.competitionParams,
            filter: evt.item,
            page: 0,
        };
        this.getAllCompetitions(this.competitionParams);
    }

    getAllCompetitions(params: PaginationParams): void {
        const { size, sortBy, sortOrder, filter, page } = params;
        let p;
        this.isLoading = true;
        this.showPagination = true;

        if (!page) {
            p = 0;
        } else {
            p = page;
        }

        this.competitionService
            .getCompetitions(
                size as number,
                sortBy as string,
                sortOrder as string,
                filter as CompetitionFilter,
                p as number
            )
            .pipe(take(1))
            .subscribe({
                next: (c) => {
                    this.competitions = c;
                    this.competitionsArray = c.data.content;
                },
                error: (err) => {
                    this.toast.error({
                        detail: 'Error occured',
                        summary: err.error.message,
                        duration: 6000,
                    });
                    this.isLoading = false;
                },
                complete: () => {
                    this.isLoading = false;
                },
            });
    }

    ngOnInit(): void {
        initTE({ Input, Dropdown }, { allowReinits: true });
        this.initParams();
        this.getAllCompetitions(this.competitionParams);
    }
}
