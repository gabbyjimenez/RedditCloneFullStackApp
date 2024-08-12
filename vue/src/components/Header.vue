<template>
  <header>
    <div  id="homeLink">
    <router-link v-bind:to="{ name: 'home' }"><img
        src="https://res.cloudinary.com/daprq6s7w/image/upload/v1723474352/foxtrot_logo_fin_noText_zwlfxu.svg" /></router-link>
 </div>
<!--   
    
    <router-link id="LoginLink" v-bind:to="{ name: 'login' }">Login |</router-link>
    <router-link id="LoginLink" v-bind:to="{ name: 'logout' }"> Logout</router-link> -->


    <div id="favLog">
      <!-- <router-link v-if="this.$store.state.user.username != null" v-bind:to="{ name: 'logout' }" class="auth-link">
        Logout
      </router-link> -->

<!-- modal test -->
<p v-if="this.$store.state.user.username != null" v-bind:to="{ name: 'logout' }" class="auth-link" data-toggle="modal" data-target="#logoutModal">
        Logout
</p>


<!-- modal test -->

      <router-link v-else v-bind:to="{ name: 'login' }" class="auth-link">
        Login
      </router-link>
    </div>
  </header>

  <!-- test modal -->
 <!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" Segoe-labelledby="logoutModalLabel" Segoe-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="logoutModalLabel">Are you sure?</h5>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="logout" data-dismiss="modal">Continue</button>
        <button type="button" class="btn btn-primary"  data-dismiss="modal">Cancel</button>
      </div>
    </div>
  </div>
</div>

  <!-- test modal -->
</template>

<script>
export default {


  methods: {

    logout(){
      this.$store.commit("LOGOUT");
      this.$router.push("/dens");
    },
    goHome(){
      this.$router.push("/")
    }
    
  },
  }
  
</script>

<style scoped>
/* Apply a professional font */
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

header {
  display: flex;
  justify-content: space-between;
  font-family: 'Segoe UI';
  align-items: center;
  padding: 0 1rem;
  font-size: large;
  background: linear-gradient(90deg, rgba(252,202,70,1) 20%, rgba(201,197,103,1) 40%, rgba(97,155,138,1) 80%);
}

#homeLink {
  display: flex;
  align-items: center;
  
  height: 8%;



}
#homeLink:hover {
background-color: rgba(255, 255, 255, 0.15);
border-radius: 10%;



}

img {
  height: auto;
  max-height: 65px;
}

#headerText {
  font-family: 'Roboto', sans-serif; /* Professional font */
  font-weight: 700; /* Bold text */
  color: rgba(252,202,70,1); /* White text for contrast */
  font-size: 1.5rem; /* Adjust size as needed */
}

#favLog {
  display: flex;
  align-items: center;
}

.auth-link {
  color:rgba(252,202,70,1);
  text-decoration: none; /* Remove underline from links */
  margin-left: 1rem;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: background-color 0.3s, color 0.3s;
}

.auth-link:hover {
  background-color: rgba(255, 255, 255, 0.1);
  color: #d3d3d3;
}

.auth-link:active {
  background-color: rgba(255, 255, 255, 0.2);
}
#LoginLink{
  color: white;
}
</style>
