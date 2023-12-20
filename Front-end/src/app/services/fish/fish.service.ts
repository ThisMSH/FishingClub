import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
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
}
