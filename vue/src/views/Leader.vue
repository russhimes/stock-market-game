<template>
<div>
  <leader-board v-bind:gameId="gameId"/>
  </div>
</template>

<script>
import LeaderBoard from '../components/LeaderBoard.vue'
export default {
  components: { LeaderBoard },
  props: ["gameId"]

}
</script>

<style>

</style>