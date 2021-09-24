<template>
   <div>
      <h3>{{ stock.stock_ticker }}</h3>
      <h4>Your Position</h4>
      <p> Shares: {{ stock.total_shares}} </p>
      <p>Market Value: ${{ marketValue }} </p>
      <router-link v-bind:to="{name: 'stock-info', params: {ticker: stock.stock_ticker}}">Trade Stock</router-link>
    </div>
</template>

<script>
import stockService from '../services/StockService'
export default {
    props: ['stock'],
    data() {
        return {
            currentValue: 0,
        }
    },
    created() {
        stockService.getStockInfo(this.stock.stock_ticker)
            .then(result => {
                this.currentValue = result.data.currentPrice;
            })
    },
    computed: {
        marketValue() {
            return this.currentValue * this.stock.total_shares;
        }
    }


}
</script>

<style>

</style>