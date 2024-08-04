<template>
  <aside>
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">About</a></li>
      <li><a href="#">Services</a></li>
      <li><a href="#">Portfolio</a></li>
      <li><a href="#">Contact Us</a></li>
    </ul>


  </aside>
</template>

<script>
export default {

}
</script>

<style scoped>
#siteTitle {
  text-align: left;
}

a {
  margin-left: 5px;
}

img {
  width: 30%;
  height: auto;
}

aside {
  display: flex;
  flex-grow: 1;
  position: fixed;
  /* left: 0; 
    top: 0; 
    padding-top: 20px;  */
  border-right: 1px solid rgba(0, 173, 238, 0.4);
  width: 200px;
  height: 100vh;
}

</style>