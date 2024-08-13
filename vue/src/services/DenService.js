import axios from 'axios';

export default {


  getDens() {
     return axios.get('/dens' );
  },


  addDen(den) {
    return axios.post('/dens', den);
 },

 getCategories(){
  return axios.get('/categories' );

 },

 getFavorites(user){
  return axios.get(`/${user.username}/favorites`);

 },

 addFavorite(){
  return axios.post(`/${this.$route.params.denName}/favorite`)
 },

 delete(den){
  return axios.delete(`/${den.denName}`, den);
},
toggleFavorite(den){
return axios.post(`/${den.denName}/favorite`);
}

}