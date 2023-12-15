import { Component, Input, OnInit } from '@angular/core';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';
import { Ripple, initTE } from 'tw-elements';

@Component({
    selector: 'app-competition-card',
    templateUrl: './competition-card.component.html',
    styleUrls: ['./competition-card.component.css'],
})
export class CompetitionCardComponent implements OnInit {
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
        initTE({ Ripple });
        this.competitionStatus();
    }
}
