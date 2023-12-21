import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { BtnStyle } from 'src/app/types/types';
import { Ripple, initTE } from 'tw-elements';

@Component({
    selector: 'app-default-button',
    templateUrl: './default-button.component.html',
    styleUrls: ['./default-button.component.css'],
})
export class DefaultButtonComponent implements OnInit {
    @Input() type: string = 'submit';
    @Input() label: string = '';
    @Input() btnStyle: BtnStyle = 'primary';
    @Input() btnClass: string = '';
    @Input() isLoading: boolean = false;
    @Output() onClick = new EventEmitter();

    btnClick(evt: MouseEvent) {
        this.onClick.emit(evt)
    }

    ngOnInit(): void {
        initTE({ Ripple });
    }
}
