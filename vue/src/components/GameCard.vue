<template>
  <div id="gameCard">
      <h3 id="gameName">{{game.name}}</h3>
      <p id="gameEnd">Game ends on {{game.end_date}}, {{game.end_time}}</p>
      <button id="acceptButton" class="cardButton" v-if="player.game_status == 'Pending'" v-on:click="acceptGame()">Accept Invite</button>
      <button id="rejectButton" class="cardButton" v-if="player.game_status == 'Pending'" v-on:click="rejectGame()">Reject Invite</button>
      <router-link id="gameLink" tag="button" v-if="player.game_status == 'Accepted'" v-bind:to="{name: 'game', params: {id: game.id}}">Go To Game</router-link>
  </div>
</template>

<script>
import playerService from '../services/PlayerService'
export default {
    props: ["game"],
    data() {
        return {
            player: {
                id: "",
                user_id: "",
                game_id: "",
                availableFunds: "",
                game_status: ""
            }
        }
    },
    methods: {
        acceptGame(){
            this.player.game_status = 'Accepted'
            console.log(this.player.user_id);
            playerService.update(this.player).then(response => {
                if (response.status) {
                    this.$store.commit("UPDATE_PLAYER_STATUS", this.player);
                    console.log(this.$store.state.games);
                }
            });
        },
        rejectGame(){
            this.player.game_status = 'Rejected'
            playerService.update(this.player).then(response => {
                if (response.status) {
                    this.$store.commit("UPDATE_PLAYER_STATUS", this.player);
                    console.log(this.$store.state.games);
                }
            });
        }
    },

   created() {
        playerService.getPlayerByGame(this.game.id)
            .then(response => {
                this.player.game_status = response.data.game_status;
                this.player.id = response.data.id;
                this.player.user_id = response.data.user_id;
                this.player.availableFunds = response.data.availableFunds;
            })
    }
}

</script>

<style scoped>
#gameCard {
    border: 2px black solid;
    background-color: rgb(47, 107, 50);
    text-emphasis: Bold;
    color: white;
    border-radius: 6px;
    padding-bottom: 3px;
    margin-right: 10px;
}

</style>