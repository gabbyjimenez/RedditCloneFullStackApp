<template>
  <header>
    <router-link id="homeLink" v-bind:to="{ name: 'home' }">
      <img src="https://res.cloudinary.com/daprq6s7w/image/upload/v1722965836/foxbutton_j6yfhm.png" alt="Home" />
      <span id="headerText">Foxtrot Communities</span>
    </router-link>

    <div id="favLog">
      <router-link v-if="this.$store.state.user.username != ''" v-bind:to="{ name: 'logout' }" class="auth-link">
        Logout
      </router-link>
      <router-link v-else v-bind:to="{ name: 'login' }" class="auth-link">
        Login
      </router-link>
    </div>
  </header>
</template>

<script>
export default {}
</script>

<style scoped>
/* Apply a professional font */
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');

header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 1rem;
  font-size: large;
  background: linear-gradient(90deg, rgba(97, 155, 138,1) 5%, rgba(161, 193, 129,1) 41%, rgba(161, 193, 129,1) 67%, rgba(97, 155, 138,1) 100%, rgba(97, 155, 138,1) 100%);
}

#homeLink {
  display: flex;
  align-items: center;
  gap: 1rem; /* Space between image and text */
  text-decoration: none; /* Ensure the router-link itself has no underline */
}

img {
  height: 40px; /* Adjust height as needed */
}

#headerText {
  font-family: 'Roboto', sans-serif; /* Professional font */
  font-weight: 700; /* Bold text */
  color: #fff; /* White text for contrast */
  font-size: 1.5rem; /* Adjust size as needed */
}

#favLog {
  display: flex;
  align-items: center;
}

.auth-link {
  color: #fff;
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
</style>
