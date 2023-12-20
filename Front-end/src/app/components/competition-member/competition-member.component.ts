import { Component, Input, OnInit } from '@angular/core';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';
import { Collapse, initTE } from 'tw-elements';

@Component({
    selector: 'app-competition-member',
    templateUrl: './competition-member.component.html',
    styleUrls: ['./competition-member.component.css'],
})
export class CompetitionMemberComponent implements OnInit {
    @Input() competition!: CompetitionResponse;
    competitionStartTime!: Date;
    now: Date = new Date(Date.now() + 24 * 60 * 60 * 1000);

    ngOnInit(): void {
        initTE({ Collapse });
        this.competitionStartTime = new Date(this.competition.startTime);
    }
}
