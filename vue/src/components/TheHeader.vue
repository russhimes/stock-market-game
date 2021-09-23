<template>
  <div id="header">
      <h1>Virtual Stock Market</h1>
      <router-link v-bind:to="{ name: 'home' }">Home &nbsp;|&nbsp;</router-link>
      <p v-if="$store.state.user.username" id="username">{{$store.state.user.username}} &nbsp;|&nbsp;</p>
      <routerLink v-if="$store.state.user.username" id="logoutLink" to="/logout">Log Out</routerLink>
      <router-link v-else id="loginLink" to="/login">Log In</router-link>
  </div>
</template>

<script>
export default {
    created() {
    }
}
</script>

<style scoped>
#header {
    max-height: 10vh;
    width: 100vh;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
</style>