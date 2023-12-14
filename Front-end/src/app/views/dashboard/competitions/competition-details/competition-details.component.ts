import { Component, OnInit } from '@angular/core';
import { Tab, initTE } from 'tw-elements';

@Component({
    selector: 'app-competition-details',
    templateUrl: './competition-details.component.html',
    styleUrls: ['./competition-details.component.css'],
})
export class CompetitionDetailsComponent implements OnInit {
    ngOnInit(): void {
        initTE({ Tab });
    }
}
