<template>
<div class = "balance">
  <h3 class = "balanceHeader">Your Current Balance is</h3>
  <p>This is where we will display balance</p>
  <!-- <h4 this.balance></h4> -->

</div>
</template>

<script>
import playerService from '../services/PlayerService.js';
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
.balanceHeader{
  color: pink;
}
.balance{
  border-style: solid;
}

</style>