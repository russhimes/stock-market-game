<template>
   <div class="stock">
       <div class="column">
        <h4>{{ stock.stock_ticker }}</h4>
        <p> {{ stock.total_shares}} Share{{stock.total_shares != 1 ? "s" : ""}} </p>
        <p>Value: ${{ marketValue }} </p>
       </div>
       <div class="column">

        <router-link v-bind:to="{name: 'stock-info', params: {ticker: stock.stock_ticker}}">Trade</router-link>
      </div>
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

<style scoped>
    .stock {
        border: 1px solid var(--color-primary);
        display: flex;
        justify-content: space-between;
        padding: 1rem;
        align-items: center;
    }

</style>