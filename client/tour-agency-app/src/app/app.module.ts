import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { CountryModifierComponent } from './country-modifier/country-modifier.component';

import { NgxSoapModule } from 'ngx-soap';
import { CountrySoapService } from './services/country.soap.service';
import { CountryService } from './services/CountryService';
import { TourModifierComponent } from './tour-modifier/tour-modifier.component';

@NgModule({
  declarations: [
    AppComponent,
    CountryModifierComponent,
    TourModifierComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    NgxSoapModule
  ],
  providers: [
    {provide: CountryService, useClass: CountrySoapService}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
