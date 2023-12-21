import { Component, Input } from '@angular/core';
import { MemberResponse } from 'src/app/models/member/member-response';

@Component({
    selector: 'app-member-card',
    templateUrl: './member-card.component.html',
    styleUrls: ['./member-card.component.css'],
})
export class MemberCardComponent {
    @Input() member!: MemberResponse;
}
