import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CompetitionResponse } from 'src/app/models/competition/competition-response';
import { PaginationResponse } from 'src/app/models/response/pagination-response';
import { environment } from 'src/environments/environment.development';
import { Observable } from 'rxjs';
import { Response } from 'src/app/models/response/response';
import { CompetitionFilter } from 'src/app/types/types';
import { CompetitionRequest } from 'src/app/models/competition/competition-request';

@Injectable({
    providedIn: 'root',
})
export class CompetitionService {
    private http = inject(HttpClient);
    url = environment.apiUrl + '/competitions';

    getCompetition(code: string): Observable<Response<CompetitionResponse>> {
        return this.http.get<Response<CompetitionResponse>>(
            `${this.url}/${code}`
        );
    }

    getCompetitions(
        size: number,
        sortBy: string,
        sortOrder: string,
        filter: CompetitionFilter,
        page: number = 0
    ): Observable<PaginationResponse<CompetitionResponse>> {
        return this.http.get<PaginationResponse<CompetitionResponse>>(
            `${this.url}?page=${page}&size=${size}&sortBy=${sortBy}&sortOrder=${sortOrder}&filter=${filter}`
        );
    }

    createCompetition(
        competition: CompetitionRequest
    ): Observable<Response<CompetitionResponse>> {
        return this.http.post<Response<CompetitionResponse>>(
            `${this.url}/add`,
            competition
        );
    }

    updateCompetition(
        competition: CompetitionRequest
    ): Observable<Response<CompetitionResponse>> {
        return this.http.put<Response<CompetitionResponse>>(
            `${this.url}/update`,
            competition
        );
    }

    deleteCompetition(code: string): Observable<Response<CompetitionResponse>> {
        return this.http.delete<Response<CompetitionResponse>>(
            `${this.url}/delete/${code}`
        );
    }
}
