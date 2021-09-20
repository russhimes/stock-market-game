<template>
  <div class="home">
    <p v-if="!$store.state.user.username">You must be authenticated to see this</p>
    <div v-else>
      <!--Discuss during team stand-up, we need to be able to get games by user,
      and we need to be able to assign games a "pending/accepted/rejected status" -->
      <game-card v-for="game in $store.state.games" v-bind:key="game.id" v-bind:game="game"/>
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
};
</script>
