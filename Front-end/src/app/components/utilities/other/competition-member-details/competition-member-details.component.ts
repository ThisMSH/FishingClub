import { Component, EventEmitter, Input, OnInit, Output, inject } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { take } from 'rxjs';
import { RankingRequest } from 'src/app/models/ranking/ranking-request';
import { RankingResponse } from 'src/app/models/ranking/ranking-response';
import { RankingService } from 'src/app/services/ranking/ranking.service';
import { Collapse, initTE } from 'tw-elements';

@Component({
    selector: 'app-competition-member-details',
    templateUrl: './competition-member-details.component.html',
    styleUrls: ['./competition-member-details.component.css'],
})
export class CompetitionMemberDetailsComponent implements OnInit {
    @Input() ranking!: RankingResponse;
    @Input() accordionId!: string;
    @Input() accordionTarget!: string;
    @Input() competitionCode!: string;
    @Output() participantRemoved = new EventEmitter();
    private rankingService = inject(RankingService);
    toast = inject(NgToastService);
    showDeleteModal: boolean = false;
    deleteBtnLoading: boolean = false;

    updateNumOfHunting(evt: any): void {
        if (evt.numOfFish === 0) {
            this.ranking.member.huntings.splice(evt.idx, 1);
        } else {
            this.ranking.member.huntings[evt.idx].numberOfFish = evt.numOfFish;
        }
    }

    deleteParticipantfromCompetition(): void {
        const ranking: RankingRequest = {
            memberNumber: this.ranking.member.number as number,
            competitionCode: this.competitionCode
        };

        this.deleteBtnLoading = true;
        this.rankingService
            .deleteRanking(ranking)
            .pipe(take(1))
            .subscribe({
                next: (r) => {
                    console.log(r);
                },
                error: (err) => {
                    this.deleteBtnLoading = false;
                    this.toast.error({
                        detail: 'Error occured',
                        summary: err.error.message,
                        duration: 6000
                    });
                },
                complete: () => {
                    this.deleteBtnLoading = false;
                    this.showDeleteModal = false;
                    this.participantRemoved.emit();
                    this.toast.success({
                        detail: 'Participant deleted',
                        summary: `The participant ${this.ranking.member.name} ${this.ranking.member.familyName} has been deleted from this competition.`,
                        duration: 6000
                    });
                }
            });
    }

    ngOnInit(): void {
        initTE({ Collapse }, { allowReinits: true });
    }
}
