import { Component, EventEmitter, OnInit, Output, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgToastService } from 'ng-angular-popup';
import { take } from 'rxjs';
import { CompetitionRequest } from 'src/app/models/competition/competition-request';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';
import { CompetitionService } from 'src/app/services/competition/competition.service';
import { Modal, Ripple, Toast, initTE } from 'tw-elements';

@Component({
    selector: 'app-create-competition',
    templateUrl: './create-competition.component.html',
    styleUrls: ['./create-competition.component.css'],
})
export class CreateCompetitionComponent implements OnInit {
    @Output() refreshCompetitionsList = new EventEmitter();
    private competitionService = inject(CompetitionService);
    private formBuilder = inject(FormBuilder);
    private toast = inject(NgToastService);
    isLoading: boolean = false;
    minDate: Date = new Date();

    competitionForm = this.formBuilder.group({
        date: ['', Validators.required],
        startTime: ['', Validators.required],
        endTime: ['', Validators.required],
        location: [
            '',
            [
                Validators.required,
                Validators.maxLength(50),
                Validators.minLength(3),
            ],
        ],
        amount: ['', Validators.required],
        numberOfParticipants: ['', Validators.required],
    });

    competitionErrors = {
        date: { required: 'Date is required.' },
        startTime: { required: 'Starting time is required.' },
        endTime: { required: 'Ending time is required.' },
        location: {
            required: 'Location is required.',
            maxlength: 'Location cannot exceed 50 characters.',
            minlength: 'Location must have at least 3 characters.',
        },
        amount: { required: 'Amount is required.' },
        numberOfParticipants: {
            required: 'Max number of participants is required.',
        },
    };

    generateCode(location: string, date: string): string {
        const codeOne: string = location
            .replace(' ', '')
            .slice(0, 3)
            .toLowerCase();
        const codeTwo: string[] = date.split('-');

        return `${codeOne}-${codeTwo[2]}-${codeTwo[1]}-${codeTwo[0].slice(2, 4)}`;
    }

    onSubmit(): void {
        this.isLoading = true;

        if (this.competitionForm.valid) {
            const competitionReq: CompetitionRequest = this.competitionForm
                .value as unknown as CompetitionRequest;

            competitionReq.code = this.generateCode(
                this.competitionForm.value.location as string,
                this.competitionForm.value.date as string
            );
            competitionReq.date = new Date(
                this.competitionForm.value.date as string
            ).toISOString();
            competitionReq.startTime = new Date(
                this.competitionForm.value.startTime as string
            ).toISOString();
            competitionReq.endTime = new Date(
                this.competitionForm.value.endTime as string
            ).toISOString();

            this.competitionService
                .createCompetition(
                    this.competitionForm.value as unknown as CompetitionRequest
                )
                .pipe(take(1))
                .subscribe({
                    next: (c) => {
                        this.refreshCompetitionsList.emit();

                        this.toast.success({
                            detail: 'Competition created',
                            summary: c.message,
                            duration: 5000,
                        });
                    },
                    error: (err) => {
                        this.isLoading = false;

                        this.toast.error({
                            detail: 'Error occurred',
                            summary: err.error.message,
                            duration: 5000,
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

    ngOnInit(): void {
        initTE({ Modal, Ripple });
    }
}
