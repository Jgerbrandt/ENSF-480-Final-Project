import {React, useState} from 'react'
import './DateDropDown.css'


const DateDropDown = (props) => {
    const [dropOn, setDropOn] = useState(false);
    const [selected, setSelected] = useState(false);
    const movieDates = []
    props.movies.map((movie) => 
        {if((movie.movie === props.title) && (!movieDates.includes(movie.releaseDate))){
            movieDates.push(movie.releaseDate);
        }}
    );

    const handleClick = (date) => {
      props.daySelect(date)
      setSelected(date)
      setDropOn(false)
      
    }


  return (

    <div>
      <button onClick={() => (setDropOn(!dropOn), setSelected(false))}>
        {selected ? (selected): ("Select Day")}
      </button>

        {movieDates.map((date, index) => (dropOn ? (
          

          <div key={index} className={"menu-items"}>
            <button onClick={() => handleClick(date)}>
            {date}
            </button>
            
          </div>):(<div></div>)
        )

        )}
    </div>
  )
}

export default DateDropDown