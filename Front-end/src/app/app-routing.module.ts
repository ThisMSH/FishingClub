import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './views/main/home/home.component';
import { MainComponent } from './views/main/main.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';
import { CompetitionsComponent } from './views/dashboard/competitions/competitions.component';
import { CompetitionDetailsComponent } from './views/dashboard/competitions/competition-details/competition-details.component';
import { MembersComponent } from './views/dashboard/members/members.component';
import { MembersDetailsComponent } from './views/dashboard/members/members-details/members-details.component';
import { FishesComponent } from './views/dashboard/fishes/fishes.component';
import { FishDetailsComponent } from './views/dashboard/fishes/fish-details/fish-details.component';
import { LevelsComponent } from './views/dashboard/levels/levels.component';
import { LevelsDetailsComponent } from './views/dashboard/levels/levels-details/levels-details.component';
import { PageNotFoundComponent } from './views/page-not-found/page-not-found.component';

const routes: Routes = [
    {
        path: '',
        component: MainComponent,
        children: [
            {
                path: '',
                // component: HomeComponent,
                title: 'AFTAS Fishing Club',
                redirectTo: '/dashboard/competitions',
                pathMatch: 'full',
            },
        ],
    },
    {
        path: 'dashboard',
        component: DashboardComponent,
        children: [
            {
                path: '',
                redirectTo: 'competitions',
                pathMatch: 'full',
            },
            {
                path: 'competitions',
                children: [
                    {
                        path: '',
                        component: CompetitionsComponent,
                        title: 'Competitions - AFTAS Dashboard',
                    },
                    {
                        path: ':code',
                        component: CompetitionDetailsComponent,
                    },
                ],
            },
            {
                path: 'members',
                children: [
                    {
                        path: '',
                        component: MembersComponent,
                        title: 'Members - AFTAS Dashboard',
                    },
                    {
                        path: ':number',
                        component: MembersDetailsComponent,
                    },
                ],
            },
            {
                path: 'fishes',
                children: [
                    {
                        path: '',
                        component: FishesComponent,
                        title: 'Fishes - AFTAS Dashboard',
                    },
                    {
                        path: ':name',
                        component: FishDetailsComponent,
                    },
                ],
            },
            {
                path: 'levels',
                children: [
                    {
                        path: '',
                        component: LevelsComponent,
                        title: 'Levels - AFTAS Dashboard',
                    },
                    {
                        path: ':id',
                        component: LevelsDetailsComponent,
                    },
                ],
            },
        ],
    },
    {
        path: '**',
        component: PageNotFoundComponent,
        title: 'Page Not Found - AFTAS',
    },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule],
})
export class AppRoutingModule {}
