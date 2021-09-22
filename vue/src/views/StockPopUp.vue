<template>
  <trade-stock-pop-up v-bind:stock="stock"> </trade-stock-pop-up>
</template>

<script>
import TradeStockPopUp from '../components/TradeStockPopUp.vue'
export default {
  components: { TradeStockPopUp },
  props: ['stock']
}
</script>

<style>

</style>