import {React, useState} from 'react'
import './ShowTimeDropDown.css'


const ShowTimeDropDown = (props) => {
    const [dropOn, setDropOn] = useState(false);
    const [selected, setSelected] = useState(false);
    const movieTimes = []
    props.movies.map((movie) => 
        {if((movie.movie === props.title) && (!movieTimes.includes(movie.time) && (props.selectedDay === movie.releaseDate))){
            movieTimes.push(movie.time);
        }}
    );
    const handleClick = (time) => {
      props.timeSelect(time)
      setSelected(time)
      setDropOn(false)
      
    }

  if(props.selectedDay){
  return (

    <div>
      <button onClick={() => (setDropOn(!dropOn), setSelected(false))}>
        {selected ? (selected): ("Select Time")}
      </button>

        {movieTimes.map((time, index) => (dropOn ? (
          

          <div key={index} className={"menu-items"}>
            <button onClick={() => handleClick(time)}>
            {time}
            </button>
            
          </div>):(<div></div>)
        )

        )}
    </div>
  )}
  else{
    return(
      <div>
      <button onClick={() => (setDropOn(!dropOn), setSelected(false))}>
        {selected ? (selected): ("Select Time")}
      </button>
      </div>
    )
  }
}

export default ShowTimeDropDown