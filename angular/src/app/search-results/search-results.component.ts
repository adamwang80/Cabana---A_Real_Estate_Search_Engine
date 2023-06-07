import { Component, OnInit, ViewEncapsulation, } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserPrefPageService } from "../user-pref-page.service";

declare var google: any;

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class SearchResultsComponent implements OnInit
{
  map: any;
  apartments;

  showApartmentDetails: boolean = false;
  selectedApartment;

  constructor(private route: ActivatedRoute, private userPrefService: UserPrefPageService, private router: Router) { }

  ngOnInit() 
  {
    this.apartments = this.userPrefService.getSearchResults();
    // console.log(this.apartments);

    this.initMap();
    this.markApartments();
  }

  initMap() 
  {
    this.map = new google.maps.Map(document.getElementById('map'), {
      // center: {lat: this.apartments.rentalRequest.latitude, lng: this.apartments.rentalRequest.longitude},
      zoom: 12,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    var centermarkerIcon = {
      url: 'https://cdn.iconscout.com/icon/premium/png-256-thumb/search-location-1985772-1678677.png',
      scaledSize: new google.maps.Size(64, 64)
    };

    var center= {lat: this.apartments.rentalRequest.latitude, lng: this.apartments.rentalRequest.longitude}

    var centerMarker = new google.maps.Marker({
      position: center,
      map: this.map,
      icon: centermarkerIcon,
      title: this.apartments.rentalRequest.destination
    });

    this.map.setCenter(center);
  }

  markApartments()
  {
    if(this.apartments.rentalResponse.length==0)
    {
      console.log("emptyResults");
      var card = document.createElement('div');
      card.className = 'emptySearch';
      card.innerHTML = '<h1>No apartments found! :(</h1>'
      document.getElementById('apartment-list').appendChild(card);
    }
    
    this.apartments.rentalResponse.forEach((apartment) =>
    {
      // var card = document.createElement('div');
      // card.className = 'card';
      // card.innerHTML = '<img src="' + apartment.imageLink + '"><div><h2>' + apartment.apartmentName + '</h2><p>' + apartment.unit + '<br> $' + apartment.price + '</p><p class="distance">' + apartment.distance + ', ' + apartment.duration + ' commute</p></div>';
      // document.getElementById('apartment-list').appendChild(card);

      var marker = new google.maps.Marker({
          position: {lat: apartment.latitude, lng: apartment.longitude},
          map: this.map,
          title: apartment.apartmentName
          // icon: 'https://maps.google.com/mapfiles/ms/icons/red-dot.png'
      });

      var content = '<div><h2>' + apartment.apartmentName + '</h2><p>' + '$' + apartment.price + '</p></div>' + '</p><p class="distance">' + apartment.distance + ', ' + apartment.duration + ' </p></div>';
      var infowindow = new google.maps.InfoWindow({
          content: content
      });
  
      marker.addListener('mouseover', function() {
          infowindow.open(this.map, marker);
      });

      marker.addListener('mouseout', function() {
        infowindow.close(this.map, marker);
      });
    });
  }

  toggleApartmentDetails() 
  {
    this.showApartmentDetails = !this.showApartmentDetails;
  }

  updateSelected(apartment)
  {
    this.selectedApartment = apartment;
    this.toggleApartmentDetails();
    // console.log(this.selectedApartment);
  }

  home()
  {
    this.router.navigate(['/']);
  }
}
