<template>
    <div id="page">
        <Header id="header"></Header>

        <!-- Main panel -->
        <!--Beginning of Gabby messing everything up-->
        <div id="mainPanel" style="height: 40rem;">
            <img id="heroImage" src="../assets/2-imageonline.co-1872188.gif" alt="Hero Image">
            <!--End of Gabby messing everything up-->
            <div id="overlayContainer" class="card-img-overlay">
                <h1 class="headline">Welcome to Foxtrot</h1>
                <p id="textBox" class="subtitle">
                    Discover Foxtrot, an employee-centric SaaS platform empowering Employee Resource Groups (ERGs) with a
                    service that provides convenient communication and enriching community collaboration.</p>
                <div class="action-buttons">
                    <router-link v-bind:to="{ name: 'dens' }">
                        <button id="buttonsOfACtion" type="button" class="btn btn-primary">Get Started</button>
                    </router-link>
                    <!-- <router-link id="LoginLink" v-bind:to="{ name: 'login' }">
                        <button type="button" class="btn btn-outline-light">Login</button>
                    </router-link> -->
                </div>
                <p class="signup-text">
                    Don't have an account? <router-link id="signUpLink" v-bind:to="{ name: 'register' }">Sign up
                        here!</router-link>
                </p>
            </div>
        </div>

        <!-- Content Section -->
        <div id="content" class="w3-row-padding w3-center w3-margin-top">

            <BenefitsCard id="cardA" title="Dens: Your ERG’s Information Hub" icon="fa-solid fa-tent"
                :benefits="['Facilitate Interdepartmental Collaboration','Build Organizational Communities', 'Encourage Team Development']" />
            <BenefitsCard id="cardB" title="Community Driven Content" icon="fa-solid fa-sort"
                :benefits="['User feedback via Upvote and Downvote on content ', 'Pin relevant comments that matter to you', 'Follow and filter your favorite Dens',  ]" />
            <BenefitsCard id="cardC" title="Stay Connected" icon="<fa-solid fa-comment-sms"
                :benefits="['Receive notifications on your Posts and Comments', 'SMS Functionality', '']" />
        </div>
        

        <!-- Footer -->
        <Footer id="footer"></Footer>
    </div>
</template>

<script>
import Header from '../components/Header.vue';
import Footer from '../components/Footer.vue';
import BenefitsCard from '../components/BenefitsCard.vue';

export default {
    components: {
        Header,
        Footer,
        BenefitsCard
    }
}
</script>

<style scoped>
/* Ensure full viewport height and flexible layout */
#page {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    margin: 0;
    font-family: 'Segoe UI';
}

#header {
    flex-shrink: 0;
    /* Ensure the header doesn't shrink */

}

#mainPanel {
    position: relative;
    height: 50vh;
    overflow: hidden;
}

#heroImage {
    width: 100%;
    height: 100%;
    object-fit: cover;
    filter: brightness(70%);
}

#overlayContainer {

    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background: rgba(0, 0, 0, 0.4);
    /**-- Semi-transparent overlay -- **/
    color: white;
    text-align: center;
    padding: 2rem;



}

.headline {
    font-size: 4rem;
    font-weight: bold;
    margin-bottom: 1rem;
}

.subtitle {
    font-size: 1.25rem;
    margin-bottom: 2rem;
}

.action-buttons {
    display: flex;
    gap: 1rem;
    margin-bottom: 1rem;
}

button {
    padding: 0.75rem 2rem;
    font-size: 1rem;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.btn-primary {
    background-color: #007bff;
    border: none;
    color: white;
}

.btn-primary:hover {
    background-color: #0056b3;
}

.btn-outline-light {
    background-color: transparent;
    border: 1px solid white;
    color: white;
}

.btn-outline-light:hover {
    background-color: white;
    color: #007bff;
}

.signup-text {
    font-size: 0.9rem;
}

#content {
    flex: 1;
    /**below was here before**/
    /**padding: 2rem; **/

    background-color: #f9f9f9;
    /**above was here before**/
    /**beginning of Gabby playing with things**/
    padding: 0rem;
    font-family: 'Segoe UI';

}

.contentSection {
    margin-bottom: 3rem;
    color: rgba(0, 0, 0, 0.434);
}

.contentSection h2 {
    margin-bottom: 1rem;
    font-size: 3rem;
    background-color: rgba(0, 0, 0, 0.434);
}

.contentSection p {
    font-size: 1rem;
    line-height: 1.6;
}

#footer {
    background: #333;
    color: white;
    padding: 1.5rem;
    text-align: center;
}

#footer p {
    margin: 0;
}

#buttonsOfACtion {
    background-color: rgba(0, 0, 0, 0.50);
}

#buttonsOfACtion:hover {
    background-color: rgba(97, 155, 138, 0.50);
}

#signUpLink:hover {
    color: rgba(255, 255, 255, 0.5);
}

#textBox {
    display: flex;
    width: 70%;
    font-size: 2rem;
}

#cardA:hover {
    display: inline-block;
    color: rgba(0, 0, 255, 0);
    object-fit: cover;
    content: url('https://res.cloudinary.com/daprq6s7w/image/upload/v1723677337/A_zhwkgi.svg');

    
}

img:focus {
        flex: 1;
        object-fit: cover;
    }

#cardB:hover {
    display: inline-block;
    color: rgba(0, 0, 255, 0);
    object-fit: cover;
    content: url('https://res.cloudinary.com/daprq6s7w/image/upload/v1723677356/B_ucjh0s.svg');

    
}

img:focus {
        flex: 1;
        object-fit: cover;
    }



#cardC:hover {
    display: inline-block;
    color: rgba(0, 0, 255, 0);
    object-fit: cover;
    content: url('https://res.cloudinary.com/daprq6s7w/image/upload/v1723677350/C_qq6jtb.svg');
    

    
}

img:focus {
        flex: 1;
        object-fit: cover;
    }
</style>