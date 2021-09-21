import axios from 'axios';

// need path names  
// using console.log as placeholder to avoid compiler errors
export default {
    getAllPlayers(){
        return axios.get('/players');
    },
    // get(id) {
    //     return axios.get();
    //   },

    //   getBalance(id){
    //     return axios.get();
    //   },
    
    create(player) {
      return axios.post('/players', player);
    },
  
    //   update(player) {
    //     return axios.put();
    //   },
    
    delete(id) {
      return axios.delete(`/players/${id}`);
    }
  }
    
