<template>
    <div class="topic" v-for="den in dens" v-bind:key="den.id">
    <router-link v-bind:to="{ name: 'TopicDetailsView', params: { denId: den.id } }">
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