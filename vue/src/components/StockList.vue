<template>
  <div>
    <div v-for="stock in stocks" v-bind:key="stock.id">
      <h3>{{ stock.stock_ticker }}</h3>
      <p> Shares: {{ stock.total_shares}} </p>
      <router-link v-bind:to="{name: 'stock-info', params: {ticker: stock.stock_ticker}}" v-bind:stock="stock">Trade Stock</router-link>
    </div>
  </div>
</template>

<script>
export default {
  props: ['stocks'],
  data(){
    return {
    }
  },
  created(){}
};
</script>

<style>

</style>