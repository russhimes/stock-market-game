import axios from 'axios';

// need path names  
// using console.log as placeholder to avoid compiler errors
export default {
    getAllPlayers(){
        return axios.get()
    },
    get(id) {
      console.log(id);
        return axios.get();
      },

      getBalance(id){
        console.log(id);
        return axios.get();
      },
    
      create(player) {
        console.log(player);
        return axios.post();
      },
    
      update(player) {
        console.log(player);
        return axios.put();
      },
    
      delete(id) {
        console.log(id);
        return axios.delete();
      }
    }
    
