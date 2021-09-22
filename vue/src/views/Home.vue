<template>
  <div class="home">
    <p v-if="!$store.state.user.username">You must be authenticated to see this</p>
    <div v-else>
      <router-link id="createGame" tag="button" to="/new-game">Create Game!</router-link>
      <games-list />
      
      
    </div>
  </div>
</template>

<script>
import GamesList from '../components/GamesList.vue';
import gamesService from '../services/GamesService.js';
import playerService from '../services/PlayerService.js';

export default {
  components: { /*GameCard, */GamesList},
  name: "home",
  mounted() {
    gamesService.getAllGames().then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        let game = response.data[i];
        let isInList = false;
        for (let j = 0; j < this.$store.state.games.length; j++) {    
          if (this.$store.state.games[j].id == game.id) isInList = true;
        }
        if (!isInList) {
          this.$store.commit("ADD_GAME", game);
          playerService.getPlayersByGame(game.id)
          .then(playerResponse => {
            for (let j = 0; j < playerResponse.data.length; j++) {
              let player = playerResponse.data[j];
              isInList = false;
              for (let k = 0; k < this.$store.state.players.length; k++) {
                if (this.$store.state.players[k].id == player.id) isInList = true; 
              }
              if (!isInList) {
                this.$store.commit("ADD_PLAYER", player);
              }
            }
          });   
        }
      }
    });
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
