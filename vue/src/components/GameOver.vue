<template>
  <div id="gameOver">
      <div v-if="winnerCalculated == true">
        <h2> Game Over! </h2>
        <h3>{{winnerInfo[0].username}} WINS!</h3>
        <leader-board v-bind:gameId="gameId"/>
      </div>
      <p v-else>loading</p>
  </div>
</template>

<script>
import playerService from '../services/PlayerService.js'
import userService from '../services/UserService.js'
import LeaderBoard from '../views/Leader.vue'
export default {
    components: {LeaderBoard},
    props: ["gameId"],
    data() {
        return {
            winnerCalculated : false,
            winnerInfo : []
        }
    },
    created() {
        this.checkWinner().then(() => {
        const timer = setInterval (() => {
            this.checkWinner();
            clearInterval(timer);
            return;
        }, 100)
        })
    },
    methods: {
        checkWinner() {
            let result = playerService.getPlayersByGame(this.gameId).then(result => {
            for (let i = 0; i < result.data.length; i++) {
                if (result.data[i].game_status != "Finished") {
                    this.winnerCalculated = false;
                    break;
                }
                 if (i == result.data.length-1) this.winnerCalculated = true;
            }
            if (this.winnerCalculated) {
                for (let i = 0; i < result.data.length; i++) {
                    this.winnerInfo.push(result.data[i]);
                }
                this.winnerInfo.sort((a, b) => {return a.availableFunds - b.availableFunds});
                for (let i = 0; i < this.winnerInfo.length; i++) {
                    userService.getUserById(this.winnerInfo[i].user_id).then(userResult => {
                        this.winnerInfo[i].username = userResult.data.username;
                    })
                }
            }
            return result;
            });
            return result;
        }
    }   

}
</script>

<style scoped>
#gameOver{
    color: black;
}

</style>