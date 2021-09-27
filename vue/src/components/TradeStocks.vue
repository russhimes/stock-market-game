<template>
<div class="main">
    <!-- load data pulled into table  -->
    <!-- need to make it dyanamic - need to build out logic -->
    <!-- create component stock information with option buy and sell 
    pop up w logic on how much you can buy/ sell 
    form submission at the bottom to search and show results here  -->
    <div class="wrapper">
        <h3>Make a Trade</h3>
        <div class="scroll">
            <div v-for="stock in topStocks" v-bind:key="stock.id" class="row">
                <img :src="stock.logoURL">
                <p> {{stock.stockSymbol}} </p>
                <p> {{stock.companyName}} </p>
                <p> ${{stock.currentPrice}} </p>
                <p>
                    <router-link v-bind:to="{name: 'stock-info', params: {ticker: stock.stockSymbol}}"> Trade </router-link>
                </p>
            </div>
        </div>
        <stock-search />


    </div>

</div>
 
</template>

<script>
import tradeTopStocks from '../services/StockService.js'
import StockSearch from '../components/StockSearch.vue'
export default {
    components: {StockSearch},
    data(){
        return {
            topStocks: []
    }
},

created(){
    tradeTopStocks.getTopStocks().then(
        (response) => {
            this.topStocks = response.data;
            //console.log(this.topStocks);
        }
    ).catch(
        (error) => console.log(error)
    );

}
};
</script>

<style scoped>
    img {
        max-height: 3rem;
        max-width: 3rem;
    }

    .main {
        padding: 1rem 0;
        display: flex;
        justify-content: center;
        flex-grow: 1;
    }

    .scroll {
        max-height: 50vh;
        overflow-y: scroll;
        width: 100%;
    }

    .row {
        border-collapse: collapse;
        width: 100%;
        margin: 0.5rem 0;
        border-radius: var(--border-radius);
    }

    .row {
        height: 4rem;
        padding: 10px;
        align-items: center;
        max-height: 4rem;
        background-color: var(--background-color);
        color: var(--color-primary);
        display: flex;
        align-content: center;
        justify-content: space-between;
    }

    h3 {
        padding: 1rem 0;
        text-align: center;
    }

    p {
        padding: 0;
    }
</style>