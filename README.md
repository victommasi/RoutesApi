# RoutesApi
This application creates routes with multiples waypoints given through json.
The best route is created by Google Maps API and saved in NoSQL database, MongoDB.
* Java 8 
* Spring Boot
* MongoDB


1. Use a rest client to communicate with application
2. GET to /route/list retrieves the route list
3. POST to /route/create giving json save a new route in DB 
4. Example json:
```sh
[
	{
    	"name":"Fortaleza",
   	 	"lat":"-3.852324",
    	"lng":"-38.502451"
	},
	{
    	"name":"Salvador",
   		"lat":"-12.937787",
    	"lng":"-38.449182"
	},
	{
        "name":"Brasilia",
   	 	"lat":"-15.815882",
     	"lng":"-47.838683"
	},
	{
    	"name":"Recife",
   		"lat":"-8.052902",
        "lng":"-34.907085"
	}
]
```
