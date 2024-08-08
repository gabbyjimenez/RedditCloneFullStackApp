<template>
    <div id="page">
        <Header id="header"></Header>
        <div id="container">
            
            <div id="callToAction">
            <router-link id="denLink" v-bind:to="{ name: 'dens' }"><button id="button">Get Started!</button></router-link>
            </div>
        <div id="infoPanels">    
        
        </div>
        </div>
    </div>
</template>

<script>
import Header from '../components/Header.vue';

export default {
    components: {
        Header
    }
}
</script>

<style scoped>
#page {
    display: flex;
    flex-direction: column;
    height: 100vh; /* Full viewport height */
}

#content {
    display: flex;
    flex-direction: column;
    justify-content: center; /* Center content vertically */
    align-items: center;     /* Center content horizontally */
    flex-grow: 1;            /* Take up remaining space */
}

#button {
    margin-bottom: 20px;     /* Add some space between button and description */
}

#description {
    height: 33%;              /* Take up one third of the screen */
    width: 100%;              /* Full width */
    display: flex;
    align-items: center;     /* Center grid vertically */
    justify-content: center; /* Center grid horizontally */
    background-color: #f0f0f0; /* Optional: Add a background color for contrast */
}

.grid-container {
    display: grid;
    grid-template-columns: repeat(3, 1fr); /* 3 columns of equal width */
    grid-template-rows: repeat(3, 1fr);    /* 3 rows of equal height */
    gap: 10px;                            /* Space between grid items */
    width: 80%;                           /* Adjust width as needed */
    height: 80%;                          /* Adjust height as needed */
}

.grid-item {
    display: flex;
    align-items: center; /* Center content vertically */
    justify-content: center; /* Center content horizontally */
    background-color: #e0e0e0; /* Optional: Add a background color for contrast */
    border: 1px solid #ccc; /* Optional: Add a border */
}
</style>
