import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { FishRequest } from 'src/app/models/fish/fish-request';
import { FishResponse } from 'src/app/models/fish/fish-response';
import { PaginationResponse } from 'src/app/models/response/pagination-response';
import { Response } from 'src/app/models/response/response';
import { environment } from 'src/environments/environment.development';

@Injectable({
    providedIn: 'root',
})
export class FishService {
    private http = inject(HttpClient);
    url = environment.apiUrl + '/fishes';

    getFish(name: string): Observable<Response<FishResponse>> {
        return this.http.get<Response<FishResponse>>(`${this.url}/${name}`);
    }

    getAllFishes(
        size: number,
        sortBy: string,
        sortOrder: string,
        page: number = 0
    ): Observable<PaginationResponse<FishResponse>> {
        return this.http.get<PaginationResponse<FishResponse>>(
            `${this.url}?page=${page}&size=${size}&sortBy=${sortBy}&sortOrder=${sortOrder}`
        );
    }

    createFish(fish: FishRequest): Observable<Response<FishResponse>> {
        return this.http.post<Response<FishResponse>>(`${this.url}/add`, fish);
    }

    updateFish(fish: FishRequest): Observable<Response<FishResponse>> {
        return this.http.put<Response<FishResponse>>(
            `${this.url}/update`,
            fish
        );
    }

    deleteFish(name: string): Observable<Response<FishResponse>> {
        return this.http.delete<Response<FishResponse>>(
            `${this.url}/delete/${name}`
        );
    }
}
