import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-modal-container',
  templateUrl: './modal-container.component.html',
  styleUrls: ['./modal-container.component.css']
})
export class ModalContainerComponent {
    @Input() title!: string;
    @Input() modalId!: string;
    @Input() titleId!: string;
    @Input() isLoading: boolean = false;
    @Input() formGroup!: FormGroup;
    @Output() onSubmit = new EventEmitter();

    formSubmit(): void {
        this.onSubmit.emit();
    }
}
