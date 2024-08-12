<template>
    <div id="page">
        <Header id="header"></Header>
        <div id="panelMain">
            <img id="image" class="card-img"
                src="https://res.cloudinary.com/daprq6s7w/image/upload/v1723135298/workers_qqq1ka.webp" alt="Card image">
            <div id="denLink" class="card-img-overlay">
                <p class="text-white">Product description</p>
                <h3  class="card-title"><router-link v-bind:to="{ name: 'dens' }"><button type="button" class="btn btn-primary">Get Started</button></router-link></h3>
                <h15 id="loginText" class="card-title" ><router-link id="LoginLink" v-bind:to="{ name: 'login' }">Login</router-link> | Don't have a account? sign up <router-link v-bind:to="{ name: 'register' }">here</router-link>!</h15>    
            </div> 
        </div>


        <div id="infoPanels">

            <!-- Left Panel -->
            <div id="panelLeft" class="card bg-dark text-white">
                <img id="image" class="img"
                    src="...">
                <div class="card-img-overlay">
                    <h5 class="card-title">Features</h5>
                </div>
            </div>

            <!-- Center Panel -->
            <div id="panelCenter" class="card bg-dark text-white">
                <img id="image" class="card-img"
                    src="...">
                <div class="card-img-overlay">
                    <h5 class="card-title">Benefits</h5>
                </div>
            </div>

            <!-- Right Panel -->
            <div id="panelRight" class="card bg-dark text-white">
                <img id="image" class="card-img"
                    src="...">
                <div class="card-img-overlay">
                    <h5 class="card-title">Purpose</h5>
                </div>
            </div>
        </div>
        <Footer id="footer"></Footer>

    </div>
</template>

<script>

import Header from '../components/Header.vue';
import Footer from '../components/Footer.vue';

export default {
    components: {
        Header,
        Footer
    }

}
</script>
<style scoped>
#page {
    display: flex;
    flex-direction: column;
    height: 100vh;

    /* Full viewport height */
}

#panelMain {
    display: flex;
    height: 50%;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;

}

#denLink {
    flex-direction: column;
    margin: auto;
    
}

#infoPanels {
    display: flex;
    flex-direction: row;
    height: 30%;
    flex-grow: 1;
    justify-content: center;

}

#image {
    object-fit: fill;
    height: 100%;

}

.card-title {
    align-content: center;
    justify-content: center;

}

.card-img-overlay {
    display: flex;
    justify-content: center;

}

#panelLeft {
    display: flex;
    flex-grow: 1;

}

#panelCenter {
    display: flex;
    flex-grow: 1;

}

#panelRight {
    display: flex;
    flex-grow: 1;

}

#footer {
    height: 10%;
    display: flex;
    flex-direction: column;
}
h15 {
color: red;
font-size: 50%;
font-weight: bold;


}

#panelA {
    background-color: red;
    flex-grow: 1
}
button {
   background-color: rgba(0, 0, 0, 0.584);
   border-color: rgba(240, 255, 250, 0);
}
p {
    font-size: 300%;

}
</style>
