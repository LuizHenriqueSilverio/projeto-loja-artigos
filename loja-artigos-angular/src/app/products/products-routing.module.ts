import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { ProductsGridComponent } from "./components/products-grid/products-grid.component";

const routes: Routes = [
  { path: "", component: ProductsGridComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductsRoutingModule {}
