<template>
<div class = "LeaderBoard">
  <h3 class="title">Leader Board!</h3>
  <p class="body">This is where we will show the stats of the leaderboard!</p>

  <!-- <div class='playerCard' v-for='player in playerList'>
    {{ player.name }}
  </div> -->
</div>
</template>

<script>
import playerService from '../services/PlayerService.js'
export default {
    data(){
      return {
        playerList: []
      }
    },
    created(){
      // first thing that happens when component loads 
      // taking data returned from getAllPlayers() and saving into our playerList
      playerService.getAllPlayers().then(
          (response) => {
            this.playerList = response.data;
          }
      ).catch(
        (error) => console.log(error)
      );

    }
};

</script>

<style>
.title{
  color: pink;
}
.LeaderBoard{
  border-style: solid;
}
</style>