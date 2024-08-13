<template>
  <div>
    <div class="favoritesContainer" v-for="favorite in favorites" v-bind:key="favorite.denName">
    <a @click="$router.push({ name: 'den', params: { denName: favorite.denName } })"> {{ favorite.denName }}</a>
    </div>
  </div>
</template>

<script>
import DenService from "../services/DenService.js";

export default {
  data() {
    return {
      favorites: [],
    };
  },

  methods: {
    getFavorites() {
      DenService.getFavorites()
        .then((response) => {
          this.favorites = response.data;
        })
        .catch((error) => {
          console.log("You are out of luck");
        });
    },
  },

  created() {
    this.getFavorites();
  }
};
</script>
<style>






</style>