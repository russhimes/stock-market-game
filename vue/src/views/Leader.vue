<template>
<div id="leader">
  <leader-board v-bind:gameId="gameId"/>
  <!-- <leader-chart class="leaderChart" v-bind:gameId="gameId"/> -->
  </div>
</template>

<script>
import LeaderBoard from '../components/LeaderBoard.vue'
// import LeaderChart from '../components/LeaderChart.vue'
export default {
  components: { LeaderBoard, 
  // LeaderChart
  },
  props: ["gameId"]

}
</script>

<style scoped>
  #leader {
    display: flex;
    flex-direction: column;
    /* margin: 0; */
  }
  .leaderChart{
    min-width: 500px;
    min-height: 500px;
  }
</style>