import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { BehaviorSubject, forkJoin, map, mergeMap, Observable } from 'rxjs';
import { Sale } from 'src/model/sale';
import { SaleItem } from 'src/model/saleitem';

import { SaleItemService } from './sale-item.service';
import { SalesService } from './sales.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private saleItemSubject = new BehaviorSubject<number>(0);
  private saleItems: SaleItem[] = [];

  constructor(
    private saleService: SalesService,
    private saleItemService: SaleItemService,
    private cookieService: CookieService
  ) {
    this.loadCartItemsFromCookie();
  }

  addSaleItem(saleItem: SaleItem): void {
    this.saleItems.push(saleItem);
    this.saleItemSubject.next(this.saleItems.length);
    this.saveCartItemsToCookie();
  }

  removeSaleItem(item: SaleItem): void {
    const index = this.saleItems.findIndex(i => i === item);
    if(index != -1){
      this.saleItems.splice(index, 1);
      this.saleItemSubject.next(this.saleItems.length);
    }
    this.saveCartItemsToCookie();
  }

  updateQuantity(item: SaleItem, quantity: number): void {
    const index = this.saleItems.findIndex(i => i === item);
    if(index != -1){
      this.saleItems[index].quantity = quantity;
    }
    this.saveCartItemsToCookie();
  }

  getSaleItems(): SaleItem[] {
    this.loadCartItemsFromCookie();
    return this.saleItems;
  }

  clearSaleItems(): void{
    this.saleItems = [];
    this.saleItemSubject.next(0);
    this.cookieService.delete('cart');
  }

  getCartItemCount(): Observable<number> {
    return this.saleItemSubject.asObservable();
  }

  getTotalPrice(): number {
    return this.saleItems.reduce((acc, saleItem) => acc + (saleItem.product.price * saleItem.quantity), 0);
  }

  finalizeOrder(): Observable<any> {
    const total = this.getTotalPrice();
    const sale: Sale = { total, items: this.saleItems };

    return this.saleService.save(sale).pipe(
      mergeMap((savedSale: any) => {
        const saleItemsPromises: Observable<SaleItem>[] = [];
        this.saleItems.forEach(saleItem => {
          saleItem.sale = savedSale;
          saleItemsPromises.push(this.saleItemService.save(saleItem));
        });
        return forkJoin(saleItemsPromises);
      }),
      map(() => {
        this.clearSaleItems();
        return true;
      })
    );
  }

  saveCartItemsToCookie(): void {
    const saleItemsString = JSON.stringify(this.saleItems);
    this.cookieService.set('cart', saleItemsString);
  }

  loadCartItemsFromCookie(): void {
    const saleItemsString = this.cookieService.get('cart');
    if(saleItemsString){
      this.saleItems = JSON.parse(saleItemsString);
      this.saleItemSubject.next(this.saleItems.length);
    }
  }
}
