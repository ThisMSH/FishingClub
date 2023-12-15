import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { take } from 'rxjs';
import { CompetitionRequest } from 'src/app/models/competition/competition-request';
import { CompetitionService } from 'src/app/services/competition/competition.service';
import { Modal, Ripple, Toast, initTE } from 'tw-elements';

@Component({
    selector: 'app-create-competition',
    templateUrl: './create-competition.component.html',
    styleUrls: ['./create-competition.component.css'],
})
export class CreateCompetitionComponent implements OnInit {
    private competitionService = inject(CompetitionService);
    private formBuilder = inject(FormBuilder);
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
        const codeOne: string = location.replace(' ', '').slice(0, 3).toLowerCase();
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
            competitionReq.date = new Date(this.competitionForm.value.date as string).toISOString();
            competitionReq.startTime = new Date(this.competitionForm.value.startTime as string).toISOString();
            competitionReq.endTime = new Date(this.competitionForm.value.endTime as string).toISOString();

            console.log(competitionReq);

            this.competitionService
                .createCompetition(
                    this.competitionForm.value as unknown as CompetitionRequest
                )
                .pipe(take(1))
                .subscribe({
                    next: (c) => {
                        console.log(c);
                    },
                    error: (err) => {
                        this.isLoading = false;
                        console.log(err);
                    },
                    complete: () => {
                        this.isLoading = false;
                    },
                });
        } else {
            this.isLoading = false;
            console.log('Invalid form');
        }
    }

    ngOnInit(): void {
        initTE({ Modal, Ripple });
    }
}
