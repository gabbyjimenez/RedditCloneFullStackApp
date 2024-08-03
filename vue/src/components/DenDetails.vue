<template>
    <div>
        <h4>Explore Dens</h4>
        <div class="den" v-for="den in dens" v-bind:key="den.Id">
           <p>{{ den.denId }} : {{ den.denName }} - {{ den.denCreatorId }}</p>
        </div>
    </div>
  </template>
  
  <script>
  
  import DenService from '../services/DenService.js';
  
  export default {
  
      data() {
          return {
              dens: [
                

              ]
          }
      },
      created() {
  
          DenService.getDens().then(response => {
              this.dens = response.data;
          }).catch(error => {
              console.log('You are out of luck')
          }) 
  
      }
  
  }
  </script>
  
  <style>
.den {
  border-bottom: 1px solid #f2f2f2;
}
.den:last-child {
  border-bottom: 0px;
}
.den a {
  display: block;
  padding: 10px 20px;
}
.den a:link, .den a:visited {
  color: black;
  text-decoration: none;
}
.den a:hover {
  background-color: #f2f2f2;
  cursor: pointer;
}



</style>