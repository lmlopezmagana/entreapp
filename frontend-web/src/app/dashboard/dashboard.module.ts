import { AgmCoreModule } from '@agm/core';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { MaterialModule } from './../material-module';
import { BadgesComponent } from './badges/badges.component';
import { CategoriesComponent } from './categories/categories.component';
import { DashboardRoutes } from './dashboard.routing';
import { MenuComponent } from './menu/menu.component';
import { MyProfileComponent } from './my-profile/my-profile.component';
import { PoiCreateComponent } from './poi-create/poi-create.component';
import { PoiDetailsComponent } from './poi-details/poi-details.component';
import { PoiEditComponent } from './poi-edit/poi-edit.component';
import { PoiComponent } from './poi/poi.component';
import { RouteComponent } from './route/route.component';
import { UserComponent } from './user/user.component';
import { QuillModule } from 'ngx-quill';
import { FileSelectDirective } from 'ng2-file-upload';

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(DashboardRoutes),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyA-JcBiiDwRaudqbUlqAC4c-Ehn4uPCsqY'
    }),
    QuillModule
  ],
  // tslint:disable-next-line:max-line-length
  declarations: [
    PoiComponent,
    MenuComponent,
    UserComponent,
    CategoriesComponent,
    RouteComponent,
    BadgesComponent,
    PoiDetailsComponent,
    MyProfileComponent,
    PoiEditComponent,
    PoiCreateComponent,
    FileSelectDirective
  ],
})

export class DashboardModule { }
