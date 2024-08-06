<template>
    <div id="main">


        <button v-on:click="denOpen = true">+</button>


        <form id="AddNewDen" v-on:submit.prevent="AddNewDen" v-show="denOpen == true">
            <div class="field">
                <label for="denName">Den Name:</label>
                <input type="text" id="denName" name="denName" v-model="newDen.denName" required />
            </div>
            <div class="field">
                <label for="lastName">Categories:</label>
                <input type="text" id="categories" name="categories" v-model="newDen.categoryNames" required />
            </div>
            <div class="field">
                <label for="username">Description:</label>
                <input type="text" id="denDesc" name="denDesc" v-model="newDen.denDesc" required />
            </div>

            <button type="submit" class="btn save" v-on:click="denOpen = false">Save Den</button>
        </form>
    </div>
</template>

<script>
import DenService from '../services/DenService.js';




export default {

    data() {
        return {
            newDen: {
                denName: "",
                denCreatorId: 1,
                denCreatorUserName: "user",
                categoryNames: [
                    "Sports"
                ],
                denDesc: "WE LOVE PLANse AHOY"
            }, denOpen: false
        }
    },

    methods: {

        AddNewDen() {
            console.log(this.newDen)

            DenService.addDen(this.newDen).then((response) => {
                console.log(response.data);
                this.clearForm()
                console.log(this.$store.state.dens)

            }).catch((error) => {
                console.log(error)
            });
        },
        clearForm() {
            this.newDen = {};
        },

    }
}
</script>

<style></style>