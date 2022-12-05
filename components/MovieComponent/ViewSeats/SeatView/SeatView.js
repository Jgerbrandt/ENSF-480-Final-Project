import {React, useState, useEffect} from 'react'
import MovieService from '../../../services/MovieService';
import Checkout from './Checkout/Checkout';
import './SeatView.css'

const SeatView = (props) => {
    let seatingMap;
    let ourMovie;
    const [countChanges, setCountChanges] = useState(0)
    const [checkOut, setCheckOut] = useState(false);
    const [seatsBought, setSeatsBought] = useState(0);
    const [boughtSeats, setBoughtSeats] = useState([]);


    props.movies.forEach((movie) => {
        if((movie.movie === props.title) && (movie.time === props.time) && (movie.releaseDate === props.date)){
            ourMovie = (movie);
            seatingMap = (movie.seats.seats)
        }
    }
    )
    

    const seatClick = (rowIndex, seatIndex) =>{
        seatingMap[rowIndex][seatIndex] = (seatingMap[rowIndex][seatIndex] == 2 ? 0:2);
        
         setCountChanges(countChanges + 1);
         
         boughtSeats.push([rowIndex, seatIndex]);
         //setBoughtSeats(boughtSeats)
        // boughtSeats.filter(([indexRow, indexSeat]) => (indexRow !== rowIndex && indexSeat !== seatIndex))

    }


    const handleSubmit = () => {
        let temp = 0;
        for(let i=0; i<seatingMap.length; i++){
            for(let j=0; j<seatingMap[i].length; j++){
                if(seatingMap[i][j] == 2){
                    seatingMap[i][j] = 1;
                    temp++;
                }
            }
        }
        //setBoughtSeats(boughtSeats.filter(seats=> seats!==[]))
        
        
        setSeatsBought(temp)
        ourMovie.seats.seats = seatingMap;
        
        let returnArray = [[ourMovie.id]]
        boughtSeats.forEach(seatPair => returnArray.push(seatPair))
        MovieService.post(returnArray)
        setCheckOut(true)
        setBoughtSeats([])

    }
    

    if(props.popUpOpen){
        if(checkOut){
            return(
                <div className="popup-box">
                <div className="box">
                  <span className="close-icon" onClick={() => (props.setPopUpOpen(false), setCheckOut(false))}>x</span>
                    <Checkout setBoughtSeats={setBoughtSeats} boughtSeats={boughtSeats} currentUser={props.currentUser} seatsBought={seatsBought} setSeatsBought={setSeatsBought} setCheckOut={setCheckOut} />

                  </div>
                  </div>)
        }
        else if(ourMovie.seats.soldOut){
            return(
                <div className="popup-box">
                <div className="box">
                <span className="close-icon" onClick={() => (props.setPopUpOpen(false), setCheckOut(false))}>x</span>
                    <h1>Theatre Sold Out</h1>
                </div>
                </div>
            )
        }
        else{
    return (
        <div className="popup-box">
          <div className="box">
            <span className="close-icon" onClick={() => props.setPopUpOpen(false)}>x</span>
            <h1>
                Select Seats
            </h1>
            <div className="map">
                {seatingMap.map((row, rowIndex) => (
                    <div className="row">
                    {row.map((seat, seatIndex) => (
                        <div className="seat">
                            {seat === 1? 
                            <div className="previouslySelected"> 
                            <button>
                                X
                            </button>
                            </div>:
                            <div className={seat==2 ? "open":"taken"}>
                            <button onClick={() => (seatClick(rowIndex, seatIndex))}>
                                {seatingMap[rowIndex][seatIndex] ? "x":"o"}
                            </button>
                            </div>}
                            
                            
                        </div>
                    ))}
                    </div>
                ))}
            </div>
            Screen
            <div>
            <button onClick={() => handleSubmit()}>
                Submit
            </button>
            </div>
          </div>
          
        </div>
      );
    }}
    else{
        return <div></div>
    }
}

export default SeatView