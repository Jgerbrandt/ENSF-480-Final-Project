import React from 'react';
import MovieService from '../services/MovieService';
import {Avengers, HarryPotter, LordoftheRings} from '../img/Import';
import './MovieComponent.css';
import ShowTimeDropDown from './ShowTimeDropDown/ShowTimeDropDown';
import DateDropDown from './DateDropDown/DateDropDown';
import ViewSeats from './ViewSeats/ViewSeats';


class MovieComponent extends React.Component{
    
    constructor(props){
        super(props);
        const movieImg = {Avengers, HarryPotter, LordoftheRings};
        this.state = {
            movies:[],
            movieImg: movieImg,
            movieNames:[],
            selectedDay: "",
            selectedTime: "",
            selectedMovie: "",
            lastUser: null
        }
    }
    


    handleDaySelect = (day) =>{
        this.setState({selectedDay: day})
    }

    handleTimeSelect = (time) =>{
        this.setState({selectedTime: time})
    }


    //Load normal user movies on render
    componentDidMount(){
        MovieService.getMovies().then((response) => {
            this.setState({movies: response.data})
            let cats = [...new Set(response.data.map(movie => movie.movie))]
            this.setState({movieNames: cats})
        });
    }

    //If user switches, load appropriate movies
    componentDidUpdate(){
        if(this.props.currentUser !== this.state.lastUser){
            this.setState({lastUser: this.props.currentUser})
            if(this.props.currentUser){
                MovieService.getEarlyMovies().then((response) => {
                    this.setState({movies: response.data})
                    let cats = [...new Set(response.data.map(movie => movie.movie))]
                    this.setState({movieNames: cats})
                });
            }
            else{
            MovieService.getMovies().then((response) => {
                this.setState({movies: response.data})
                let cats = [...new Set(response.data.map(movie => movie.movie))]
                this.setState({movieNames: cats})
            });}
        }
        
    }
    

    render(){
        return (
            <div className="movies">
                <h1 className = "text-center">
                    Movies List
                </h1>
                <table className= "table table-striped">
                    <tbody>
                        {

                           this.state.movieNames.map((movie) => 
                           
                            <tr>
                                 <td className="poster"> <img src={[this.state.movieImg[(movie).replace(/ /g, '')]]}/> </td>
                                 
                                <h1>
                                        {movie} 
                                </h1>
                                
                                
                                <div> 

                                    <div>
                                        <DateDropDown title={movie} movies={this.state.movies} daySelect={this.handleDaySelect}/>
                                        
                                        <ShowTimeDropDown selectedDay={this.state.selectedDay} title={movie} movies={this.state.movies} timeSelect={this.handleTimeSelect}/>

                                        <ViewSeats currentUser={this.props.currentUser} time={this.state.selectedTime} day={this.state.selectedDay} title={movie} movies={this.state.movies}/>
                                    </div>

                                </div>
                                 


                            </tr>
                            
                                    
                                
                                
                           
                           )
                        }
                    </tbody>


                </table>
                

                
            </div>
        )
    }
}

export default MovieComponent;