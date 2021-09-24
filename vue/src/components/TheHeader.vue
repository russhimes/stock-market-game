<template>
  <div id="header">
      <h1>Virtual Stock Market</h1>
      <div class="links">
        <router-link v-bind:to="{ name: 'home' }">Home</router-link>
        <router-link v-if="$store.state.user.username" id="logoutLink" to="/logout">Log Out</router-link>
        <router-link v-else id="loginLink" to="/login">Log In</router-link>
      </div>
  </div>
</template>

<script>
export default {
    created() {
    }
}
</script>

<style scoped>
    * {
        background-color: var(--color-lighter);
        color: var(--background-color);
    }

    #header {
        max-height: 10vh;
        height: 10vh;
        width: 100vw;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 2rem 4rem;
    }

    h1 {
        font-size: 1.8rem;
        font-weight: 100;
        text-transform: uppercase;
        flex-grow: 1;
    }

    a {
        text-decoration: none;
    }

    .links {
        display: flex;
        justify-content: space-around;
        flex-grow: 0.5;
    }
</style>