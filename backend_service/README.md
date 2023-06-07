### Use the following url to upload excel data ###

http://localhost:8080/api/excel/upload

Request Type: POST

### Use the following url to fetch all available addresses in the database ###

http://localhost:8080/api/excel/addresses

Request Type: GET

### Use the following url to update the coordinates for all addresses in the database ###

http://localhost:8080/api/googlemaps/addAddressCoordinates

Request Type: GET

### Use the following url to fetch the coordinates for an addresses ###

http://localhost:8080/api/googlemaps/fetchCoordinates

Request Type: POST
Request Body:
{
"address" : "277 Bedford Avenue, Brooklyn, NY 11211, USA"
}
### Use the following url to fetch the address for a coordinate ###

http://localhost:8080/api/googlemaps/fetchAddress

Request Type: POST
Request Body: 
{
"latitude" : 33.7784041,
"longitude" : -84.3451435
}

### Understanding Google's Distance Matrix ###

https://developers.google.com/maps/documentation/distance-matrix/distance-matrix
https://maps.googleapis.com/maps/api/distancematrix/json?origins=Washington%2C%20DC&destinations=New%20York%20City%2C%20NY&units=imperial&key=AIzaSyAfN9kx-j7QW3atFmPhgjnoOWREPGd8TOM


### Use the following url to fetch the list of rentals available within 20 miles ###

http://localhost:8080/api/googlemaps/fetchAvailableRentals