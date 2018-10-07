import { Component, OnInit, Input } from '@angular/core';
import { CountryService } from '../services/CountryService';
import { Country } from '../domain/Country';
import { MatDialog } from '@angular/material';
import { UpdateCountryDialogComponent } from '../update-country-dialog/update-country-dialog.component';
import { Router } from '@angular/router';
import { AddCountryDialogComponent } from '../add-country-dialog/add-country-dialog.component';

@Component({
  selector: 'app-country-modifier',
  templateUrl: './country-modifier.component.html',
  styleUrls: ['./country-modifier.component.css']
})
export class CountryModifierComponent implements OnInit {

  countries: Country[];

  constructor(
    private countryService: CountryService,
    public dialog: MatDialog,
    private router: Router
  ) { }

  ngOnInit() { 
    this.getAll();
  }

  openUpdateDialog(country: Country): void {
    const dialogRef = this.dialog.open(UpdateCountryDialogComponent, {
      data: {name: country.name}
    });

    dialogRef.afterClosed().subscribe((name: string) => {
      if (name && country.name !== name) {
        this.update(new Country(country.id, name));
      }
    });
  }

  openAddDialog(): void {
    const dialogRef = this.dialog.open(AddCountryDialogComponent);

    dialogRef.afterClosed().subscribe((name: string) => {
      if (name) {
        this.add(name);
      }
    });
  }

  private getAll(): void {
    this.countryService.getAll().subscribe(
      (res: Country[]) => {
        this.countries = res;
      }
    );
  }

  private add(name: string): void {
    this.countryService.add(new Country(1, name)).subscribe(
      () => {
        this.getAll();
      },
      (err:Error) => {
        this.router.navigate(['/not_found']);
      }
    )
  }

  private update(country: Country): void {
    console.log(country);
    this.countryService.update(country).subscribe(
      () => {
        this.getAll();
      },
      (err:Error) => {
        this.router.navigate(['/not_found']);
      }
    )
  }

  private delete(id: number): void {
    this.countryService.remove(id).subscribe(
      () => {
        this.getAll();
      },
      (err:Error) => {
        this.router.navigate(['/not_found']);
      }
    )
  }


}
