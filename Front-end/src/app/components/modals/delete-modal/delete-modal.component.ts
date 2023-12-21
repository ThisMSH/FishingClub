import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
    selector: 'app-delete-modal',
    templateUrl: './delete-modal.component.html',
    styleUrls: ['./delete-modal.component.css'],
})
export class DeleteModalComponent {
    @Output() confirmDelete = new EventEmitter();
    @Input() deleteLoading: boolean = false;
    @Input() showModal: boolean = false;

    onConfirm(): void {
        this.confirmDelete.emit();
    }
}
