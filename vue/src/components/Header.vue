<template>
    <header>
      <router-link id="homeLink" v-bind:to="{ name: 'home' }">Home</router-link>
        <p id="headerText">Header Name</p>
        <p>logout</p>
        
    </header>
  </template>
  
  <script>


  export default {

}
  </script>
  
  <style scoped>
  #headerText {
    
    text-decoration:underline;

  }
  header {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    
    font-size: large;
    background-color: lightblue;
    text-align: center;
  }
  #homeLink {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 1%;



  }
 
  
  </style>