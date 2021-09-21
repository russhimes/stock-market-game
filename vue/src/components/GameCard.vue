<template>
  <div id="gameCard">
      <h3 id="gameName">{{game.name}}</h3>
      <p id="gameEnd">Game ends on {{game.end_date}}, {{game.end_time}}</p>
      <button id="acceptButton" class="cardButton" v-if="player.gameStatus == 'Pending'" v-on:click="acceptGame">Accept Invite</button>
      <button id="rejectButton" class="cardButton" v-if="player.gameStatus == 'Pending'" v-on:click="rejectGame">Reject Invite</button>
      <router-link id="gameLink" tag="button" v-if="true" v-bind:to="{name: 'game', params: {id: game.id}}">Go To Game</router-link>
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
                game_id: this.game.id,
                availableFunds: "",
                gameStatus: ""
            }
        }
    },
    method: {
        acceptGame(){},
        rejectGame(){}
    },
    created() {
        playerService.getPlayerByGame(this.game.id)
            .then(response => {
                this.player.gameStatus = response.data.gameStatus;
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
    margin: 0.2rem;
    background-color: rgb(47, 107, 50);
    text-emphasis: Bold;
    color: white;
    border-radius: 6px;
    font-family: sans-serif;
}

#gameName {
    font-size: 2rem;
}

</style>