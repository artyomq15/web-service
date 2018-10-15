import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { CountryModifierComponent } from './country-modifier/country-modifier.component';

import { NgxSoapModule } from 'ngx-soap';
import { CountryService } from './services/CountryService';
import { ToursComponent } from './tours/tours.component';
import { TourService } from './services/TourService';
import { MaterialModule } from './material.module';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule, NoopAnimationsModule } from '@angular/platform-browser/animations';
import { UpdateCountryDialogComponent } from './update-country-dialog/update-country-dialog.component';
import { NotFoundPageComponent } from './not-found-page/not-found-page.component';


import { Routes, RouterModule } from '@angular/router';
import { CountryRestService } from './services/country.rest.service';
import { AddCountryDialogComponent } from './add-country-dialog/add-country-dialog.component';
import { NavbarComponent } from './navbar/navbar.component';
import { TourRestService } from './services/tour.rest.service';
import { TourModifierComponent } from './tour-modifier/tour-modifier.component';

export const appRoutes: Routes = [
  { path: 'countries', component: CountryModifierComponent },
  { path: 'tours', component: ToursComponent },
  { path: 'tourss', component: TourModifierComponent },
  { path: 'not_found', component: NotFoundPageComponent },
  { path: '**',  redirectTo: 'countries' }
];


@NgModule({
  declarations: [
    AppComponent,
    CountryModifierComponent,
    ToursComponent,
    UpdateCountryDialogComponent,
    NotFoundPageComponent,
    AddCountryDialogComponent,
    NavbarComponent,
    TourModifierComponent
  ],
  imports: [
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    NoopAnimationsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    NgxSoapModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [
    { provide: CountryService, useClass: CountryRestService },
    { provide: TourService, useClass: TourRestService }
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    UpdateCountryDialogComponent,
    AddCountryDialogComponent
  ]
})
export class AppModule { }
