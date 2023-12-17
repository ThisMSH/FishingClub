import { Component, OnInit, inject } from '@angular/core';
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
    competitions!: PaginationResponse<CompetitionResponse>;
    competitionsArray!: CompetitionResponse[];
    competitionParams!: PaginationParams;
    isLoading: boolean = true;
    search!: string;

    searchFn(): void {
        this.isLoading = true;

        if (this.search) {
            this.competitionService
                .getCompetition(this.search)
                .pipe(take(1))
                .subscribe({
                    next: (c) => {
                        this.competitionsArray = [c.data as CompetitionResponse];
                    },
                    error: (err) => {
                        if (err.error.status === 404) {
                            this.competitionsArray = [];
                        }

                        console.log(err);
                        this.isLoading = false;
                    },
                    complete: () => {
                        this.isLoading = false;
                    }
                })
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
    }

    goToPage(evt: any): void {
        console.log(evt.p);
        this.competitionParams = { ...this.competitionParams, page: evt.p };
        console.log(this.competitionParams);
        this.getAllCompetitions(this.competitionParams);
    }

    getAllCompetitions(params: PaginationParams): void {
        this.isLoading = true;
        const { size, sortBy, sortOrder, filter, page } = params;
        let p;

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
                    console.log(err);
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
