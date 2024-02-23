import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Product } from 'src/model/product';

@Component({
  selector: 'app-add-to-cart',
  templateUrl: './add-to-cart.component.html',
  styleUrls: ['./add-to-cart.component.scss']
})
export class AddToCartComponent {

  quantity: number = 1;
  product: Product;

  constructor(
    public dialogRef: MatDialogRef<AddToCartComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { product: Product, quantity: number }
    ) {
      this.product = data.product;
      this.quantity = data.quantity;
    }

  ngOnInit() {
  }

  onCancel(): void {
    this.dialogRef.close();
  }
}
