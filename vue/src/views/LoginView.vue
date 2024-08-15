<template>
  <div id="login">
    <form v-on:submit.prevent="login" class="login-form">
      <h1>Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials" class="alert alert-error">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration" class="alert alert-success">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      
      <button type="submit" class="submit-button">Sign in</button>
      <button type="button" class="guest-button" @click="loginAsGuest">Continue as Guest</button>
      <p class="signup-link">
        <router-link v-bind:to="{ name: 'register' }">Need an account? Sign up.</router-link>
      </p>
    </form>
  </div>
</template>


<script>
import authService from "../services/AuthService";

export default {
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
        
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/dens");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    loginAsGuest() {
      this.$router.push("/dens");
    }
  }
};
</script>

<style scoped>
#login {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f4f4f4;
}

.login-form {
  background: #ffffff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 2rem;
  width: 100%;
  max-width: 400px;
}

h1 {
  margin-bottom: 1.5rem;
  font-size: 1.5rem;
  color: #333;
  text-align: center;
}

.alert {
  padding: 1rem;
  margin-bottom: 1rem;
  border-radius: 4px;
}

.alert-error {
  background-color: #f8d7da;
  color: #721c24;
}

.alert-success {
  background-color: #d4edda;
  color: #155724;
}

.form-input-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
  color: #555;
}

input[type="text"],
input[type="password"],
input[type="tel"] {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
}

button.submit-button {
  width: 100%;
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: #ffffff;
  font-size: 1rem;
  cursor: pointer;
}

button.submit-button:hover {
  background-color: #0056b3;
}

button.guest-button {
  width: 100%;
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  background-color: #6c757d;
  color: #ffffff;
  font-size: 1rem;
  cursor: pointer;
  margin-top: 0.75rem;
}

button.guest-button:hover {
  background-color: #5a6268;
}

.signup-link {
  text-align: center;
  margin-top: 1rem;
}

.signup-link a {
  color: #007bff;
  text-decoration: none;
}

.signup-link a:hover {
  text-decoration: underline;
}
</style>
