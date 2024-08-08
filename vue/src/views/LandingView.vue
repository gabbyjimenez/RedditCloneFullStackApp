<template>
    <div id="page">
        <Header id="header"></Header>

        <div id="panelMain" class="panel">
            <router-link id="denLink" v-bind:to="{ name: 'dens' }"><button type="button" class="btn btn-primary">Get
                    Started</button></router-link>
        </div>

        <div id="infoPanels">
            <div id="panelFeatures" class="card bg-dark text-white">
                <img class="card-img" src="..." alt="Card image">
                <div class="card-img-overlay">
                    <h5 class="card-title">A</h5>

                </div>
            </div>

            <div id="panelB" class="panel">B</div>
            <div id="panelC" class="panel">C</div>
        </div>


        <Footer></Footer>
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
    height: 60%;
    background-color: indigo;



}

#denLink {
    justify-content: center;
    align-content: center;
}

.panel {
    display: flex;
    justify-content: center;
    border-color: black;

}

#infoPanels {
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    height: 40%;

}

#panelA {
    background-color: red;
    flex-grow: 1
}

#panelB {
    background-color: green;
    flex-grow: 1;
}

#panelC {
    background-color: blue;
    flex-grow: 1
}</style>
