import { Component } from '@angular/core';
import { SideBarLinks } from 'src/app/types/types';

@Component({
    selector: 'app-dashboard-side-bar',
    templateUrl: './dashboard-side-bar.component.html',
    styleUrls: ['./dashboard-side-bar.component.css'],
})
export class DashboardSideBarComponent {
    sideBarLinks: SideBarLinks[] = [
        {
            name: 'Competition',
            img: '/assets/images/icons/competition.png',
            link: '/dashboard/competitions',
        },
        {
            name: 'Members',
            img: '/assets/images/icons/member.png',
            link: '/dashboard/members',
        },
        // {
        //     name: 'Fishes',
        //     img: '/assets/images/icons/fish.png',
        //     link: '/dashboard/fishes',
        // },
        // {
        //     name: 'Levels',
        //     img: '/assets/images/icons/level.png',
        //     link: '/dashboard/levels',
        // },
    ];
}
