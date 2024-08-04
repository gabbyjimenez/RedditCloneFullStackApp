<template>
    <header class="flex">
        <h1>{{post.postTitle}}</h1>
  </header>
  <div class="created">

<label>Created By:</label>
<span>{{ post.creatorUsername }} </span>
</div>
<div class="postBody">
    <div class="wrap">
        {{post.postDesc}}
</div>
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