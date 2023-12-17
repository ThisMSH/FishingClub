import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
    selector: 'app-default-dropdown',
    templateUrl: './default-dropdown.component.html',
    styleUrls: ['./default-dropdown.component.css'],
})
export class DefaultDropdownComponent {
    @Input() label!: string;
    @Input() dropdownId!: string;
    @Input() list!: string[];
    @Input() current!: string;
    @Output() sendItem = new EventEmitter();

    selectedItem(item: string): void {
        this.sendItem.emit({item});
    }
}
