import { SaleItem } from "./saleitem";

export interface Sale {
  id?: string;
  items?: SaleItem[];
  total: number;
}
