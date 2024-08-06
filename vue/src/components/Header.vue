<template>
  <header>
    <router-link id="homeLink" v-bind:to="{ name: 'home' }"><img
        src="https://res.cloudinary.com/daprq6s7w/image/upload/v1722965836/foxbutton_j6yfhm.png" /></router-link>
    
    <div id ="searchBar" class="input-group rounded">
      <input type="search" class="form-control rounded" placeholder="Search" aria-label="Search"
        aria-describedby="search-addon" />
    </div>

    <p>favorite|logout</p>

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
#searchBar{
display: flex;
width: 30%;
height: 50%;
justify-content: center;
align-items: center;

}
</style>