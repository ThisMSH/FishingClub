import { Component, Input } from '@angular/core';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';

@Component({
    selector: 'app-competition-ranking',
    templateUrl: './competition-ranking.component.html',
    styleUrls: ['./competition-ranking.component.css'],
})
export class CompetitionRankingComponent {
    @Input() competition!: CompetitionResponse;
}
