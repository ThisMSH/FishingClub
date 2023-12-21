import { Component, Input, OnInit, ViewChild, inject } from '@angular/core';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';
import { DeleteModalComponent } from '../modals/delete-modal/delete-modal.component';
import { CompetitionService } from 'src/app/services/competition/competition.service';
import { take } from 'rxjs';
import { NgToastService } from 'ng-angular-popup';
import { Router } from '@angular/router';

@Component({
    selector: 'app-competition-info',
    templateUrl: './competition-info.component.html',
    styleUrls: ['./competition-info.component.css'],
})
export class CompetitionInfoComponent implements OnInit {
    @Input() competition!: CompetitionResponse;
    private competitionService = inject(CompetitionService);
    private router = inject(Router);
    toast = inject(NgToastService);
    status!: string;
    deleteLoading: boolean = false;
    showDeleteModal: boolean = false;

    competitionStatus(): void {
        const now = new Date();
        const startingDate = new Date(this.competition.startTime);
        const endingDate = new Date(this.competition.endTime);

        if (now < startingDate) {
            this.status = 'incoming';
        } else if (now >= startingDate && now <= endingDate) {
            this.status = 'ongoing';
        } else {
            this.status = 'done';
        }
    }

    setCompetition(evt: any): void {
        this.competition = evt.c.data as CompetitionResponse;
    }

    deleteCompetition(): void {
        this.deleteLoading = true;

        this.competitionService
            .deleteCompetition(this.competition.code as string)
            .pipe(take(1))
            .subscribe({
                next: (c) => {
                    this.toast.success({
                        detail: 'Competition deleted',
                        summary: `The competition '${
                            (c.data as CompetitionResponse).code
                        }' has been deleted successfully.`,
                        duration: 6000,
                    });

                    this.router.navigate(['/dashboard/competitions']);
                },
                error: (err) => {
                    this.deleteLoading = false;

                    this.toast.success({
                        detail: 'Error occurred',
                        summary: err.error.message,
                        duration: 6000,
                    });
                },
                complete: () => {
                    this.deleteLoading = false;
                },
            });
    }

    ngOnInit(): void {
        this.competitionStatus();
    }
}
