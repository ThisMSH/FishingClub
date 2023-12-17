import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { NgToastModule } from 'ng-angular-popup'
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
import { DashboardHeaderComponent } from './components/dashboard-header/dashboard-header.component';
import { DashboardSideBarComponent } from './components/dashboard-side-bar/dashboard-side-bar.component';
import { DefaultButtonComponent } from './components/utilities/elements/default-button/default-button.component';
import { DarkModeComponent } from './components/dark-mode/dark-mode.component';
import { BreadcrumbComponent } from './components/utilities/other/breadcrumb/breadcrumb.component';
import { SideBarLinkComponent } from './components/utilities/elements/side-bar-link/side-bar-link.component';
import { ContentContainerComponent } from './components/utilities/other/content-container/content-container.component';
import { ContentContainerHeaderComponent } from './components/utilities/other/content-container-header/content-container-header.component';
import { CompetitionCardComponent } from './components/competition-card/competition-card.component';
import { DefaultInputComponent } from './components/utilities/elements/default-input/default-input.component';
import { CompetitionInfoComponent } from './components/competition-info/competition-info.component';
import { CompetitionRankingComponent } from './components/competition-ranking/competition-ranking.component';
import { CompetitionMemberComponent } from './components/competition-member/competition-member.component';
import { CompetitionMemberDetailsComponent } from './components/utilities/other/competition-member-details/competition-member-details.component';
import { ModalContainerComponent } from './components/modals/modal-container/modal-container.component';
import { CreateCompetitionComponent } from './components/modals/create-competition/create-competition.component';
import { ControlValueAccessorDirective } from './directives/control-value-accessor/control-value-accessor.directive';
import { CustomCurrencyPipe } from './pipes/custom-currency/custom-currency.pipe';
import { PaginationComponent } from './components/pagination/pagination.component';
import { TwoWayInputComponent } from './components/utilities/elements/two-way-input/two-way-input.component';
import { SpinnerComponent } from './components/utilities/other/spinner/spinner.component';
import { LoadingOrOtherContainerComponent } from './components/utilities/other/loading-or-other-container/loading-or-other-container.component';
import { TwoWaySelectInputComponent } from './components/utilities/elements/two-way-select-input/two-way-select-input.component';
import { DefaultDropdownComponent } from './components/utilities/elements/default-dropdown/default-dropdown.component';

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
        DashboardComponent,
        DashboardHeaderComponent,
        DashboardSideBarComponent,
        DefaultButtonComponent,
        DarkModeComponent,
        BreadcrumbComponent,
        SideBarLinkComponent,
        ContentContainerComponent,
        ContentContainerHeaderComponent,
        CompetitionCardComponent,
        DefaultInputComponent,
        CompetitionInfoComponent,
        CompetitionRankingComponent,
        CompetitionMemberComponent,
        CompetitionMemberDetailsComponent,
        ModalContainerComponent,
        CreateCompetitionComponent,
        ControlValueAccessorDirective,
        CustomCurrencyPipe,
        PaginationComponent,
        TwoWayInputComponent,
        SpinnerComponent,
        LoadingOrOtherContainerComponent,
        TwoWaySelectInputComponent,
        DefaultDropdownComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        NgToastModule
    ],
    providers: [],
    bootstrap: [AppComponent],
})
export class AppModule {}
