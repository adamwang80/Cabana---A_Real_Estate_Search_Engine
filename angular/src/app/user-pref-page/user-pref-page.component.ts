import { Component, OnInit, NgZone } from "@angular/core";
import { UserPrefPageService } from "../user-pref-page.service";
import { UserPrefData } from "./user-pref-data.model";
import { Router } from "@angular/router";

@Component({
  selector: "app-user-pref-page",
  templateUrl: "./user-pref-page.component.html",
  styleUrls: ["./user-pref-page.component.css"],
})
export class UserPrefPageComponent implements OnInit {
  address: Object;
  establishmentAddress: Object;

  formattedAddress: string;
  formattedEstablishmentAddress: string;

  phone: string;

  poiList = [{ address: "" }];
  // { type: "geocode", address: "" }
  addressType: string = "geocode";
  commuteType: string = "DRIVING";
  bedroomType: string = "studio";
  searchRadius: number = 1;
  buttonDisabled: boolean = true;

  constructor(
    public zone: NgZone,
    private userPrefService: UserPrefPageService,
    private router: Router
  ) {}

  ngOnInit(): void {
  }

  getAddress(place: object, index: number) {
    console.log(place);
    this.address = place["formatted_address"];
    this.phone = this.getPhone(place);
    this.poiList[index].address = place["formatted_address"];
    this.formattedAddress = place["formatted_address"];
    if (this.formattedAddress === "") this.buttonDisabled = true;
    else this.buttonDisabled = false;
    console.log(this.buttonDisabled);
    this.zone.run(() => {
      this.poiList[index].address = place["formatted_address"];
      this.formattedAddress = place["formatted_address"];
    });
    console.log(this.poiList);
    console.log(this.poiList[index].address);
    console.log(index);
  }

  getEstablishmentAddress(place: object, index: number) {
    this.establishmentAddress = place["formatted_address"];
    this.phone = this.getPhone(place);
    this.poiList[index].address = place["formatted_address"];
    this.formattedEstablishmentAddress = place["formatted_address"];
    if (this.formattedAddress === "") this.buttonDisabled = true;
    else this.buttonDisabled = false;
    console.log(this.buttonDisabled);
    this.zone.run(() => {
      this.poiList[index].address = place["formatted_address"];
      this.formattedEstablishmentAddress = place["formatted_address"];
      this.phone = place["formatted_phone_number"];
    });
    console.log(this.poiList);
    console.log(this.poiList[index].address);
    console.log(index);
  }

  getAddrComponent(place, componentTemplate) {
    let result;

    for (let i = 0; i < place.address_components.length; i++) {
      const addressType = place.address_components[i].types[0];
      if (componentTemplate[addressType]) {
        result = place.address_components[i][componentTemplate[addressType]];
        return result;
      }
    }
    return;
  }

  getStreetNumber(place) {
    const COMPONENT_TEMPLATE = { street_number: "short_name" },
      streetNumber = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return streetNumber;
  }

  getStreet(place) {
    const COMPONENT_TEMPLATE = { route: "long_name" },
      street = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return street;
  }

  getCity(place) {
    const COMPONENT_TEMPLATE = { locality: "long_name" },
      city = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return city;
  }

  getState(place) {
    const COMPONENT_TEMPLATE = { administrative_area_level_1: "short_name" },
      state = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return state;
  }

  getDistrict(place) {
    const COMPONENT_TEMPLATE = { administrative_area_level_2: "short_name" },
      state = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return state;
  }

  getCountryShort(place) {
    const COMPONENT_TEMPLATE = { country: "short_name" },
      countryShort = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return countryShort;
  }

  getCountry(place) {
    const COMPONENT_TEMPLATE = { country: "long_name" },
      country = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return country;
  }

  getPostCode(place) {
    const COMPONENT_TEMPLATE = { postal_code: "long_name" },
      postCode = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return postCode;
  }

  getPhone(place) {
    const COMPONENT_TEMPLATE = {
        formatted_phone_number: "formatted_phone_number",
      },
      phone = this.getAddrComponent(place, COMPONENT_TEMPLATE);
    return phone;
  }

  addPoI() {
    this.poiList.push({ address: "" });
    console.log(this.poiList);
    this.buttonDisabled = true;
  }

  setAddressType(event: any) {
    this.addressType = event.target.value;
    console.log(this.addressType);
    // this.addressType = type;
  }

  setCommuteType(event: any) {
    this.commuteType = event.target.value;
    console.log(this.commuteType);
    // this.addressType = type;
  }

  setNumBr(event: any) {
    this.bedroomType = event.target.value;
    console.log(this.bedroomType);
  }

  setSearchRadius(event: any) {
    this.searchRadius = event.target.value;
    console.log(this.searchRadius);
  }

  postUserPreferences() {
    this.showLoader();
    var userPreferences: UserPrefData = {
      commuteType: this.commuteType,
      destination: this.poiList[0]["address"],
      bedroomType: this.bedroomType,
      searchRadius: this.searchRadius,
    };
    console.log(userPreferences);
    this.userPrefService
      .getRentalData(userPreferences)
      .subscribe((rentalData) => {
        if (rentalData) {
          console.log("Received rental data: ", rentalData);
          this.hideLoader();
          this.router.navigate(["/search-results", rentalData]);
        }
      });
  }
  // Sending only one address to the backend

  hideLoader() {
    document.getElementById("loading-page").style.display = "none";
    document.getElementById("loading").style.display = "none";
  }

  showLoader() {
    document.getElementById("loading-page").style.display = "block";
    document.getElementById("loading").style.display = "block";
  }

  removeAddress(event: any) {
    console.log(event.target.value);
    this.poiList.splice(event.target.value, 1);
    console.log(this.poiList);
  }
}
