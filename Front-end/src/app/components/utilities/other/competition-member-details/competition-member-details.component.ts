import { Component, Input, OnInit } from '@angular/core';
import { RankingResponse } from 'src/app/models/ranking/ranking-response';
import { Collapse, initTE } from 'tw-elements';

@Component({
    selector: 'app-competition-member-details',
    templateUrl: './competition-member-details.component.html',
    styleUrls: ['./competition-member-details.component.css'],
})
export class CompetitionMemberDetailsComponent implements OnInit {
    @Input() ranking!: RankingResponse;
    @Input() accordionId!: string;
    @Input() accordionTarget!: string;
    @Input() competitionCode!: string;

    ngOnInit(): void {
        initTE({ Collapse }, { allowReinits: true });
        console.log('Ranking:');
        console.log(this.ranking);
    }
}
