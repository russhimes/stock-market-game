<template>
  <div id="app">
    <div id="nav">
      <the-header />
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>


<script>
import TheHeader from './components/TheHeader.vue'
export default {
  components: { TheHeader },
  
}
</script>

<style>

</style>
