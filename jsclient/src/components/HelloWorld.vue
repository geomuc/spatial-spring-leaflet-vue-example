<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
    >
      <v-list dense>
        <v-list-item>
          <v-list-item-action>
            <v-icon>mdi-home</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item>
          <v-list-item-action>
            <v-icon>mdi-contact-mail</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>Contact</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      app
      color="indigo"
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Application</v-toolbar-title>
    </v-app-bar>

    <v-content>
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col class="d-flex" cols="12" sm="4">
            <v-select
              v-model= "auswahl"
              :items="items"
              label="Select Address"
              outlined
              v-on:change="setMarker()"
            ></v-select>
          </v-col>
          <v-col class="d-flex" cols="12" sm="8">
            <v-card
              class="mx-auto"
              width="100%"
              height="500px"
            >
              <v-img
                height="100%"
                width="100%"
                id="map"
              ></v-img>
            <p>Long {{longout}} :: Lat {{latout}}</p>
            </v-card>
          </v-col>
          
        </v-row>
      </v-container>
    </v-content>
    <v-footer
      color="indigo"
      app
    >
      <span class="white--text">&copy; 2019</span>
    </v-footer>
  </v-app>
</template>

<script>
 /* eslint-disable */
import L from 'leaflet';
import "leaflet/dist/leaflet.css";
  export default {
    props: {
      source: String,
    },
    data: () => ({
      auswahl: "",
      drawer: null,
      longitude: 11.0,
      latitude: 48.0,
      longout: 11.0,
      latout: 48.0,
      items: [],
      addresses: [],
    }),
  mounted() {
    this.initMap();
  },
  created(){
    this.fetchData();
  },
  methods: {
    fetchData(){
      fetch('http://localhost:8080/address')
        .then(function(response) {
          return response.json();
        })
        .then(data => {
      this.addresses=data;
      console.log(data);
      this.getAdrString();
      })
      .catch(function(error) {
          console.error(error);
      });;

    },

    getAdrString(){
      var adrString = [];
      this.addresses.forEach(function(element) {
        adrString.push(element.number+", "+element.street);
      });
      this.items=adrString;
    },

    setMarker(){  
      var res = this.auswahl.split(",")[0];
      var address = "";
      this.addresses.forEach(function(element) {
          if (element.number==res){
            address=element;
          };
      });
      var long = address.location.coordinates[0];
      var lat =  address.location.coordinates[1];
      console.log(long);
      console.log(lat);
      this.circle = L.circle([lat,long], {
        color: 'red',
        fillColor: '#f03',
        fillOpacity: 0.5,
        radius: 5
      }).addTo(this.map);
      this.map.setView([lat,long], 17);
    },

    initMap() {
      this.map = L.map('map').setView([this.latitude, this.longitude], 12);
      this.tileLayer = L.tileLayer(
        'https://cartodb-basemaps-{s}.global.ssl.fastly.net/rastertiles/voyager/{z}/{x}/{y}.png',
        {
          maxZoom: 18,
          attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>, &copy; <a href="https://carto.com/attribution">CARTO</a>',
        }
      );     
      this.tileLayer.addTo(this.map);
      this.map.on('move', this.onMapMove);
    },

    onMapMove: function(e) {  
      this.longout=this.map.getCenter().lng;
      this.latout=this.map.getCenter().lat;
    },
  },
  }
</script>

<style scoped>
      .map { height: "100%" ;
            width: "100%"}
</style>
