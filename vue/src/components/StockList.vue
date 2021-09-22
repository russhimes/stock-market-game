<template>
  <div>
    <div v-for="stock in stocks" v-bind:key="stock.id">
      <h3>{{ stock.stock_ticker }}</h3>
      <p> Shares: {{ stock.total_shares}} </p>
    </div>
  </div>
</template>

<script>
export default {
  props: ['stocks'],
  data(){
    return {
      stockList: []
    }
  },
  created(){}
};
</script>

<style>

</style>