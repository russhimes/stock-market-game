<template>
  <div class="home">
    <p v-if="!$store.state.user.username">You must be authenticated to see this</p>
    <div v-else>
      <router-link id="createGame" tag="button" v-bind:to="{name: 'new-game'}">Create Game!</router-link>
      <h2>Active Games</h2>
      <!--Discuss during team stand-up, we need to be able to get games by user,
      and we need to be able to assign games a "pending/accepted/rejected status" -->
      <game-card v-for="game in $store.state.games" v-bind:key="game.id" v-bind:game="game"/>
      <h2>Pending Invites</h2>
      <!--Pending Games-->
      <h2>Rejected Games</h2>
      <!--RejectedGames-->
      
    </div>
  </div>
</template>

<script>
import GameCard from '../components/GameCard.vue';
export default {
  components: { GameCard },
  name: "home",
  created() {
    if (!this.$store.state.loaded) {
      this.$store.commit("GET_GAMES", 0);
    }
  }
}
</script>

<style scoped>
button {
  background-color: rgb(47, 107, 50);
  color: white;
  font-size: 3rem;
  min-height: 10vh;
  min-width: 30vw;
}
h2 {
  font-size: 3rem;
}

</style>
