import { Component, OnInit, ViewChild, AfterViewInit, Input} from '@angular/core';
import { UserPrefPageService } from "../user-pref-page.service";

declare var google: any;

@Component({
  selector: 'app-apartment-details',
  templateUrl: './apartment-details.component.html',
  styleUrls: ['./apartment-details.component.css']
})
export class ApartmentDetailsComponent implements OnInit, AfterViewInit
{
  @ViewChild('route') route;
  @Input() selectedApartment;

  apartments;

  directionsService: any;
  directionsRenderer: any;

  constructor(private userPrefService: UserPrefPageService) { }

  ngOnInit() 
  {
    this.apartments = this.userPrefService.getSearchResults();
  }

  ngAfterViewInit() 
  {
    this.initMapRoute();
  }


  initMapRoute()
  {
    this.directionsService = new google.maps.DirectionsService();
    this.directionsRenderer = new google.maps.DirectionsRenderer();

    const mapRoute = new google.maps.Map(document.getElementById('route'),
    {
      zoom: 12,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    this.directionsRenderer.setMap(mapRoute);

    const src = new google.maps.LatLng(this.selectedApartment.latitude, this.selectedApartment.longitude)
    const dest = new google.maps.LatLng(this.apartments.rentalRequest.latitude, this.apartments.rentalRequest.longitude)

    Promise.all([src, dest]).then((locations) => {
      this.displayRoute(locations[0], locations[1]);
    });
  }

  displayRoute(start: google.maps.LatLng, end: google.maps.LatLng) 
  {
    const request = {
      origin: start,
      destination: end,
      travelMode: this.apartments.rentalRequest.commuteType
    };

    this.directionsService.route(request, (result, status) => {
      if (status === 'OK') 
      {
        this.directionsRenderer.setDirections(result);

        const originMarker = new google.maps.Marker({
          position: start,
          map: this.route,
          title: this.selectedApartment.apartmentName
        });

        const destinationMarker = new google.maps.Marker({
          position: end,
          map: this.route,
          title: this.apartments.rentalRequest.destination
          });
        
        originMarker.addListener('mouseover', () => {
          const infoWindow = new google.maps.InfoWindow({
            content: this.selectedApartment.apartmentName
          });
          infoWindow.open(this.route, originMarker);
        });

        originMarker.addListener('mouseout', () => {
          const infoWindow = new google.maps.InfoWindow({
            content: this.selectedApartment.apartmentName
          });
          infoWindow.open(this.route, originMarker);
        });

        destinationMarker.addListener('mouseover', () => {
          const infoWindow = new google.maps.InfoWindow({
            content: this.apartments.rentalRequest.destination
          });
          infoWindow.open(this.route, destinationMarker);
        });

        destinationMarker.addListener('mouseout', () => {
          const infoWindow = new google.maps.InfoWindow({
            content: this.apartments.rentalRequest.destination
          });
          infoWindow.open(this.route, destinationMarker);
        });
      }
    });
  }
}
