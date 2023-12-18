import { DatePipe } from '@angular/common';
import {
    Component,
    EventEmitter,
    Input as NgInput,
    OnInit,
    Output,
    ViewChild,
    inject,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgToastService } from 'ng-angular-popup';
import { take } from 'rxjs';
import { CompetitionRequest } from 'src/app/models/competition/competition-request';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';
import { CompetitionService } from 'src/app/services/competition/competition.service';
import { Input, initTE } from 'tw-elements';
import { ModalContainerComponent } from '../modal-container/modal-container.component';
import { environment } from 'src/environments/environment.development';

@Component({
    selector: 'app-update-competition',
    templateUrl: './update-competition.component.html',
    styleUrls: ['./update-competition.component.css'],
})
export class UpdateCompetitionComponent implements OnInit {
    @NgInput() competition!: CompetitionResponse;
    @Output() refreshCompetitionInfo = new EventEmitter();
    @ViewChild(ModalContainerComponent)
    modalContainer!: ModalContainerComponent;
    private competitionService = inject(CompetitionService);
    private formBuilder = inject(FormBuilder);
    private toast = inject(NgToastService);
    datePipe = inject(DatePipe);
    isLoading: boolean = false;
    tzoffset: number = environment.timeZoneOffset;

    competitionForm: FormGroup = this.formBuilder.group({
        startTime: ['', Validators.required],
        endTime: ['', Validators.required],
        amount: ['', Validators.required],
        numberOfParticipants: ['', Validators.required],
    });

    competitionErrors = {
        startTime: { required: 'Starting time is required.' },
        endTime: { required: 'Ending time is required.' },
        amount: { required: 'Amount is required.' },
        numberOfParticipants: {
            required: 'Max number of participants is required.',
        },
    };

    onSubmit(): void {
        this.isLoading = true;

        if (this.competitionForm.valid) {
            const competitionReq: CompetitionRequest = this.competitionForm
                .value as unknown as CompetitionRequest;
            const startTime = new Date(this.competition.startTime);
            const endTime = new Date(this.competitionForm.value.endTime);
            const newStartTime =
                this.competitionForm.value.startTime.split(':');

            startTime.setHours(newStartTime[0]);
            startTime.setMinutes(newStartTime[1]);

            competitionReq.startTime = new Date(
                startTime.getTime() - this.tzoffset
            ).toISOString();
            competitionReq.endTime = new Date(
                endTime.getTime() - this.tzoffset
            ).toISOString();
            competitionReq.code = this.competition.code;
            competitionReq.location = this.competition.location;
            competitionReq.date = this.competition.date;

            console.log(competitionReq);

            this.competitionService
                .updateCompetition(competitionReq)
                .pipe(take(1))
                .subscribe({
                    next: (c) => {
                        this.refreshCompetitionInfo.emit({ c });
                        this.competition = c.data as CompetitionResponse;
                        this.toast.success({
                            detail: 'Competition updated',
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
                        this.modalContainer.closeModal();
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
        initTE({ Input });

        this.competitionForm.setValue({
            startTime: this.datePipe.transform(
                this.competition.startTime,
                'HH:mm'
            ),
            endTime: this.datePipe.transform(
                this.competition.endTime,
                'yyyy-MM-dd, HH:mm'
            ),
            amount: this.competition.amount,
            numberOfParticipants: this.competition.numberOfParticipants,
        });
    }
}
