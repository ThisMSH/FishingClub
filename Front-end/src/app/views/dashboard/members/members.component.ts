import { Component, OnInit, inject } from '@angular/core';
import { NgToastService } from 'ng-angular-popup';
import { take } from 'rxjs';
import { MemberResponse } from 'src/app/models/member/member-response';
import { PaginationResponse } from 'src/app/models/response/pagination-response';
import { MemberService } from 'src/app/services/member/member.service';
import { PaginationParams } from 'src/app/types/types';
import { Input, initTE } from 'tw-elements';

@Component({
    selector: 'app-members',
    templateUrl: './members.component.html',
    styleUrls: ['./members.component.css'],
})
export class MembersComponent implements OnInit {
    private memberService = inject(MemberService);
    toast = inject(NgToastService);
    search!: string;
    isLoading: boolean = true;
    members!: PaginationResponse<MemberResponse>;
    memberArray: MemberResponse[] = [];
    memberParams!: PaginationParams;
    size!: number;
    sizeOptions: Record<string, number> = {
        '03': 3,
        '06': 6,
        '12': 12,
    };

    initParams(): void {
        const storedParams: string | null =
            localStorage.getItem('memberParams');

        if (!storedParams) {
            const params: PaginationParams = {
                size: 12,
                sortBy: 'name',
                sortOrder: 'ASC',
            };

            this.memberParams = params;
            localStorage.setItem('memberParams', JSON.stringify(params));
        } else {
            this.memberParams = JSON.parse(storedParams as string);
        }

        this.size = this.memberParams.size as number;
    }

    goToPage(evt: any): void {
        this.memberParams = { ...this.memberParams, page: evt.p };
        this.getAllMembers(this.memberParams);
    }

    setSize(evt: any): void {
        this.memberParams.size = evt.size;
        const { page, fullName, ...params } = { ...this.memberParams };
        const newParams = { ...params, page: 0, fullName: fullName ?? '' };

        localStorage.setItem('memberParams', JSON.stringify(params));

        this.getAllMembers(newParams);
    }

    searchFn(): void {
        this.memberParams.page = 0;
        this.memberParams.fullName = this.search;

        this.getAllMembers(this.memberParams);
    }

    getAllMembers(params: PaginationParams): void {
        const { size, sortBy, sortOrder, fullName, page } = params;
        let p;
        this.isLoading = true;

        if (!page) {
            p = 0;
        } else {
            p = page;
        }

        console.log(params);

        this.memberService
            .getMembers(
                size as number,
                sortBy as string,
                sortOrder as string,
                p as number,
                fullName ?? ''
            )
            .pipe(take(1))
            .subscribe({
                next: (c) => {
                    this.members = c;
                    this.memberArray = c.data.content;
                    console.log(this.memberArray);
                },
                error: (err) => {
                    this.toast.error({
                        detail: 'Error occured',
                        summary: err.error.message,
                        duration: 6000,
                    });
                    this.isLoading = false;
                },
                complete: () => {
                    this.isLoading = false;
                },
            });
    }

    ngOnInit(): void {
        initTE({ Input }, { allowReinits: true });
        this.initParams();
        this.getAllMembers(this.memberParams);
    }
}
