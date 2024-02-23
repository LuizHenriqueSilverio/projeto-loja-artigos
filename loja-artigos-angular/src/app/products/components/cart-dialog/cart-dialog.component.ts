import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { MaterialDialogComponent } from 'src/app/shared/components/material-dialog/material-dialog.component';
import { SaleItem } from 'src/model/saleitem';
import { CartService } from 'src/services/cart.service';

@Component({
  selector: 'app-cart-dialog',
  templateUrl: './cart-dialog.component.html',
  styleUrls: ['./cart-dialog.component.scss']
})
export class CartDialogComponent {

  cartItems: SaleItem[] = [];
  totalPrice: number = 0;

  constructor(
    private cartService: CartService,
    public dialogRef: MatDialogRef<CartDialogComponent>,
    public dialog: MatDialog
  ) {
    this.cartItems = this.cartService.getSaleItems();
    this.totalPrice = this.cartService.getTotalPrice();
  }

  onCloseDialog(): void {
    this.dialogRef.close();
  }

  onClearCart(): void {
    this.cartService.clearSaleItems();
    this.cartItems = [];
    this.totalPrice = 0;
  }

  onUpdateQuantity(item: SaleItem, quantity: number): void {
    this.cartService.updateQuantity(item, quantity);
    this.totalPrice = this.cartService.getTotalPrice();
  }

  onRemoveItem(item: SaleItem): void {
    this.cartService.removeSaleItem(item);
    this.cartItems = this.cartService.getSaleItems();
    this.totalPrice = this.cartService.getTotalPrice();
  }

  onCheckout(): void {
    this.cartService.finalizeOrder().subscribe(success => {
      if(success) {
        this.onSuccess();
      }else {
        this.onError();
      }
    });
  }

  private onSuccess(): void {
    this.onClearCart();
    this.dialog.open(MaterialDialogComponent, {
      width: '400px',
      data: {
        title: 'Compra finalizada com sucesso!',
        message: 'Sua compra foi finalizada com sucesso!'
      }
    });
  }

  private onError(): void {
    this.dialog.open(ErrorDialogComponent, {
      width: '400px',
      data: { message: 'Erro ao finalizar a compra!'}
    })
  }
}
