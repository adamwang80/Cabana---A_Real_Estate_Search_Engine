class RentalResponse {
    apartmentName: string;
    latitude: number;
    longitude: number;
    address: string;
    bedroomType: string;
    price: number;
    distance: string;
    duration: string;
    imageLink: string;
}
class RentalRequest {
    destination: string;
    commuteType: string;
    bedroomType: string;
    latitude: number;
    longitude: number;
    searchRadius: number;
}
export class RentalData {
    rentalRequest: RentalRequest;
    rentalResponse: RentalResponse[];
}

     
