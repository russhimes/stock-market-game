<template>
  <div class="home">
    <p v-if="!$store.state.user.username">You must be authenticated to see this</p>
    <div v-else>
      <h2>Active Games</h2>
      <!--Discuss during team stand-up, we need to be able to get games by user,
      and we need to be able to assign games a "pending/accepted/rejected status" -->
      <game-card v-for="game in $store.state.games" v-bind:key="game.id" v-bind:game="game"/>
      <h2>Pending Invites</h2>
      <!--Pending Games-->
      <h2>Rejected Games</h2>
      <!--RejectedGames-->
      <h2>Create Game</h2>
      <form id="createGame" @submit.prevent="createGame">
        <div>
          <p>   Name: </p>
          <input type="text" id="gameName" v-model="game.name"/>
        </div>
        <div>
        <p>   Date: </p>
        <input type="date" id="endDate" v-model="game.end_date"/>
        </div>
        <div>
        <p>   Time: </p>
        <input type="time" id="endTime" v-model="game.end_time"/>
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  </div>
</template>

<script>
import GameCard from '../components/GameCard.vue';
import playerGamesService from '../services/PlayerGamesService.js'
export default {
  components: { GameCard },
  name: "home",
  created() {
    if (!this.$store.state.loaded) {
      this.$store.commit("GET_GAMES", 0);
    }
  },
  data() {
    return {
      game: {
        organizer_id: this.$store.state.user.id,
        name: "",
        end_date: '',
        end_time: ''
      }
    }
  },
  methods: {
    createGame() {
      playerGamesService.createGame(this.game);
      playerGamesService.getGames(0);
    }
  }
};
</script>

<style scoped>
h2 {
  font-size: 3rem;
}

#createGame {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border: 1px black solid;
  border-radius: 6px;
}
#createGame > * {
  display: flex;
} 

#createGame > button {
  min-width: 10vw;
  min-height: 50px;
  vertical-align: middle;
}


</style>
