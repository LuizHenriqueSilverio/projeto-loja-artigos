import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AddToCartComponent } from 'src/app/products/components/add-to-cart/add-to-cart.component';
import { CartService } from './cart.service';
import { SaleItem } from 'src/model/saleitem';
import { Product } from 'src/model/product';

@Injectable({
  providedIn: 'root'
})
export class AddToCartService {

  constructor(
    private dialog: MatDialog,
    private cartService: CartService
    ) { }

  openDialog(product: Product): void {
    const dialogRef = this.dialog.open(AddToCartComponent, {
      width: '250px',
      data: { product: product, quantity: 1}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        const saleItem: SaleItem = {
          product: result.product,
          quantity: result.quantity
        };
        this.cartService.addSaleItem(saleItem);
      }
    });
  }
}
