<template>  
    <div id="main">
        <div id="button">
            <div id="button" class="card" style="width: 50%;" v-on:click="denOpen = true">
                <div class="card-body">
                    <p class="card-title">+</p>
                </div>
            </div>
        </div>

        <form id="AddNewDen" v-on:submit.prevent="AddNewDen" v-show="denOpen == true">
            <div class="field">
                <label for="denName">Den Name:</label>
                <input type="text" id="denName" name="denName" v-model="newDen.denName" required />
            </div>
            <div class="field">

                <tr v-for="category in categories" v-bind:key="category.id">
                    <td>
                        <input type="checkbox" v-bind:id="category.categoryName" v-bind:value="category.categoryName"
                            v-model="newDen.categoryNames" />
                    </td>
                    <td>{{ category.categoryName }}</td>
                </tr>

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
                denCreatorUserName: "",
                categoryNames: [],
                denDesc: ""
            },
            denOpen: false,
            categories: []

        }

    },

    methods: {

        AddNewDen() {
            console.log(this.newDen)

            DenService.addDen(this.newDen).then((response) => {
                this.getDens();
                console.log(response.data);
                this.clearForm()
                console.log(this.$store.state.dens)

            }).catch((error) => {
                console.log(error)

            });
        },

        clearForm() {
            this.newDen = {
                denName: "",
                denCreatorId: 1,
                denCreatorUserName: "user",
                categoryNames: [],
                denDesc: ""
            };

        },

        getCategories() {
            DenService.getCategories().then(response => {
                this.categories = response.data
            }).catch(error => {
               console.log('You are out of luck')

            })
        },
        getDens() {
      DenService.getDens().then(response => {
        this.$store.state.dens = response.data
      }).catch(error => {
        console.log('You are out of luck')
      })
    },
    },

    created() {
        this.getCategories();
    }
}
</script>

<style>
#button {
    display: flex;
    flex-direction: row;
    justify-content: center;
    margin: 1%;


}
</style>