<template>
  <h2>Your Current Balance is</h2>
  <!-- <h4 this.balance></h4> -->


</template>

<script>
<<<<<<< HEAD
import playerService from '../services/PlayerService.js';
=======
//import playerService from '..services/PlayerService.js';
>>>>>>> ac89d665bd6381daa94c89d64816029616a04568
export default {
  data(){
    return {
      balance: 0,
      id: -1
    }
  },
  created(){
    playerService.getBalance(this.id).then(
      (response) => {
        this.balance = response.data;
      }
    ).catch(
        (error) => console.log(error)
      );

  }

};
</script>

<style>

</style>