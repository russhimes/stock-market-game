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
import GameCard from '../components/GameCard.vue'
export default {
    components: {GameCard},  
    computed: {
            rejected() {
                let rejected = [];
                for (let game = 0; game < this.$store.state.games.length; game++) {
                    //loop through to get the status of the current user
                    //there must be an easier way to do this
                    for (let player = 0; player < this.$store.state.games[game].players.length; player++) {
                        if (this.$store.state.games[game].players[player].user_id == 
                        this.$store.state.user.id &&
                        this.$store.state.games[game].players[player].game_status == 'Rejected'){
                         rejected.push(this.$store.state.games[game]);
                        }
                    }
                }
                return rejected;
            },
            accepted() {
                let accepted = [];
                for (let game = 0; game < this.$store.state.games.length; game++) {
                    //loop through to get the status of the current user
                    //there must be an easier way to do this
                    for (let player = 0; player < this.$store.state.games[game].players.length; player++) {
                        if (this.$store.state.games[game].players[player].user_id == 
                        this.$store.state.user.id &&
                        this.$store.state.games[game].players[player].game_status == 'Accepted'){
                         accepted.push(this.$store.state.games[game]);
                        }
                    }
                }
                
                return accepted;
            },
            pending() {
                let pending = [];
                for (let game = 0; game < this.$store.state.games.length; game++) {
                    //loop through to get the status of the current user
                    //there must be an easier way to do this
                    for (let player = 0; player < this.$store.state.games[game].players.length; player++) {
                        if (this.$store.state.games[game].players[player].user_id == 
                        this.$store.state.user.id &&
                        this.$store.state.games[game].players[player].game_status == 'Pending'){
                         pending.push(this.$store.state.games[game]);
                        }
                    }
                }
                return pending;  
            }
        }
}
</script>

<style>

</style>