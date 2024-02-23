import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from './app-material/app-material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MaterialDialogComponent } from './components/material-dialog/material-dialog.component';

@NgModule({
  declarations: [
  
    MaterialDialogComponent
  ],
  imports: [
    CommonModule,
    AppMaterialModule,
    FlexLayoutModule
  ],
  exports: [
    AppMaterialModule,
    FlexLayoutModule
  ]
})
export class SharedModule { }
