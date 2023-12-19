import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { RankingRequest } from 'src/app/models/ranking/ranking-request';
import { RankingResponse } from 'src/app/models/ranking/ranking-response';
import { Response } from 'src/app/models/response/response';
import { environment } from 'src/environments/environment.development';

@Injectable({
    providedIn: 'root',
})
export class RankingService {
    private http = inject(HttpClient);
    url = environment.apiUrl + '/ranking';

    createRanking(
        ranking: RankingRequest
    ): Observable<Response<RankingResponse>> {
        return this.http.post<Response<RankingResponse>>(
            `${this.url}/add`,
            ranking
        );
    }

    deleteRanking(
        ranking: RankingRequest
    ): Observable<Response<RankingResponse>> {
        return this.http.delete<Response<RankingResponse>>(
            `${this.url}/delete/code-${ranking.competitionCode}/member-${ranking.memberNumber}`
        );
    }
}
