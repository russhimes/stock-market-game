<template>
 <div id="gamesList">
      <h2>Active Games</h2>
      <game-card v-for="game in accepted" v-bind:key="game.id" v-bind:game="game"/>
      <h2>Pending Invites</h2>
      <game-card v-for="game in pending" v-bind:key="game.id" v-bind:game="game"/>
      <h2>Rejected Games</h2>
      <game-card v-for="game in rejected" v-bind:key="game.id" v-bind:game="game"/>
 </div>
</template>

<script>
import playerService from '../services/PlayerService.js'
import GameCard from '../components/GameCard.vue'
export default {
    components: {GameCard},
    created() {
        for (let game = 0; game < this.$store.state.games.length; game++) {
            console.log(this.$store.state.games[game]);
          playerService.getPlayerByGame(this.$store.state.games[game].id)
            .then(response => {
                console.log(response.data);
                console.log(this.$store.state.games[game]);
                if (response.data.game_status == 'Pending') this.pending.push(this.$store.state.games[game]);
                if (response.data.game_status == 'Rejected') this.rejected.push(this.$store.state.games[game]);
                if (response.data.game_status == 'Accepted') this.accepted.push(this.$store.state.games[game]);  
            });
        }
    },
    data () {
        return {
            rejected: [],
            accepted: [],
            pending: []
        }
    }
}
</script>

<style>

</style>