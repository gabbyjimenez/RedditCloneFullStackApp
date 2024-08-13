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

 delete(den){
  return axios.delete(`/${den.denName}`, den);
},
toggleFavorite(den){
return axios.post(`/${den.denName}/favorite`);
}

}