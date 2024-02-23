import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Category } from '../../../../model/category';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-category-filter',
  templateUrl: './category-filter.component.html',
  styleUrls: ['./category-filter.component.scss']
})
export class CategoryFilterComponent {

  @Input() categories!: Observable<Category[]> | null;

  @Output() categorySelected = new EventEmitter<string>();

  public selectedCategoryId: string = 'any'

  constructor() {}

  onCategorySelected(category: string): void {
    this.categorySelected.emit(category);
  }
}
