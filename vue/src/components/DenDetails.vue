<template>
  <div class="container">
    

    <div id="searchBar">
      <input type="search" class="form-control rounded" placeholder="Search dens or categories" aria-label="Search" v-model="searchFilter" />
    </div>

    <ul id="denList">
      <li v-for="den in filteredDens" :key="den.denName">
        <div class="card">
          <div class="card-header">
            <h5>{{ den.denName }}</h5>
            <p>Created by: {{ den.denCreatorUserName }}</p>
          </div>
          <div class="card-body" v-on:click="$router.push({ name: 'den', params: { denName: den.denName } })">
            <p>{{ den.denDesc }}</p>
          </div>
          <button class="delete-button" v-if="den.denCreatorUserName === $store.state.user.username || this.$store.state.user.authorities.some(auth => auth.name === 'ROLE_ADMIN')" v-on:click="DeleteDen(den)"></button>
        </div>
      </li>
    </ul>
  </div>
</template>

  
<script>
import DenService from '../services/DenService';


export default {
  components: {
    
  },


  data() {
    return {
      searchFilter: "",
    };
  },
  computed: {
    filteredDens() {

      const searchFilter = this.searchFilter.toLowerCase();
      return this.$store.state.dens.filter((den) => {
        // Check if denName includes the searchFilter
        const nameMatch = den.denName.toLowerCase().includes(searchFilter);

        // Check if any categoryName includes the searchFilter
        const categoryMatch = den.categoryNames.some((category) =>
          category.toLowerCase().includes(searchFilter)
        );

        // Return true if either condition matches or searchFilter is empty
        return searchFilter === "" ? true : nameMatch || categoryMatch;
      });
    },

  },
  methods: {
    DeleteDen(den) {
      console.log("bleep")
      if (confirm("Are you sure you want to delete this message? This action cannot be undone.")) {

        // TODO - Do a delete, then navigate to Topic Details on success
        // For errors, call handleErrorResponse
        console.log(den)
        console.log("blorg")

        DenService.delete(den).then(response => {
          this.getDens()
          console.log("deleted")
        }).catch(error => {
          this.handleErrorResponse(error, 'deleting');
        })

      }
      
    },
    getDens() {
      DenService.getDens().then(response => {
        this.$store.state.dens = response.data
      }).catch(error => {
        console.log('You are out of luck')
      })
    }
  }
};
</script>

<style>
/* Overall page container */
.page-container {
  display: flex;
  height: 100vh;
  background-color: #f4f4f9; /* Light background color for the whole page */
}

/* Sidebar styling */
.sidebar {
  width: 250px;
  background-color: #007bff; /* Sidebar background color */
  color: #fff;
  padding: 20px;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  position: fixed;
  top: 0;
  left: 0;
  height: 100%;
  overflow-y: auto;
}

.sidebar h2 {
  font-size: 20px;
  margin-bottom: 15px;
}

.sidebar p {
  margin: 10px 0;
}

/* Main content area */
.main-content {
  margin-left: 250px; /* Space for the sidebar */
  width: calc(100% - 250px);
  padding: 20px;
}

/* Container to center content vertically and horizontally */
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  padding: 20px;
}

/* Styling for the + card */
.card-plus {
  width: 100%;
  max-width: 300px;
  margin-bottom: 20px;
}

.card-plus .card-body {
  background-color: #007bff; /* Primary color */
  border-radius: 8px;
  padding: 20px;
  text-align: center;
  cursor: pointer;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  color: #fff;
}

.card-plus h5 {
  font-size: 28px;
  margin: 0;
}

.card-plus p {
  font-size: 16px;
  margin: 5px 0;
}

/* Search bar styling */
#searchBar {
  width: 100%;
  max-width: 400px;
  margin-bottom: 20px;
}

#searchBar .form-control {
  border: 2px solid #007bff; /* Primary color */
  border-radius: 4px;
  padding: 10px;
}

/* List styling */
#denList {
  width: 100%;
  max-width: 800px;
  padding: 0;
  list-style: none;
}

#denList li {
  margin-bottom: 20px;
}

/* Card styling */
.card {
  position: relative;
  background-color: #fff; /* Card background color */
  border: 1px solid #ddd; /* Light border for a clean look */
  border-radius: 4px; /* Sharper edges */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* Subtle shadow */
  padding: 20px;
  overflow: hidden;
}

/* Card header styling */
.card .card-header {
  margin-bottom: 20px;
}

.card .card-header h5 {
  margin: 0;
  font-size: 18px;
  color: #333; /* Darker text for better readability */
}

.card .card-header p {
  margin: 5px 0;
  font-size: 14px;
  color: #666; /* Slightly lighter text */
}

/* Card body for centered content */
.card .card-body {
  text-align: center;
  color: #333; /* Consistent text color */
}

/* Delete button styling */
.delete-button {
  position: absolute;
  bottom: 20px;
  right: 20px;
  background-color: #dc3545; /* Red background */
  color: #fff;
  border: none;
  border-radius: 4px;
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-size: 18px; /* Size of the X */
  text-align: center;
  line-height: 1;
  padding: 0;
}

.delete-button:hover {
  background-color: #c82333; /* Darker shade on hover */
}

/* Create the X shape */
.delete-button::before {
  content: 'X'; /* Unicode character for multiplication sign (X) */
}
</style>
