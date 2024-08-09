<template>  
    <div id="main">
        <div id="button">
            <div id="button" class="card" style="width: 50%;" v-on:click="(denOpen = !denOpen); clearForm();" >
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
<<<<<<< HEAD
           
=======
        
>>>>>>> f8f92fd57d0262fce7a37815567e07c684e0ae17
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
                denCreatorId: this.$store.state.user.id,
                denCreatorUserName: this.$store.state.user.username,
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
                denCreatorId: this.$store.state.user.id,
                denCreatorUserName: this.$store.state.user.username,
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
    width: 70%;
    justify-content: center;
    margin: auto;
    margin-top: 1%;
    margin-bottom: 1%;
    font-weight: bolder;

}
</style>