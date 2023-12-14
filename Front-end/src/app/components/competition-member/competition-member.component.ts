import { Component, OnInit } from '@angular/core';
import { Collapse, initTE } from 'tw-elements';

@Component({
    selector: 'app-competition-member',
    templateUrl: './competition-member.component.html',
    styleUrls: ['./competition-member.component.css'],
})
export class CompetitionMemberComponent implements OnInit {
    ngOnInit(): void {
        initTE({ Collapse });
    }
}
