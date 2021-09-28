<template>
<div id="leader">
  <leader-board v-bind:gameId="gameId"/>
  <leader-chart class="leaderChart" v-bind:gameId="gameId"/>
  </div>
</template>

<script>
import LeaderBoard from '../components/LeaderBoard.vue'
import LeaderChart from '../components/LeaderChart.vue'
export default {
  components: { LeaderBoard, LeaderChart},
  props: ["gameId"]

}
</script>

<style>
  #leader {
    display: flex;
    flex-direction: column;
    margin: 0;
  }
  .leaderChart {
    min-height: 50%;
    min-width: 100%;
  }
</style>