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
    competitionParams!: PaginationParams;
    isLoading: boolean = true;

    initParams(): void {
        const storedParams: string | null =
            localStorage.getItem('competitionParams');

        if (!storedParams) {
            const params: PaginationParams = {
                size: 24,
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

    getAllCompetitions(params: PaginationParams): void {
        this.isLoading = true;
        const { size, sortBy, sortOrder, filter } = params;

        this.competitionService
            .getCompetitions(
                size as number,
                sortBy as string,
                sortOrder as string,
                filter as CompetitionFilter
            )
            .pipe(take(1))
            .subscribe({
                next: (c) => {
                    this.competitions = c;
                    console.log(c);
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
