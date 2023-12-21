import { Component, Input, OnInit, inject } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { take } from 'rxjs';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';
import { RankingResponse } from 'src/app/models/ranking/ranking-response';
import { RankingService } from 'src/app/services/ranking/ranking.service';

@Component({
    selector: 'app-competition-ranking',
    templateUrl: './competition-ranking.component.html',
    styleUrls: ['./competition-ranking.component.css'],
})
export class CompetitionRankingComponent implements OnInit {
    @Input() competition!: CompetitionResponse;
    private rankingService = inject(RankingService);
    toast = inject(NgToastService);
    rankingList: RankingResponse[] = [];

    getRankingByCompetitionCode(code: string): void {
        this.rankingService
            .getRankingByCompetition(code)
            .pipe(take(1))
            .subscribe({
                next: (r) => {
                    this.rankingList = r.data as RankingResponse[];
                    console.log(r);

                },
                error: (err) => {
                    this.toast.error({
                        detail: 'Error occurred',
                        summary: err.error.message,
                        duration: 5000,
                    });
                },
                complete: () => {
                    console.log('complete');
                },
            });
    }

    setRanks(): void {
        this.rankingService
            .setRanks(this.competition.code as string)
            .pipe(take(1))
            .subscribe({
                next: (r) => {
                    console.log(r);
                },
                error: (err) => {
                    console.log(err);
                },
                complete: () => {
                    console.log('setting ranks complete');
                    this.getRankingByCompetitionCode(
                        this.competition.code as string
                    );
                },
            });
    }

    ngOnInit(): void {
        this.getRankingByCompetitionCode(this.competition.code as string);
    }
}
