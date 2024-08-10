<template>
    <div id="page">
        <Header id="header"></Header>

        <!-- Main panel -->
        <div id="mainPanel">
            <img id="heroImage" src="https://res.cloudinary.com/daprq6s7w/image/upload/v1723135298/workers_qqq1ka.webp" alt="Hero Image">
            <div id="overlayContainer" class="card-img-overlay">
                <p class="text-white">Discover the amazing features of our app and how it can benefit you.</p>
                <h3 class="card-title">
                    <router-link v-bind:to="{ name: 'dens' }">
                        <button type="button" class="btn btn-primary">Get Started</button>
                    </router-link>
                </h3>
                <h15 id="loginText">
                    <router-link id="LoginLink" v-bind:to="{ name: 'login' }">Login</router-link> | 
                    Don't have an account? Sign up <router-link v-bind:to="{ name: 'register' }">here</router-link>!
                </h15>
            </div>
        </div>

        <!-- Main content sections -->
        <div id="content">
            <section class="contentSection">
                <h2>Features</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam in dolor vitae nulla laoreet interdum. Vivamus dapibus, purus vel convallis pharetra, elit felis efficitur quam, nec dictum urna dui eu neque.</p>
            </section>

            <section class="contentSection">
                <h2>Benefits</h2>
                <p>Praesent sit amet est non purus fringilla tincidunt. Integer tincidunt purus eu leo dictum, sed feugiat nunc dapibus. Donec non arcu a nulla blandit consectetur non et ligula.</p>
            </section>

            <section class="contentSection">
                <h2>Purpose</h2>
                <p>Cras ullamcorper magna sed tellus volutpat, at ultricies eros placerat. Sed id ex id velit accumsan consectetur. Fusce vel nulla ut sem suscipit condimentum.</p>
            </section>

            <section class="contentSection">
                <h2>Our Team</h2>
                <p>Meet our dedicated team of professionals who are committed to providing you with the best experience. Our team brings together a wealth of knowledge and expertise to deliver exceptional results.</p>
            </section>
        </div>

        <!-- Footer -->
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
/* Ensure full viewport height and flexible layout */
#page {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    margin: 0;
}

#header {
    flex-shrink: 0; /* Ensure the header doesn't shrink */
}

#mainPanel {
    position: relative;
    height: 25vh; /* Reduced height for the image */
    overflow: hidden;
}

#heroImage {
    width: 100%;
    height: 100%;
    object-fit: cover; /* Ensure image covers the container */
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
    background: rgba(0, 0, 0, 0.5); /* Semi-transparent overlay */
    color: white;
    text-align: center;
    padding: 2rem; /* Responsive padding */
}

#content {
    flex: 1; /* Allow content to expand and push footer to the bottom */
    padding: 2rem; /* Responsive padding */
    background-color: #f4f4f4;
}

.contentSection {
    margin-bottom: 2rem; /* Responsive margin */
}

.contentSection h2 {
    margin-bottom: 1rem; /* Responsive margin */
    font-size: 1.5rem; /* Responsive font size */
}

.contentSection p {
    font-size: 1rem; /* Responsive font size */
    line-height: 1.6;
}

#footer {
    background: #333;
    color: white;
    padding: 1rem; /* Responsive padding */
    text-align: center;
}

#footer p {
    margin: 0;
}

h15 {
    color: red;
    font-size: 0.8rem;
    font-weight: bold;
}

button {
    background-color: rgba(0, 0, 0, 0.7);
    border: none;
    color: white;
    padding: 0.75rem 1.5rem; /* Responsive padding */
    font-size: 1rem; /* Responsive font size */
    cursor: pointer;
}

button:hover {
    background-color: rgba(0, 0, 0, 0.9);
}
</style>
