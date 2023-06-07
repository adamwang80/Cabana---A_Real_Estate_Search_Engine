import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AutocompleteComponent } from './google-places.component';
import { UserPrefPageComponent } from './user-pref-page/user-pref-page.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { AppRoutingModule } from './/app-routing.module';
import { ApartmentDetailsComponent } from './apartment-details/apartment-details.component';

@NgModule({
  declarations: [
    AppComponent,
    AutocompleteComponent,
    UserPrefPageComponent,
    SearchResultsComponent,
    ApartmentDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
