import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { MemberRequest } from 'src/app/models/member/member-request';
import { MemberResponse } from 'src/app/models/member/member-response';
import { PaginationResponse } from 'src/app/models/response/pagination-response';
import { Response } from 'src/app/models/response/response';
import { environment } from 'src/environments/environment.development';

@Injectable({
    providedIn: 'root',
})
export class MemberService {
    private http = inject(HttpClient);
    url = environment.apiUrl + '/members';

    getMembers(
        size: number,
        sortBy: string,
        sortOrder: string,
        page: number = 0,
        fullName: string = ''
    ): Observable<PaginationResponse<MemberResponse>> {
        return this.http.get<PaginationResponse<MemberResponse>>(
            `${this.url}?page=${page}&size=${size}&sortBy=${sortBy}&sortOrder=${sortOrder}&fullName=${fullName}`
        );
    }

    getMember(id: number): Observable<Response<MemberResponse>> {
        return this.http.get<Response<MemberResponse>>(`${this.url}/${id}`);
    }

    createMember(member: MemberRequest): Observable<Response<MemberResponse>> {
        return this.http.post<Response<MemberResponse>>(
            `${this.url}/add`,
            member
        );
    }

    updateMember(member: MemberRequest): Observable<Response<MemberResponse>> {
        return this.http.put<Response<MemberResponse>>(
            `${this.url}/update`,
            member
        );
    }

    deleteMember(id: number): Observable<Response<MemberResponse>> {
        return this.http.delete<Response<MemberResponse>>(
            `${this.url}/delete/${id}`
        );
    }
}
