<template>
    <p>blorg</p>

    <div>
        <label for="">Search: </label>
        <input type="text" name="denName" v-model="searchFilter">
    </div>

    <div class="den" v-for="post in filteredPosts" v-bind:key="post.postTitle" >
        <div v-on:click="$router.push({ name: 'post', params: { postId: post.postId } })" v-bind="post">
            <label>{{ post.postTitle }} : {{ post.creatorUsername }}</label>
            <p>Desc</p>
            <p>{{ post.postDesc }}</p>


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

<style scoped>
.den {
    border-bottom: 1px solid #f2f2f2;
    display: block;
    padding: 10px 20px;
    background-color: #f2f2f2;
    cursor: pointer;
}

</style>