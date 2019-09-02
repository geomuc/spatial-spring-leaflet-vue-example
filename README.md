# spatial-spring-leaflet-vue-example

## Running it

Maven and npm need to be installed

Clone the git repository

```
git clone https://github.com/geomuc/spatial-spring-leaflet-vue-example.git
```

start the Spring-Boot server (should start at Port 8080)
```
cd spatial-spring-leaflet-vue-example\spatialservice
mvn spring-boot:run
```

Open a new cmd/console for the vue service

```
cd spatial-spring-leaflet-vue-example\jsclient
npm install
npm run serve
```
since 8080 is already used by the spring-service, vue will probably start at Port 8081.
Open the Browser and go to

http://localhost:8081/
