import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './views/main/home/home.component';
import { CompetitionsComponent } from './views/dashboard/competitions/competitions.component';
import { CompetitionDetailsComponent } from './views/dashboard/competitions/competition-details/competition-details.component';
import { MembersComponent } from './views/dashboard/members/members.component';
import { MembersDetailsComponent } from './views/dashboard/members/members-details/members-details.component';
import { LevelsComponent } from './views/dashboard/levels/levels.component';
import { LevelsDetailsComponent } from './views/dashboard/levels/levels-details/levels-details.component';
import { FishesComponent } from './views/dashboard/fishes/fishes.component';
import { FishDetailsComponent } from './views/dashboard/fishes/fish-details/fish-details.component';
import { PageNotFoundComponent } from './views/page-not-found/page-not-found.component';
import { MainComponent } from './views/main/main.component';
import { DashboardComponent } from './views/dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CompetitionsComponent,
    CompetitionDetailsComponent,
    MembersComponent,
    MembersDetailsComponent,
    LevelsComponent,
    LevelsDetailsComponent,
    FishesComponent,
    FishDetailsComponent,
    PageNotFoundComponent,
    MainComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
