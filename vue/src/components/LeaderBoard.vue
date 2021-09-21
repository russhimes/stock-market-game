<template>
  <h2>Leader Board!</h2>

  <!-- <div class='playerCard' v-for='player in playerList'>
    {{ player.name }}
  </div> -->

</template>

<script>
import playerService from '../services/PlayerService.js';
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

</style>