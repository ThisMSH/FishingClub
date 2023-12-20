import { Component, Input, OnInit, ViewChild, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgToastService } from 'ng-angular-popup';
import { take } from 'rxjs';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';
import { MemberResponse } from 'src/app/models/member/member-response';
import { MemberService } from 'src/app/services/member/member.service';
import { RankingService } from 'src/app/services/ranking/ranking.service';
import { ModalContainerComponent } from '../modal-container/modal-container.component';
import { RankingResponse } from 'src/app/models/ranking/ranking-response';

@Component({
    selector: 'app-create-ranking',
    templateUrl: './create-ranking.component.html',
    styleUrls: ['./create-ranking.component.css'],
})
export class CreateRankingComponent implements OnInit {
    @ViewChild(ModalContainerComponent) modalContainer!: ModalContainerComponent;
    @Input() code: string | undefined;
    private rankingService = inject(RankingService);
    private memberService = inject(MemberService);
    private formBuilder = inject(FormBuilder);
    toast = inject(NgToastService);
    competitions!: CompetitionResponse[];
    members!: MemberResponse[];
    isLoading: boolean = false;
    memberOptions: Record<string, number> = {};

    rankingForm: FormGroup = this.formBuilder.group({
        memberNumber: ['', Validators.required],
        competitionCode: '',
    });

    rankingErrors = {
        memberNumber: { required: 'You must select a member' },
    };

    onSubmit(): void {
        this.isLoading = true;

        if (this.rankingForm.valid) {
            this.rankingService
                .createRanking(this.rankingForm.value)
                .pipe(take(1))
                .subscribe({
                    next: (r) => {
                        console.log(r);
                        this.modalContainer.closeModal();

                        this.toast.success({
                            detail: 'Participant added',
                            summary: `${
                                (r.data as RankingResponse).member.name
                            } ${
                                (r.data as RankingResponse).member.familyName
                            } has been assigned to this competition successfully.`,
                            duration: 6000,
                        });
                    },
                    error: (err) => {
                        this.isLoading = false;
                        console.log(err);

                        this.toast.error({
                            detail: 'Error occurred',
                            summary: err.error.message,
                            duration: 6000,
                        });
                    },
                    complete: () => {
                        this.isLoading = false;
                    },
                });
        } else {
            this.isLoading = false;

            this.toast.warning({
                detail: 'Invalid data',
                summary: 'Please fill all the required fields.',
                duration: 5000,
            });
        }
    }

    getMembers(): void {
        this.memberService
            .getMembers(999, 'name', 'ASC')
            .pipe(take(1))
            .subscribe({
                next: (m) => {
                    this.members = m.data.content as MemberResponse[];
                    console.log(this.members);
                },
                error: (err) => {
                    this.toast.error({
                        detail: 'Error occurred while fetching members',
                        summary: err.error.message,
                        duration: 6000,
                    });
                },
                complete: () => {
                    this.members.forEach((member: MemberResponse) => {
                        this.memberOptions[
                            `${member.name} ${member.familyName}`
                        ] = member.number as number;
                    });

                    console.log(this.memberOptions);
                },
            });
    }

    ngOnInit(): void {
        this.getMembers();
        this.rankingForm.controls['competitionCode'].setValue(this.code);
    }
}
