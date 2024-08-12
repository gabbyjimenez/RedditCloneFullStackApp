<template>
    <div class="w3-third">
      <div class="w3-card w3-container" style="min-height:460px">
        <h3>{{ title }}</h3><br>
        <i :class="['fa', icon, 'w3-margin-bottom', 'w3-text-theme']" style="font-size:120px"></i>
        <p v-for="(benefit, index) in benefits" :key="index">{{ benefit }}</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      title: {
        type: String,
        required: true
      },
      icon: {
        type: String,
        required: true
      },
      benefits: {
        type: Array,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  /* You can add custom styles here if needed */
  </style>