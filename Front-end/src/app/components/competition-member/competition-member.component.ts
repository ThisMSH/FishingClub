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
    ngOnInit(): void {
        initTE({ Collapse });
    }
}
