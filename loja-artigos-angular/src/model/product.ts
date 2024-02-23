import { Category } from "./category";

export interface Product {
  id?: string;
  name: string;
  category: Category;
  description: string;
  imageUrl: string;
  price: number;
}
