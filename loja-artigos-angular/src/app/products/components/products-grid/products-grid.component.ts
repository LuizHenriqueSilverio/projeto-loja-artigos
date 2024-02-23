import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { catchError, map, Observable, of } from 'rxjs';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

import { Category } from '../../../../model/category';
import { Product } from '../../../../model/product';
import { CategoriesService } from '../../../../services/categories.service';
import { ProductsService } from '../../../../services/products.service';

@Component({
  selector: 'app-products-grid',
  templateUrl: './products-grid.component.html',
  styleUrls: ['./products-grid.component.scss']
})
export class ProductsGridComponent {

  cols!: number;
  products$: Observable<Product[]> | null = null;
  categories$: Observable<Category[]> | null = null;
  filteredProducts$: Observable<Product[]> | null = null;
  selectedCategoryId: string = 'any';

  constructor(
    private breakPointObserver: BreakpointObserver,
    private productService: ProductsService,
    private categoryService: CategoriesService,
    public dialog: MatDialog
    ) {
      this.refresh();
    }

  ngOnInit(): void {
    this.refreshCols();
    this.refresh();
  }

  ngOnDestroy(): void {
  }

  refreshCols(): void {
    this.breakPointObserver.observe([
      Breakpoints.Handset,
      Breakpoints.Tablet,
      Breakpoints.Web,
      Breakpoints.HandsetPortrait,
      Breakpoints.TabletPortrait,
      Breakpoints.WebPortrait
    ]).subscribe(result => {
      if (result.breakpoints[Breakpoints.Handset] || result.breakpoints[Breakpoints.HandsetPortrait]) {
        this.cols = 1;
      } else if (result.breakpoints[Breakpoints.Tablet] || result.breakpoints[Breakpoints.TabletPortrait]) {
        this.cols = 2;
      } else {
        this.cols = 3;
      }
    });
  }

  onError(errorMsg: string): void {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  refresh(): void {

    this.products$ = this.productService.listAll().pipe(
      catchError(error => {
        this.onError("Error loading products");
        return of([]);
      })
    );

    this.categories$ = this.categoryService.listAll().pipe(
      catchError(error => {
        this.onError("Error loading categories");
        return of([]);
      })
    );
  }

  onCategorySelected(categoryId: string): void {
    if(!this.products$) {
      return;
    }

    this.filteredProducts$ = this.products$.pipe(
      catchError(error => {
        this.onError("Error filtering products");
        return of([]);
      }),
      map(products => {
        if(categoryId === "any") {
          return products;
        }else{
          return products.filter(product => product.category.id === categoryId);
        }
      })
    );
  }
}
