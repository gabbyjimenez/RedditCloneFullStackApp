<template>
    <div id="main">

        <button v-on:click="newUserOpen = true" v-if="newUserOpen === false">Add New User</button>

        <form id="frmAddNewUser" v-on:submit.prevent="AddNewUser" v-show="newUserOpen === true">
            <div class="field">
                <label for="denName">Den Name:</label>
                <input type="text" id="denName" name="denName" v-model="newDen.denName" required />
            </div>
            <div class="field">
                <label for="lastName">categories:</label>
                <input type="text" id="categories" name="categories" v-model="newDen.categoryNames" required />
            </div>
            <div class="field">
                <label for="username">Description:</label>
                <input type="text" id="denDesc" name="denDesc" v-model="newDen.denDesc" required />
            </div>
           
            <button type="submit" class="btn save">Save User</button>
        </form>
    </div>
</template>

<script>
export default {

    newUserOpen: false,
    newDen: {
        denName: "",
        denCreatorId: "",
        denCreatorUserName: "",
        categoryNames: [],
        denDesc: ""
    },
    methods: {
   
    AddNewUser(){
     
      this.newUser.id = this.nextUserId;
      this.users.unshift(this.newUser);
      this.newUserOpen = false;
      this.clearForm();
    },
    clearForm(){
      this.newUser = {status: 'Active'};
    },
}

}
</script>

<style></style>