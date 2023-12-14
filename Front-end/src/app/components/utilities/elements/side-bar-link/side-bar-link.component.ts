import { Component, Input } from '@angular/core';
import { SideBarLinks } from 'src/app/types/types';

@Component({
    selector: 'app-side-bar-link',
    templateUrl: './side-bar-link.component.html',
    styleUrls: ['./side-bar-link.component.css'],
})
export class SideBarLinkComponent {
    @Input() linkInfo!: SideBarLinks;
    isActive: boolean = false;
}
