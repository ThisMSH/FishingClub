import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { HuntingRequest } from 'src/app/models/hunting/hunting-request';
import { HuntingResponse } from 'src/app/models/hunting/hunting-response';
import { Response } from 'src/app/models/response/response';
import { environment } from 'src/environments/environment.development';

@Injectable({
    providedIn: 'root',
})
export class HuntingService {
    private http = inject(HttpClient);
    url = environment.apiUrl + '/hunting';

    createHunting(
        hunting: HuntingRequest
    ): Observable<Response<HuntingResponse>> {
        return this.http.post<Response<HuntingResponse>>(
            `${this.url}/add`,
            hunting
        );
    }

    deleteHunting(id: number): Observable<Response<HuntingResponse>> {
        return this.http.delete<Response<HuntingResponse>>(`${this.url}/delete/${id}`);
    }
}
