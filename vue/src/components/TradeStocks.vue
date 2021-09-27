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
            <table>
                <tr v-for="stock in topStocks" v-bind:key="stock.id">
                    <td><img :src="stock.logoURL"></td>
                    <td> {{stock.stockSymbol}} </td>
                    <td> {{stock.companyName}} </td>
                    <td> ${{stock.currentPrice}} </td>
                    <td>
                        <router-link v-bind:to="{name: 'stock-info', params: {ticker: stock.stockSymbol}}"> Trade </router-link>
                    </td>
                </tr>
            </table>
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

<style scope>
    img {
        max-height: 3rem;
        max-width: 3rem;
        margin-right: 1rem;
    }

    .main {
        padding: 1rem;
        border: 2px solid black;
        display: flex;
        justify-content: center;
    }

    .scroll {
        max-height: 50vh;
        overflow-y: scroll;
    }

    tr {
        height: 3rem;
        padding: 10px;
        align-items: center;
        max-height: 3rem;
    }

    td {
        padding-right: 10px;
    }

    h3 {
        padding: 1rem 0;
        text-align: center;
    }
</style>