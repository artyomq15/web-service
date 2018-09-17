import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { CountryModifierComponent } from './country-modifier/country-modifier.component';

import { NgxSoapModule } from 'ngx-soap';
import { CountrySoapService } from './services/country.soap.service';
import { CountryService } from './services/CountryService';

@NgModule({
  declarations: [
    AppComponent,
    CountryModifierComponent
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
