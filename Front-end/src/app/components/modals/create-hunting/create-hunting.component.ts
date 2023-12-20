import { Component, Input, OnInit, ViewChild, inject } from '@angular/core';
import { ModalContainerComponent } from '../modal-container/modal-container.component';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HuntingService } from 'src/app/services/hunting/hunting.service';
import { NgToastService } from 'ng-angular-popup';
import { MemberService } from 'src/app/services/member/member.service';
import { take } from 'rxjs';
import { MemberResponse } from 'src/app/models/member/member-response';
import { FishService } from 'src/app/services/fish/fish.service';
import { FishResponse } from 'src/app/models/fish/fish-response';

@Component({
    selector: 'app-create-hunting',
    templateUrl: './create-hunting.component.html',
    styleUrls: ['./create-hunting.component.css'],
})
export class CreateHuntingComponent implements OnInit {
    @ViewChild(ModalContainerComponent)
    modalContainer!: ModalContainerComponent;
    @Input() competitionCode!: string;
    private formBuilder = inject(FormBuilder);
    private huntingService = inject(HuntingService);
    private memberService = inject(MemberService);
    private fishService = inject(FishService);
    toast = inject(NgToastService);
    members!: MemberResponse[];
    fishes!: FishResponse[];
    memberOptions: Record<string, number> = {};
    fishOptions: Record<string, number> = {};
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

    onSubmit(): void {
        if (this.huntingForm.valid) {
            console.log(this.huntingForm.value);
        } else {
            this.toast.warning({
                detail: 'Invalid data',
                summary: 'Please fill all the required fields.',
                duration: 5000,
            });
        }
    }

    ngOnInit(): void {
        (this.huntingForm.controls['huntingGroup'] as FormArray)
            .at(0)
            .patchValue({ competitionCode: this.competitionCode });
        this.getMembers();
    }
}
