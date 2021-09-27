<template>
  <div id="stockSearch">
      <div class="input">
        <input type="text" id="searchText" v-model="searchTerm" />
        <button v-on:click="retrieveSearch(searchTerm)">Search</button>
      </div>
      <router-link class="resultsList" v-if="searchResult.companyName" :to="{name: 'stock-info', params: {ticker: searchResult.stockSymbol}}">
        {{searchResult.companyName}} (${{searchResult.currentPrice}})
      </router-link>
      <p id="errorText" v-if="!(searchResult.companyName) && searched">
          Could not find that company.  Please enter a valid stock ticker symbol.
      </p>
  </div>
</template>

<script>
import stockService from '../services/StockService.js';
export default {
    data() {
        return {
            searchTerm: "",
            searchResult: {},
            searched: false
        }
    },
    methods: {
        retrieveSearch(searchTerm) {
            this.searched = true;
            this.searchResult = {};
            stockService.getSearchInfo(searchTerm).then(response => {
                console.log(response);
                if(response.status >=200 && response.status < 300) {
                    this.searchResult = response.data;
                }
            }).catch(error => {
                console.log(error.data);
            });
        }
    }

}
</script>

<style scoped>
    .resultsList{
        display: flex;
        flex-direction:row;
        text-decoration: none;
        background-color: var(--background-color);
        padding: 1rem;
        margin: 0.5rem 0;
        border-radius: var(--border-radius);
    }

    #stockSearch {
        padding: 1rem;
        text-align: center;
        width: auto;
    }

    .input {
        display: flex;
    }


    input {
        border: none;
        background-color: transparent;
        border-bottom: 2px solid var(--background-color);
        margin: 0 1rem;
        transition: 0.4s;
        color: var(--background-color);
        font-size: 1rem;
    }

    input:focus, input:hover {
        outline: none;
        border-bottom: 2px solid var(--color-green);
        color: var(--background-color);
        font-size: 1rem;
    }

    button {
        padding: 0.2rem 2rem;
        color: var(--background-color);
        border: 2px solid var(--background-color);
        background-color: transparent;
        border-radius: 4rem;
        cursor: pointer;
        transition: 0.4s;
        text-transform: uppercase;
    }

    button:hover {
        border: 2px solid var(--color-green);
        background-color: var(--color-green);
    }

    #errorText {
        color: var(--color-red);
    }
</style>