import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Dropdown, initTE } from 'tw-elements';

@Component({
    selector: 'app-default-dropdown',
    templateUrl: './default-dropdown.component.html',
    styleUrls: ['./default-dropdown.component.css'],
})
export class DefaultDropdownComponent implements OnInit {
    @Input() label!: string;
    @Input() dropdownId!: string;
    @Input() list!: string[];
    @Input() current!: string;
    @Output() sendItem = new EventEmitter();

    selectedItem(item: string): void {
        this.sendItem.emit({ item });
    }

    ngOnInit(): void {
        initTE({ Dropdown });
    }
}
