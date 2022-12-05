import axios from 'axios';

const MOVIES_REST_API_URL = 'http://localhost:8080/api/movies';
const EARLY_MOVIES_REST_API_URL = 'http://localhost:8080/api/movies/reg';

class MovieService{

    async getMovies(){
        return axios.get(MOVIES_REST_API_URL);
    }

    async getEarlyMovies(){
        return axios.get(EARLY_MOVIES_REST_API_URL)
    }

    async post(postArray){ 
        await axios.post(MOVIES_REST_API_URL, postArray);
    }
}

export default new MovieService();