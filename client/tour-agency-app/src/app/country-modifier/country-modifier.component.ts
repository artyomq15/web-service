import { Component, OnInit, Input } from '@angular/core';
import { CountryService } from '../services/CountryService';
import { Country } from '../domain/Country';

@Component({
  selector: 'app-country-modifier',
  templateUrl: './country-modifier.component.html',
  styleUrls: ['./country-modifier.component.css']
})
export class CountryModifierComponent implements OnInit {

  countries: Country[];
  country: Country;
  getId: number = 1;
  saveName: string = "Russia";
  savedCountry: Country;
  updateId: number = 1;
  updateName: string = "Belarus";
  updatedCountry: Country;
  deleteId: number= 1;
  deletedCountry: Country;

  constructor(private countryService: CountryService) { }

  ngOnInit() { }

  private findAllCountries(): void {
    this.countryService.findAll().subscribe(
      (res: Country[]) => {
        this.countries = res;
      }
    );
  }

  public get() {
    this.findCountry(this.getId);
  }

  private findCountry(id:number): void {
    this.countryService.findOne(id).subscribe(
      (res: Country) => {
        this.country = res;
      },
      (err:Error) => {
        this.country = new Country(0, "NOT A COOUNTRY");
      }
    );
  }

  public saveMe() {
    this.saveCountry(this.saveName);
  }

  private saveCountry(name: string): void {
    this.countryService.save(new Country(1, name)).subscribe(
      (res: Country) => {
        this.savedCountry = res;
      },
      (err:Error) => {
        this.savedCountry = new Country(0, "NOT A COOUNTRY");
      }
    )
  }

  public update() {
    this.updateCountry(new Country(this.updateId, this.updateName));
  }

  private updateCountry(country: Country): void {
    console.log(country);
    this.countryService.update(country).subscribe(
      (res: Country) => {
        this.updatedCountry = res;
      },
      (err:Error) => {
        this.updatedCountry = new Country(0, "NOT A COOUNTRY");
      }
    )
  }

  public delete() {
    this.deleteCountry(this.deleteId);
  }

  private deleteCountry(id: number): void {
    this.countryService.delete(id).subscribe(
      (res: Country) => {
        this.deletedCountry = res;
      },
      (err:Error) => {
        this.deletedCountry = new Country(0, "NOT A COOUNTRY");
      }
    )
  }


}
