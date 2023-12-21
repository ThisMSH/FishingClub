import { Component, EventEmitter, Input, OnInit, Output, ViewChild, inject } from '@angular/core';
import { ModalContainerComponent } from '../modal-container/modal-container.component';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HuntingService } from 'src/app/services/hunting/hunting.service';
import { NgToastService } from 'ng-angular-popup';
import { MemberService } from 'src/app/services/member/member.service';
import { concatMap, from, take } from 'rxjs';
import { MemberResponse } from 'src/app/models/member/member-response';
import { FishService } from 'src/app/services/fish/fish.service';
import { FishResponse } from 'src/app/models/fish/fish-response';
import { HuntingResponse } from 'src/app/models/hunting/hunting-response';
import { HuntingRequest } from 'src/app/models/hunting/hunting-request';

@Component({
    selector: 'app-create-hunting',
    templateUrl: './create-hunting.component.html',
    styleUrls: ['./create-hunting.component.css'],
})
export class CreateHuntingComponent implements OnInit {
    @ViewChild(ModalContainerComponent)
    modalContainer!: ModalContainerComponent;
    @Input() competitionCode: string | undefined;
    @Output() exeGetCompetition = new EventEmitter();
    private formBuilder = inject(FormBuilder);
    private huntingService = inject(HuntingService);
    private memberService = inject(MemberService);
    private fishService = inject(FishService);
    toast = inject(NgToastService);
    members!: MemberResponse[];
    fishes!: FishResponse[];
    memberOptions: Record<string, number> = {};
    fishOptions: Record<string, string> = {};
    isLoading: boolean = false;

    huntingForm: FormGroup = this.formBuilder.group({
        huntingGroup: this.formBuilder.array([
            this.formBuilder.group({
                weight: ['', Validators.required],
                memberNumber: ['', Validators.required],
                competitionCode: ['', Validators.required],
                fishName: ['', Validators.required],
            }),
        ]),
    });

    huntingErrors = {
        weight: { required: 'Weight of the fish is required.' },
        memberNumber: { required: 'You must select a member.' },
        competitionCode: { required: '' },
        fishName: { required: 'You must select a fish.' },
    };

    get getHuntingGroup(): FormArray {
        return this.huntingForm.get('huntingGroup') as FormArray;
    }

    addHuntingForm(): void {
        this.getHuntingGroup.push(
            this.formBuilder.group({
                weight: ['', Validators.required],
                memberNumber: ['', Validators.required],
                competitionCode: [this.competitionCode, Validators.required],
                fishName: ['', Validators.required],
            })
        );
    }

    removeHuntingForm(idx: number): void {
        this.getHuntingGroup.removeAt(idx);
    }

    getMembers(): void {
        this.memberService
            .getMembers(999, 'name', 'ASC')
            .pipe(take(1))
            .subscribe({
                next: (m) => {
                    this.members = m.data.content as MemberResponse[];
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
                },
            });
    }

    getFishes(): void {
        this.fishService
            .getAllFishes(999, 'name', 'ASC')
            .pipe(take(1))
            .subscribe({
                next: (f) => {
                    this.fishes = f.data.content as FishResponse[];
                },
                error: (err) => {
                    this.toast.error({
                        detail: 'Error occurred while fetching fishes',
                        summary: err.error.message,
                        duration: 6000,
                    });
                },
                complete: () => {
                    this.fishes.forEach((fish: FishResponse) => {
                        this.fishOptions[fish.name] = fish.name as string;
                    });
                },
            });
    }

    onSubmit(): void {
        if (this.huntingForm.valid) {
            const length = (this.huntingForm.get('huntingGroup') as FormArray).length;
            let count: number = 0;

            from((this.huntingForm.get('huntingGroup') as FormArray).controls)
                .pipe(concatMap(hunt => this.huntingService.createHunting(hunt.value as HuntingRequest)))
                .subscribe({
                    next: (h) => {
                        count++;
                        console.log(h);
                    },
                    error: (err) => {
                        console.log(err);

                        this.isLoading = false;
                        this.toast.error({
                            detail: 'Error occurred',
                            summary: err.error.message,
                            duration: 6000,
                        });

                        setTimeout(() => {
                            this.toast.info({
                                detail: 'Hunted fishes added',
                                summary: `${count} out of ${length} hunted fishes have been added successfully.`,
                                duration: 6000,
                            });
                        }, 6250);

                        if (count > 0) {
                            this.exeGetCompetition.emit(this.competitionCode);
                        }
                    },
                    complete: () => {
                        this.isLoading = false;
                        this.modalContainer.closeModal();
                        this.exeGetCompetition.emit(this.competitionCode);
                        this.toast.success({
                            detail: 'Hunted fishes added',
                            summary: `${count} out of ${length} hunted fishes have been added successfully.`,
                            duration: 6000,
                        });
                    },
                });
        } else {
            this.toast.warning({
                detail: 'Invalid data',
                summary: 'Please fill all the required fields.',
                duration: 5000,
            });
        }
    }

    ngOnInit(): void {
        (this.getHuntingGroup.at(0) as FormGroup)
            .get('competitionCode')
            ?.setValue(this.competitionCode);
        this.getMembers();
        this.getFishes();
    }
}
