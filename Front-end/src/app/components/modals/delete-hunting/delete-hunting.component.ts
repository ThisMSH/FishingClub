import { Component, Input, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgToastService } from 'ng-angular-popup';
import { concatMap, from, take } from 'rxjs';
import { HuntingService } from 'src/app/services/hunting/hunting.service';

@Component({
    selector: 'app-delete-hunting',
    templateUrl: './delete-hunting.component.html',
    styleUrls: ['./delete-hunting.component.css'],
})
export class DeleteHuntingComponent implements OnInit {
    @Input() id!: number;
    @Input() numberOfFish!: number;
    private formBuilder = inject(FormBuilder);
    private huntingService = inject(HuntingService);
    private toast = inject(NgToastService);
    isLoading: boolean = false;

    huntingForm: FormGroup = this.formBuilder.group({
        id: ['', Validators.required],
        numOfFish: ['', Validators.required],
    });

    huntingErrors = {
        numOfFish: {
            required: 'Amount of fish is required.',
            max: 'The number of fish cannot exceed the hunted fish.',
        },
    };

    onSubmit(): void {
        this.isLoading = true;

        if (this.huntingForm.valid) {
            const deletedFishArr: number[] = Array.from(
                { length: this.huntingForm.value.numOfFish },
                () => this.id
            );
            let count: number = 0;

            from(deletedFishArr)
                .pipe(concatMap((id) => this.huntingService.deleteHunting(id)))
                .subscribe({
                    next: () => {
                        count++;
                    },
                    error: (err) => {
                        console.log(err);
                        this.isLoading = false;
                    },
                    complete: () => {
                        this.isLoading = false;

                        this.toast.success({
                            detail: 'Success',
                            summary: `${count} out of ${this.huntingForm.value.numOfFish} hunted fish have been deleted.`,
                            duration: 6000,
                        });
                    },
                });
        } else {
            this.isLoading = false;

            this.toast.warning({
                detail: 'Invalid date',
                summary: 'Please fill all the required fields.',
                duration: 5000,
            });
        }
    }

    ngOnInit(): void {
        this.huntingForm.controls['id'].setValue(this.id);
        this.huntingForm.controls['numOfFish'].addValidators([
            Validators.max(this.numberOfFish),
        ]);
    }
}
