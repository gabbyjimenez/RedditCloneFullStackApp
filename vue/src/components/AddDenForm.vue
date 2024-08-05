<template>
    <div id="main">
        <p>FORM</p>

        <button >Add New User</button>

        
        <form id="AddNewDen" v-on:submit.prevent="AddNewDen">
            <div class="field" v-show="newDenOpen === true">
                <label for="denName">Den Name:</label>
                <input type="text" id="denName" name="denName" v-model="newDen.denName" required />
            </div>
            <div class="field" v-show="newDenOpen === true">
                <label for="lastName">categories:</label>
                <input type="text" id="categories" name="categories" v-model="newDen.categoryNames" required />
            </div>
            <div class="field" v-show="newDenOpen === true">
                <label for="username">Description:</label>
                <input type="text" id="denDesc" name="denDesc" v-model="newDen.denDesc" required />
            </div>
           
            <button type="submit" class="btn save" v-show="newUserOpen === true">Save Den</button>
        </form>
    </div>
</template>

<script>
export default {

    data(){
        return{
    newDen: {
        denName: "",
        denCreatorId: "",
        denCreatorUserName: "",
        categoryNames: [],
        denDesc: ""
    }}},
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