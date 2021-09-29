<template>
<div id="leader">
  <leader-board v-bind:gameId="gameId"/>
  <div>
    <leader-chart class="leaderChart" v-bind:gameId="gameId"/>
  </div> 
  </div>
</template>

<script>
import LeaderBoard from '../components/LeaderBoard.vue'
import LeaderChart from '../components/LeaderChart.vue'
export default {
  components: { LeaderBoard, 
  LeaderChart
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
</style>