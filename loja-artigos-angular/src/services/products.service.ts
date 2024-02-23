import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, Observable } from 'rxjs';

import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private readonly API = 'api/products'

  constructor(private httpClient: HttpClient) { }

  listAll(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(this.API).pipe(first());
  }

  loadById(id: string): Observable<Product> {
    return this.httpClient.get<Product>(`${this.API}/${id}`);
  }

  save(record: Partial<Product>): Observable<Product> {
    if (record.id) {
      return this.update(record);
    } else {
      return this.create(record);
    }
  }

  remove(id: string): Observable<Object> {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }

  private create(record: Partial<Product>): Observable<Product> {
    return this.httpClient.post<Product>(this.API, record).pipe(first());
  }

  private update(record: Partial<Product>): Observable<Product> {
    return this.httpClient.put<Product>(`${this.API}/${record.id}`, record).pipe(first());
  }
}
