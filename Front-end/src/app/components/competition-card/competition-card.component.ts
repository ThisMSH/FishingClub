import { Component, OnInit } from '@angular/core';
import { Ripple, initTE } from 'tw-elements';

@Component({
    selector: 'app-competition-card',
    templateUrl: './competition-card.component.html',
    styleUrls: ['./competition-card.component.css'],
})
export class CompetitionCardComponent implements OnInit {
    ngOnInit(): void {
        initTE({ Ripple });
    }
}
