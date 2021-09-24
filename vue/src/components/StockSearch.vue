<template>
  <div id="stockSearch">
      <p>Search: </p>
      <input type="text" id="searchText" v-model="searchTerm" />
      <button v-on:click="retrieveSearch(searchTerm)">Search!</button>
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

<style>
.resultsList{
    display: flex;
    flex-direction:row;
    text-decoration: none;
}
</style>