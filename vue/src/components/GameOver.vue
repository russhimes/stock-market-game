<template>
  
</template>

<script>
import playerService from '../services/PlayerService.js'
export default {
    props: ["gameId"],
    data() {
        return {
            winnerCalculated = false,
            winnerInfo = []
        }
    },
    created: {
        getWinner() {
            playerService.getPlayersByGame(gameId).then(result => {
                for (let i = 0; i < result.data.length; i++) {
                    if (result.data[i].game_status != "Finished") {
                        this.winnerCalculated = false;
                        break;
                    }
                    if (i == result.winnerCalculated.length-1) this.winnerCalculated = true;
                }
                if (this.winnerCalculated) {
                    for (let i = 0; i < result.data.length; i++) {
                        winnerInfo.push(result.data[i]);
                    }
                    winnerInfo.sort((a, b) => {return a.availableFunds - b.availableFunds});
                }
            });
        }
    }

}
</script>

<style>

</style>