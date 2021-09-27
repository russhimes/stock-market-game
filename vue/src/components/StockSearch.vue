<template>
  <div id="stockSearch">
      <input type="text" id="searchText" v-model="searchTerm" />
      <button v-on:click="retrieveSearch(searchTerm)">Search</button>
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

<style>
    .resultsList{
        display: flex;
        flex-direction:row;
        text-decoration: none;
    }

    #stockSearch {
        padding: 1rem;
        text-align: center;
    }

    input {
        border: none;
        background-color: transparent;
        border-bottom: 2px solid var(--color-primary);
        margin: 0 1rem;
        width: 50%;
        transition: 0.4s;
    }

    input:focus, input:hover {
        outline: none;
        border-bottom: 2px solid var(--color-green);
    }

    button {
        padding: 0.2rem 2rem;
        border: 2px solid var(--color-primary);
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
        color: red;
    }
</style>