import {React, useState} from 'react'
import './ViewSeats.css'
import SeatView from './SeatView/SeatView'

const ViewSeats = (props) => {
  const [clicked, setClicked] = useState(false);
  const [popUpOpen, setPopUpOpen] = useState(false);
  


  if(props.title && props.day && props.time){
    return(
      <div>
        <button onClick={() => (setClicked(true), setPopUpOpen(!popUpOpen))}>
          Select Seats
          
        </button>
        {clicked ? <SeatView currentUser={props.currentUser} movies={props.movies} title={props.title} date={props.day} time={props.time} popUpOpen={popUpOpen} setPopUpOpen={setPopUpOpen}/>:<div></div>}
      </div>
    )
  }
  else{
    return(
      <div>
        <button>
          Select Seats
        </button>
      </div>
    )
  }

}

export default ViewSeats