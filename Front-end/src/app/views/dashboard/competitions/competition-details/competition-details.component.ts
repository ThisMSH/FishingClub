import { Component, OnChanges, OnInit, SimpleChanges, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgToastService } from 'ng-angular-popup';
import { take } from 'rxjs';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';
import { CompetitionService } from 'src/app/services/competition/competition.service';
import { Tab, initTE } from 'tw-elements';

@Component({
    selector: 'app-competition-details',
    templateUrl: './competition-details.component.html',
    styleUrls: ['./competition-details.component.css'],
})
export class CompetitionDetailsComponent implements OnInit {
    private competitionService = inject(CompetitionService);
    private route = inject(ActivatedRoute);
    private toast = inject(NgToastService);
    code!: string;
    competition!: CompetitionResponse | null;
    isLoading: boolean = true;

    getCompetition(code: string): void {
        this.isLoading = true;

        this.competitionService
            .getCompetition(code)
            .pipe(take(1))
            .subscribe({
                next: (c) => {
                    this.competition = c.data as CompetitionResponse;
                },
                error: (err) => {
                    if (err.error.status === 404) {
                        this.competition = null;
                    } else {
                        this.toast.error({
                            detail: 'Error occured',
                            summary: err.error.message,
                            duration: 6000
                        });
                    }

                    this.isLoading = false;
                },
                complete: () => {
                    this.isLoading = false;
                    setTimeout(() => {
                        initTE({ Tab });
                    }, 0)
                }
            })
    }

    ngOnInit(): void {
        initTE({ Tab }, { allowReinits: true });

        this.route.paramMap.subscribe(param => {
            this.code = param.get('code') as string;
        })

        console.log("oninit");

        this.getCompetition(this.code);
    }
}
