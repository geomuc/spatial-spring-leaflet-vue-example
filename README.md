# spatial-spring-leaflet-vue-example

This is a very simple example of a spatial REST Service with a Vue Client. It uses:
* For the Backend
   * a Spring-Boot Service
   * The Hibernate-Spatial Extension
   * JTS (defines the Geometries)
   * geodb (a spatial extention of the H2 database)
   * the package jackson-datatype-jts for serializing the geometries
* Frontend
   * Javascript, Vue
   * Vuetify (Material Design), I used the "baseline" layout from https://vuetifyjs.com/en/getting-started/pre-made-layouts 
   * Leaflet 
  


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
