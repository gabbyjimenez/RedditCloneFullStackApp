<template>
    <header>
      <router-link id="homeLink" v-bind:to="{ name: 'home' }"><img src="https://res.cloudinary.com/daprq6s7w/image/upload/v1722957878/foxbutton_uilkmi.png" /></router-link>
        <p id="headerText">Header Name</p>
       
        <p>favorite|logout</p>
        
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