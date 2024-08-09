<template>
  <header>
    <router-link id="homeLink" v-bind:to="{ name: 'home' }"><img
        src="https://res.cloudinary.com/daprq6s7w/image/upload/v1722965836/foxbutton_j6yfhm.png" /></router-link>
 
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
  background: linear-gradient(90deg, rgba(137,220,255,1) 5%, rgba(0,167,236,1) 41%, rgba(20,159,231,1) 67%, rgba(120,213,253,1) 100%, rgba(9,170,237,1) 100%);
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
#LoginLink{
color: aliceblue;

}
</style>