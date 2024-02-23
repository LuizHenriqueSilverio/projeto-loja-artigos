import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, Observable } from 'rxjs';
import { SaleItem } from 'src/model/saleitem';

@Injectable({
  providedIn: 'root'
})
export class SaleItemService {

  private readonly API = 'api/sale-items';

  constructor(private httpClient: HttpClient) { }

  listAll(): Observable<SaleItem[]> {
    return this.httpClient.get<SaleItem[]>(this.API).pipe(first());
  }

  loadById(id: string): Observable<SaleItem> {
    return this.httpClient.get<SaleItem>(`${this.API}/${id}`);
  }

  save(record: Partial<SaleItem>): Observable<SaleItem> {
    if (record.id) {
      return this.update(record);
    } else {
      return this.create(record);
    }
  }

  remove(id: string): Observable<Object> {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }

  private create(record: Partial<SaleItem>): Observable<SaleItem> {
    return this.httpClient.post<SaleItem>(this.API, record).pipe(first());
  }

  private update(record: Partial<SaleItem>): Observable<SaleItem> {
    return this.httpClient.put<SaleItem>(`${this.API}/${record.id}`, record).pipe(first());
  }
}
