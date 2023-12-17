import { Component, EventEmitter, Input, Output } from '@angular/core';
import { InputType } from 'src/app/types/types';

@Component({
    selector: 'app-two-way-input',
    templateUrl: './two-way-input.component.html',
    styleUrls: ['./two-way-input.component.css'],
})
export class TwoWayInputComponent {
    @Input() value!: string;
    @Input() inputId!: string;
    @Input() inputType!: InputType;
    @Input() label!: string;
    @Input() maxLength!: number;
    @Input() isRequired: boolean = false;
    @Input() isDisabled: boolean = false;
    @Output() valueChange = new EventEmitter<string>();

    onValueChange(evt: string): void {
        this.value = evt;
        this.valueChange.emit(this.value);
    }
}
