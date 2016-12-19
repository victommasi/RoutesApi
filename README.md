# RoutesApi
This application creates routes with multiples waypoints given through json.
The best route is created by Google Maps API and saved in NoSQL database, MongoDB.
* Java 8 
* Spring Boot
* MongoDB

### Usage: 
1. You need a Google APIKEY(It`s free) and type it inside application.properties file, like:
```sh
apikey=AIzaSyCGUhLM8pidet0JdnJehTlPozha_uehAq1
```

2. Use a rest client to communicate with the application
3. GET to /route/list retrieves the route list
4. POST to /route/create, giving json, save a new route in DB 
5. Example json:
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
