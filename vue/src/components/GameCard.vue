<template>
<div>
    <router-link class="gameCard" v-on:click.native="updateCurrentPlayer()" id="gameLink" tag="button" v-if="player.game_status == 'Accepted'" v-bind:to="{name: 'game', params: {id: game.id}}">
      <h3 id="gameName">{{game.name}}</h3>
      <!-- <p id="gameEnd">Game ends on {{game.end_date}}, {{game.end_time}}</p> -->
      <countdown-timer v-bind:gameId="game.id" class="countdown-timer"></countdown-timer>
      <leader-board v-bind:gameId="game.id" class="leaderboard"/>
    </router-link>
<div class="gameCard" v-else>
    <h3 id="gameName">{{game.name}}</h3>
      <p id="gameEnd">Invited By: {{gameOrganizer}}</p>
      <a id="acceptButton" class="cardButton" v-if="player.game_status == 'Pending'" v-on:click="acceptGame()">Accept Invite</a>
      <a id="rejectButton" class="cardButton" v-if="player.game_status == 'Pending'" v-on:click="rejectGame()">Reject Invite</a>
</div>

</div>

</template>

<script>
import LeaderBoard from '../components/LeaderBoard'
import CountdownTimer from '../components/CountdownTimer'
import playerService from '../services/PlayerService'
import userService from '../services/UserService'

export default {
    name: 'game-card',
    components: {
        LeaderBoard, CountdownTimer
    },
    props: ["game"],
    data() {
        return {
            player: {
                id: "",
                user_id: "",
                game_id: "",
                availableFunds: "",
                game_status: ""
            },
            gameOrganizer: "",
        }
    },
    methods: {
        acceptGame(){
            this.player.game_status = 'Accepted'
            console.log(this.player.user_id);
            playerService.update(this.player).then(response => {
                if (response.status) {
                    this.$store.commit("UPDATE_PLAYER_STATUS", this.player);
                }
            });
        },
        rejectGame(){
            this.player.game_status = 'Rejected'
            playerService.update(this.player).then(response => {
                if (response.status) {
                    this.$store.commit("UPDATE_PLAYER_STATUS", this.player);
                }
            });
        },
        updateCurrentPlayer() {
            this.$store.commit('SET_CURRENT_PLAYER', this.player.id);
            this.$store.commit('SET_ACTIVE_GAME', this.game.id);
        }
    },

   created() {
        playerService.getPlayerByGame(this.game.id)
            .then(response => {
                this.player.game_status = response.data.game_status;
                this.player.id = response.data.id;
                this.player.user_id = response.data.user_id;
                this.player.availableFunds = response.data.availableFunds;
            });
        userService.getUserById(this.game.organizer_id)
            .then(response => {
                this.gameOrganizer = response.data.username;
            })
            console.log(this.game);
    }
}

</script>

<style>
    .gameCard {
        background-color: var(--background-color);
        border-radius: var(--border-radius);
        margin-bottom: 1rem;
        padding: 1rem;
        border: none;
        transition: 0.4s;
        text-align: left;
    }

    #gameLink:hover {
        cursor: pointer;
    }

    #gameName {
        padding: 0.5rem 0;
    }

    #acceptButton, #rejectButton {
        cursor: pointer;
        font-size: 1rem;
        margin-right: 1.6rem;
        border-bottom: 2px solid var(--color-lighter);
    }

    #rejectButton:hover {
        border-bottom: 2px solid var(--color-red);
    }

    #acceptButton:hover {
        border-bottom: 2px solid var(--color-green);
    }

    h3, p {
        padding-bottom: 0.5rem;
    }

    h3 {
        text-transform: uppercase;
    }

    button {
        text-transform: none;
        width: 100%;
    }

    button:hover {
        border: 0;
    }

    /* button:hover .leaderboard, button:hover .countdown-timer {
        background-color: var(--background-color);
    } */

    .leaderboard {
        border: 0;
        padding: 0;
        text-align: center;
        background-color: white;
        border-radius: var(--border-radius);
        transition: 0.4s;
        color: var(--color-primary);
    }

    .leaderboard h3, .countdown-timer h3 {
        font-size: 1rem;
        padding: 0.5rem;
    }

    .countdown-timer {
        padding: 0;
        border: 0;
        background-color: white;
        border-radius: var(--border-radius);
        margin-bottom: 0.5rem;
        transition: 0.4s;
    }

    .countdown-timer .time {
        width: 3rem;
    }

    .countdown-timer .countdownContainer h4 {
        font-size: 1rem;
    }

    .countdown-timer .countdownContainer p {
        font-size: 0.7rem;
    }

    /* .countdown-timer .seconds {
        display: none;
    } */
</style>