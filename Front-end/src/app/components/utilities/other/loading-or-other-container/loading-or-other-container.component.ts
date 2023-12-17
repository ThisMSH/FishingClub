import { Component, Input } from '@angular/core';

@Component({
    selector: 'app-loading-or-other-container',
    templateUrl: './loading-or-other-container.component.html',
    styleUrls: ['./loading-or-other-container.component.css'],
})
export class LoadingOrOtherContainerComponent {
    @Input() customClass: string = '';
}
