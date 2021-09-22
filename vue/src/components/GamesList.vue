<template>
 <div id="gamesList">
      <h2>Active Games</h2>
      <game-card v-for="game in accepted" v-bind:key="game.id2" v-bind:game="game"/>
      <h2>Pending Invites</h2>
      <game-card v-for="game in pending" v-bind:key="game.id2" v-bind:game="game"/>
      <h2>Rejected Games</h2>
      <game-card v-for="game in rejected" v-bind:key="game.id2" v-bind:game="game"/>
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
                    console.log(this.$store.state.games);
                    if (this.$store.state.games[game].game_status == 'Rejected') rejected.push(this.$store.state.games[game]);
                }
                return rejected;
            },
            accepted() {
                let accepted = [];
                for (let game = 0; game < this.$store.state.games.length; game++) {
                    console.log(this.$store.state.games[game]);
                    if (this.$store.state.games[game].game_status == 'Accepted') accepted.push(this.$store.state.games[game]);
                }
                return accepted;
            },
            pending() {
                let pending = [];
                for (let game = 0; game < this.$store.state.games.length; game++) {
                    if (this.$store.state.games[game].game_status == 'Pending') pending.push(this.$store.state.games[game]);
                }
                return pending;  
            }
    },
    methods: {
    }
}
</script>

<style>

</style>