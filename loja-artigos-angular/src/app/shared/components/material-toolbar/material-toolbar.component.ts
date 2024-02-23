import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { CartDialogComponent } from 'src/app/products/components/cart-dialog/cart-dialog.component';
import { CartService } from 'src/services/cart.service';

@Component({
  selector: 'app-material-toolbar',
  templateUrl: './material-toolbar.component.html',
  styleUrls: ['./material-toolbar.component.scss']
})
export class MaterialToolbarComponent implements OnInit, OnDestroy {
  cartItemsCount: number = 0;
  private cartItemsCountSubscription!: Subscription;

  constructor(private cartService: CartService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.cartItemsCountSubscription = this.cartService.getCartItemCount().subscribe(count => {
      this.cartItemsCount = count;
    });
  }

  ngOnDestroy(): void {
    this.cartItemsCountSubscription.unsubscribe();
  }

  onOpenCart(): void {
    this.dialog.open(CartDialogComponent, {
      width: '400px'
    });
  }
}
