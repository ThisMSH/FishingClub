import { Component, OnInit } from '@angular/core';
import { Dropdown, Input, initTE } from 'tw-elements';

@Component({
    selector: 'app-competitions',
    templateUrl: './competitions.component.html',
    styleUrls: ['./competitions.component.css'],
})
export class CompetitionsComponent implements OnInit {
    ngOnInit(): void {
        initTE({ Input, Dropdown });
    }
}
