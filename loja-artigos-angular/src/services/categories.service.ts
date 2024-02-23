import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../model/category';
import { first } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  private readonly API = 'api/categories';

  constructor(private httpClient: HttpClient) { }

  listAll() {
    return this.httpClient.get<Category[]>(this.API).pipe(first());
  }

  loadById(id: string) {
    return this.httpClient.get<Category>(`${this.API}/${id}`);
  }

  save(record: Partial<Category>) {
    if (record.id) {
      return this.update(record);
    } else {
      return this.create(record);
    }
  }

  remove(id: string) {
    return this.httpClient.delete(`${this.API}/${id}`).pipe(first());
  }

  private create(record: Partial<Category>) {
    return this.httpClient.post<Category>(this.API, record).pipe(first());
  }

  private update(record: Partial<Category>) {
    return this.httpClient.put<Category>(`${this.API}/${record.id}`, record).pipe(first());
  }
}
