import { Component, Input } from '@angular/core';

import { Product } from '../../../../model/product';
import { AddToCartService } from 'src/services/add-to-cart.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss']
})
export class ProductCardComponent {

  @Input() product!: Product;

  constructor(private addToCartService: AddToCartService) {}

  ngOnInit() {
  }

  openAddToCartDialog(): void {
    this.addToCartService.openDialog(this.product);
  }
}
