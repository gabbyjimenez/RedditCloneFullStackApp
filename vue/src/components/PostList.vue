<template>

<div class="den" v-for="post in posts" v-bind:key="post.postTitle">
    <router-link v-bind:to="{ name: 'DenView', params: { postTitle: post.postTitle } }">
      {{ post.postTitle }}  
    </router-link>
  </div>

  
</template>

<script>
export default {
  
    props: {
       posts: {
        type: Array,
       }

    },
    
    data() {
        return {
            searchFilter: ''
        }
    },
    computed: {
        filteredPosts() {
            return this.posts.filter((post) => {
                return this.searchFilter == '' ? true : post.postTitle.includes(this.searchFilter);
            });



        }
    }

}
</script>

<style>

</style>