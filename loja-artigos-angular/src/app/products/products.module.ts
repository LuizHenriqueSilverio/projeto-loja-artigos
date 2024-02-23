import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SharedModule } from '../shared/shared.module';
import { AddToCartComponent } from './components/add-to-cart/add-to-cart.component';
import { CategoryFilterComponent } from './components/category-filter/category-filter.component';
import { ProductCardComponent } from './components/product-card/product-card.component';
import { ProductsGridComponent } from './components/products-grid/products-grid.component';
import { ProductsRoutingModule } from './products-routing.module';
import { CartDialogComponent } from './components/cart-dialog/cart-dialog.component';

@NgModule({
  declarations: [
    ProductsGridComponent,
    ProductCardComponent,
    CategoryFilterComponent,
    AddToCartComponent,
    CartDialogComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ProductsRoutingModule,
    AppMaterialModule,
    SharedModule
  ]
})
export class ProductsModule { }
