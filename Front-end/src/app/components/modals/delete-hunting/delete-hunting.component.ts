import {
    Component,
    EventEmitter,
    Input,
    OnInit,
    Output,
    ViewChild,
    inject,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgToastService } from 'ng-angular-popup';
import { concatMap, from, take } from 'rxjs';
import { HuntingService } from 'src/app/services/hunting/hunting.service';
import { CompetitionDetailsComponent } from 'src/app/views/dashboard/competitions/competition-details/competition-details.component';
import { ModalContainerComponent } from '../modal-container/modal-container.component';

@Component({
    selector: 'app-delete-hunting',
    templateUrl: './delete-hunting.component.html',
    styleUrls: ['./delete-hunting.component.css'],
})
export class DeleteHuntingComponent implements OnInit {
    @ViewChild(ModalContainerComponent) modalContainer!: ModalContainerComponent;
    @Input() id!: number;
    @Input() idx!: number;
    @Input() numberOfFish!: number;
    @Output() updateHuntedFish = new EventEmitter();
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
                        const newFishCount: number = this.numberOfFish - count;

                        this.isLoading = false;
                        this.modalContainer.closeModal();
                        this.toast.success({
                            detail: 'Success',
                            summary: `${count} out of ${this.huntingForm.value.numOfFish} hunted fish have been deleted.`,
                            duration: 6000,
                        });
                        this.updateHuntedFish.emit({
                            idx: this.idx,
                            numOfFish: newFishCount,
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
