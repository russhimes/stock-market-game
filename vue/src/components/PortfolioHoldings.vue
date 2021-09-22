<template>
  <div class="holdings">
    <div class="buyingPower">
      <h3>Buying Power: </h3>
      <p> ${{ player.availableFunds }} </p>
    </div>
      <h3 class= "holdingsTitle">Your current stock portfolio includes:</h3>
      <p>This is where we will show their portfolio holdings</p>
  </div>
</template>

<script>
import playerService from "../services/PlayerService";
export default {
  props: ['gameId'],
  data() {
    return {
      player: {}
    }
  },
  created() {
    playerService.getPlayerByGame(this.gameId) 
      .then(response => {
        this.player = response.data;
      })
  }
}
</script>

<style>
.holdingsTitle{
  color: pink;
}
.holdings{
  border-style: solid;
}
</style>