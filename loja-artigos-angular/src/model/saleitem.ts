import { Product } from './product';
import { Sale } from './sale';

export interface SaleItem {
  id?: string;
  product: Product;
  quantity: number;
  sale?: Sale
}
