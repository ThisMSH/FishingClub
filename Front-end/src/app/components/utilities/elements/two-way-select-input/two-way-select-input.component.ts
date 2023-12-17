import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Select, initTE } from 'tw-elements';

@Component({
    selector: 'app-two-way-select-input',
    templateUrl: './two-way-select-input.component.html',
    styleUrls: ['./two-way-select-input.component.css'],
})
export class TwoWaySelectInputComponent implements OnInit {
    @Input() value!: string | number;
    @Input() selectId!: string;
    @Input() label!: string;
    @Input() isRequired: boolean = false;
    @Input() isDisabled: boolean = false;
    @Input() options: Record<number | string, number | string> = {};
    @Output() valueChange = new EventEmitter<string | number>();

    onValueChange(evt: string | number): void {
        this.valueChange.emit(evt);
    }

    ngOnInit(): void {
        initTE({ Select });
    }
}
