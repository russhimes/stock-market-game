<template>
  <div id="header">
      <h1>Stock Picker Game</h1>
      <span id="login" v-if="$store.state.username">
          <p v-if="$store.state.username" id="username">{{username}}</p>
          <routerLink id="logoutLink" to="logout">Log Out</routerLink>
      </span>
      <router-link v-else id="loginLink" to="Login">Log In</router-link>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#header {
    max-height: 10vh;
    width: 100vh;
    display: flex;
}
</style>