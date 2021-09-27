<template>
  <div id="stockSearch">
      <input type="text" id="searchText" v-model="searchTerm" />
      <button v-on:click="retrieveSearch(searchTerm)">Search</button>
      <router-link class="resultsList" v-for="searchResult in searchResults" v-bind:key="searchResult.id" :to="{name: 'stock-info', params: {ticker: searchResult.stockSymbol}}">
        {{searchResult.description}} ({{searchResult.stockSymbol}})
      </router-link>
  </div>
</template>

<script>
import stockService from '../services/StockService.js';
export default {
    data() {
        return {
            searchTerm: "",
            searchResults: []
        }
    },
    methods: {
        retrieveSearch(searchTerm) {
            this.searchResults = [];
            stockService.getSearchInfo(searchTerm).then(response => {
                if(response.status >=200 && response.status < 300) {
                    for (let i = 0; i < response.data.length; i++) {
                        this.searchResults.push(response.data[i]);
                    }
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

    input {
        border: none;
        background-color: transparent;
        border-bottom: 2px solid var(--background-color);
        margin: 0 1rem;
        width: 50%;
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
</style>