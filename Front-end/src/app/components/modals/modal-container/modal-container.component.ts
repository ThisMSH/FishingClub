import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Modal, Ripple, initTE } from 'tw-elements';

@Component({
  selector: 'app-modal-container',
  templateUrl: './modal-container.component.html',
  styleUrls: ['./modal-container.component.css']
})
export class ModalContainerComponent implements OnInit {
    @ViewChild('closeModal') closeModalBtn!: ElementRef;
    @Input() title!: string;
    @Input() modalId!: string;
    @Input() titleId!: string;
    @Input() submitLabel!: string;
    @Input() cancelLabel!: string;
    @Input() isLoading: boolean = false;
    @Input() formGroup!: FormGroup;
    @Output() onSubmit = new EventEmitter();

    formSubmit(): void {
        this.onSubmit.emit();
    }

    closeModal(): void {
        this.closeModalBtn.nativeElement.click();
    }

    ngOnInit(): void {
        initTE({ Modal, Ripple });
    }
}
