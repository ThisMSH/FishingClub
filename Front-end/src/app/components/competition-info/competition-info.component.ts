import { Component, Input, OnInit } from '@angular/core';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';

@Component({
    selector: 'app-competition-info',
    templateUrl: './competition-info.component.html',
    styleUrls: ['./competition-info.component.css'],
})
export class CompetitionInfoComponent implements OnInit {
    @Input() competition!: CompetitionResponse;
    status!: string;

    competitionStatus(): void {
        const now = new Date();
        const startingDate = new Date(this.competition.startTime);
        const endingDate = new Date(this.competition.endTime);

        if (now < startingDate) {
            this.status = "incoming";
        } else if (now >= startingDate && now <= endingDate) {
            this.status = "ongoing";
        } else {
            this.status = "done";
        }
    }

    ngOnInit(): void {
        this.competitionStatus();
    }
}
