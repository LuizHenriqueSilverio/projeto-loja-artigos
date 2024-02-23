import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, first } from 'rxjs';
import { Sale } from 'src/model/sale';

@Injectable({
  providedIn: 'root'
})
export class SalesService {

  private readonly API = 'api/sales';

  constructor(private httpClient: HttpClient) { }

  listAll(): Observable<Sale> {
    return this.httpClient.get<Sale>(this.API).pipe(first());
  }

  loadById(id: string): Observable<Sale> {
    return this.httpClient.get<Sale>(`${this.API}/${id}`);
  }

  save(record: Partial<Sale>): Observable<Sale> {
    if (record.id) {
      return this.update(record);
    } else {
      return this.create(record);
    }
  }

  remove(id: string): Observable<Object> {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }

  private create(record: Partial<Sale>): Observable<Sale> {
    return this.httpClient.post<Sale>(this.API, record).pipe(first());
  }

  private update(record: Partial<Sale>): Observable<Sale> {
    return this.httpClient.put<Sale>(`${this.API}/${record.id}`, record).pipe(first());
  }
}
