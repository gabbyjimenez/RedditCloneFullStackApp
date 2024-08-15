<template>
    <span class="badge badge-pill badge-primary" style="color:black;background:#bdc16b;width:fit-content;float:right;margin-right:5px;">{{ category }}</span>
</template>
  
  <script>
  export default {
    props: {
      category: {
        type: String,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  /* You can add custom styles here if needed */
  </style>