<template>
 <div id="gamesList">
     
      <h2>Active Games</h2>
      <div class="cardsContainer">
      <game-card v-for="game in accepted" v-bind:key="game.id" v-bind:game="game"/>
      </div>
      <h2>Pending Invites</h2>
      <div class="cardsContainer">
      <game-card v-for="game in pending" v-bind:key="game.id" v-bind:game="game"/>
      </div>
      <h2>Rejected Games</h2>
      <div class="cardsContainer">
      <game-card v-for="game in rejected" v-bind:key="game.id" v-bind:game="game"/>
      </div>
 </div>
</template>

<script>
import GameCard from '../components/GameCard.vue'
export default {
    components: {GameCard},  
    computed: {
            rejected() {
                let rejected = [];
                for (let i = 0; i < this.$store.state.games.length; i++) {
                    for (let j = 0; j < this.$store.state.players.length; j++) {
                        if (this.$store.state.games[i].id == this.$store.state.players[j].game_id &&
                        this.$store.state.user.id == this.$store.state.players[j].user_id
                        && this.$store.state.players[j].game_status == 'Rejected') {
                            rejected.push(this.$store.state.games[i]);
                        }
                    }       
                }
                return rejected;
            },
            accepted() {
                let accepted = [];
                for (let i = 0; i < this.$store.state.games.length; i++) {
                    for (let j = 0; j < this.$store.state.players.length; j++) {
                        if (this.$store.state.games[i].id == this.$store.state.players[j].game_id &&
                        this.$store.state.user.id == this.$store.state.players[j].user_id
                        && this.$store.state.players[j].game_status == 'Accepted') {
                            accepted.push(this.$store.state.games[i]);
                        }
                    }
                }
                return accepted;
            },
            pending() {
                let pending = [];
                for (let i = 0; i < this.$store.state.games.length; i++) {
                    for (let j = 0; j < this.$store.state.players.length; j++) {
                        if (this.$store.state.games[i].id == this.$store.state.players[j].game_id &&
                        this.$store.state.user.id == this.$store.state.players[j].user_id
                        && this.$store.state.players[j].game_status == 'Pending') {
                            pending.push(this.$store.state.games[i]);
                        }
                    }
                }
                return pending;  
            }
    }
}
</script>

<style>
.cardsContainer {
    display: flex;
    max-height: 20vh;
}

h2 {
    font-size: 0.2rem;
}

</style>