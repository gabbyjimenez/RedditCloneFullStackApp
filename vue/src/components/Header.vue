<template>
  <header>
    <div id="homeLink">
      <router-link v-bind:to="{ name: 'home' }"><img
          src="https://res.cloudinary.com/daprq6s7w/image/upload/v1722957878/foxbutton_uilkmi.png" /></router-link>
    </div>
    <div id="headerText">
      <p >Header Name</p>
    </div>
    <div id="favLog">
      <p>favorite</p>
      <p>|</p>
      <p>logout</p>
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
#headerText {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 1%;



}
#favLog {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 1%;



}
</style>