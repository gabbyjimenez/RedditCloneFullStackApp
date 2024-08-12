<template>
  <h1>Logout</h1>

  <div>Are you sure you want to log out?</div>
  <button v-on:click="logout">Yes, Log out</button>
  <button v-on:click="goHome">No</button>

</template>

<script>
export default {

  methods: {
    logout(){
      this.$store.commit("LOGOUT");
      this.$router.push("/");
    },
    goHome(){
      this.$router.push("/")
    }
    
  },
};
</script>
