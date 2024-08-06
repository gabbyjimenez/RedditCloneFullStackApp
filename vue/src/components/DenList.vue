<template>
    <div class="den" v-for="den in this.$store.state.dens" v-bind:key="den.id">
    <router-link v-bind:to="{ name: 'DenDetails', params: { denId: den.id } }">
      {{ den.title }} 
    </router-link>
  </div>
  
</template>

<script>
export default {

}
</script>

<style>

</style>