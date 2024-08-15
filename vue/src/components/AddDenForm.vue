<template>
  <div id="main" >
      <div id="button" v-show="this.$store.state.user.id != 0">
          <!-- Adjust the button styling here -->
          <div id="addButton" class="card expandable-card" v-on:click="(denOpen = !denOpen); clearForm();">
              <div class="card-body">
                  <p class="card-title">+ Creat a Group</p>
              </div>
          </div>
      </div>

      <form id="AddNewDen" v-on:submit.prevent="AddNewDen" v-show="denOpen">
          <div class="form-group">
              <label for="denName">Den Name:</label>
              <input type="text" id="denName" name="denName" v-model="newDen.denName" required />
          </div>
          <div class="form-group">
              <label>Categories:</label>
              <div class="categories-list">
                  <div v-for="category in categories" :key="category.id" class="category-item">
                      <input type="checkbox" :id="category.categoryName" :value="category.categoryName" v-model="newDen.categoryNames" />
                      <label :for="category.categoryName">{{ category.categoryName }}</label>
                  </div>
              </div>
          </div>
          <div class="form-group">
              <label for="denDesc">Description:</label>
              <input type="text" id="denDesc" name="denDesc" v-model="newDen.denDesc" required />
          </div>
          <button type="submit" class="btn save">Save Den</button>
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
              denDesc: "",
              isFavorited: false
          },
          denOpen: false,
          categories: []
      }
  },
  methods: {
      AddNewDen() {
          console.log(this.newDen);
          DenService.addDen(this.newDen).then((response) => {
              this.getDens();
              console.log(response.data);
              this.clearForm();
              console.log(this.$store.state.dens);
          }).catch((error) => {
              console.log(error);
          });
      },
      clearForm() {
          this.newDen = {
              denName: "",
              denCreatorId: this.$store.state.user.id,
              denCreatorUserName: this.$store.state.user.username,
              categoryNames: [],
              denDesc: "",
              isFavorited: false
          };
      },
      getCategories() {
          DenService.getCategories().then(response => {
              this.categories = response.data;
          }).catch(error => {
              console.log('You are out of luck');
          });
      },
      getDens() {
          DenService.getDens().then(response => {
              this.$store.state.dens = response.data;
          }).catch(error => {
              console.log('You are out of luck');
          });
      }
  },
  created() {
      this.getCategories();
  }
}
</script>

<style scoped>
/* Overall styling for the button and form container */
#main {
display: flex;
flex-direction: column;
align-items: center;
padding: 20px;
}

/* Card styling for the expandable button */
.expandable-card {
max-width: 100%;
max-height: 65%;
background-color: transparent; /* No solid background */
border: 2px solid rgba(97, 155, 138,1); /* Colored outline */
border-radius: 8px;
box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
cursor: pointer;
text-align: center;
color: rgba(97, 155, 138,1); /* Matching text color with outline */
}

.expandable-card .card-body {
padding: 20px;
}

.expandable-card .card-title {
font-size: 24px; /* Adjust font size for better fit */
margin: 0;
}

/* Form styling */
#AddNewDen {
width: 50%;
max-width: 400px;
background-color: #fff;
border-radius: 8px;
box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
padding: 20px;
margin-top: 20px;
}

.form-group {
margin-bottom: 15px;
}

.form-group label {
display: block;
font-weight: bold;
margin-bottom: 5px;
}

.form-group input[type="text"] {
width: 100%;
padding: 10px;
border: 1px solid #ddd;
border-radius: 4px;
}

.categories-list {
display: flex;
flex-direction: column;
}

.category-item {
display: flex;
align-items: center;
margin-bottom: 5px;
}

.category-item input[type="checkbox"] {
margin-right: 10px;
}

/* Save button styling */
.btn.save {
width: 100%;
padding: 10px;
border: 2px solid #007bff; /* Outline with primary color */
border-radius: 4px;
background-color: transparent; /* Transparent background */
color: #007bff; /* Primary color text */
font-size: 16px;
cursor: pointer;
text-align: center;
}

.btn.save:hover {
background-color: #f0f0f0; /* Light background on hover for contrast */
color: #0056b3; /* Darker shade for text on hover */
}
</style>
