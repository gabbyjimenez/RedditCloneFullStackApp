<template>
  <header>
    <router-link id="homeLink" v-bind:to="{ name: 'home' }"><img
        src="https://res.cloudinary.com/daprq6s7w/image/upload/v1722965836/foxbutton_j6yfhm.png" /></router-link>
<<<<<<< HEAD
   

=======
    <div id="searchContainer">
    <!-- <div  class="input-group rounded" id ="searchBar">
      <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search"
        aria-describedby="search-addon" />
    </div> -->
    </div>
>>>>>>> 1727b78c7e3a983880042b303848ab223a5c8ed5
    <div id="favLog">
    
    <router-link id="LoginLink" v-bind:to="{ name: 'login' }">Login |</router-link>
    <router-link id="LoginLink" v-bind:to="{ name: 'logout' }"> Logout</router-link>


  </div>
  </header>
</template>
  
<script>


export default {

}
</script>
  
<style scoped>
#headerText {

  text-decoration: underline;

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
#searchContainer{
display: flex;
width: 30%;

justify-content: center;
align-items: center;


}
#favLog{
display: flex;
flex-direction: row;

align-items: center;

}
</style>