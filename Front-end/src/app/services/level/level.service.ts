import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { LevelRequest } from 'src/app/models/level/level-request';
import { LevelResponse } from 'src/app/models/level/level-response';
import { PaginationResponse } from 'src/app/models/response/pagination-response';
import { Response } from 'src/app/models/response/response';
import { environment } from 'src/environments/environment.development';

@Injectable({
    providedIn: 'root',
})
export class LevelService {
    private http = inject(HttpClient);
    url = environment.apiUrl + '/levels';

    getLevels(
        size: number,
        sortBy: string,
        sortOrder: string,
        page: number = 0
    ): Observable<PaginationResponse<LevelResponse>> {
        return this.http.get<PaginationResponse<LevelResponse>>(
            `${this.url}?page=${page}&size=${size}&sortBy=${sortBy}&sortOrder=${sortOrder}`
        );
    }

    getLevel(id: number): Observable<Response<LevelResponse>> {
        return this.http.get<Response<LevelResponse>>(`${this.url}/${id}`);
    }

    createLevel(level: LevelRequest): Observable<Response<LevelResponse>> {
        return this.http.post<Response<LevelResponse>>(
            `${this.url}/add`,
            level
        );
    }

    updateLevel(level: LevelRequest): Observable<Response<LevelResponse>> {
        return this.http.put<Response<LevelResponse>>(
            `${this.url}/update`,
            level
        );
    }

    deleteLevel(id: number): Observable<Response<LevelResponse>> {
        return this.http.delete<Response<LevelResponse>>(
            `${this.url}/delete/${id}`
        );
    }
}
