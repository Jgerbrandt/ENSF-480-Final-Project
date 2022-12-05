import {React, useState} from 'react'
import OrderService from '../../../../services/OrderService';

const Checkout = (props) => {
    const [email, setEmail] = useState();


    const handleSubmit = () => {
        if(props.currentUser){
            setEmail(props.currentUser.email)
        }

        let ticketArr = []
            props.boughtSeats.forEach(seatPair => {
                let ticket = {id: 0, movieName: props.ourMovie.movie, seatColumn: seatPair[0], seatRow: seatPair[1], time: props.ourMovie.time }
                ticketArr.push(ticket)
            })
        let order = {OrderID: 0, email: email, tickets: ticketArr}
        OrderService.post(order);



    
            props.setSeatsBought(0)
            props.setCheckOut(0)
            props.setBoughtSeats([])
        }

    if(props.currentUser)
  return (
    <div>
        <div>
            Cost: ${parseInt(props.seatsBought) * 10}.00
        </div>
        <div>
            Email Receipt To: {props.currentUser.email}
        </div>
        <div>
            Payment To: {props.currentUser.creditCardNum}
        </div>
        <button onClick={() => (handleSubmit())}>
            Pay
        </button>
    </div>
  )
  else{
      return(
          <div>
        
      <div>
          <h1>
          Enter Details
          </h1>
          <div>
              <div>Bought Seats:{props.boughtSeats.map((seat) => (<div> {seat} </div>))}</div>
            Cost: ${parseInt(props.seatsBought) * 10}.00
        </div>
        <form>
          <input type='text' id="Email" placeholder='Email' onChange={(e) => setEmail(e.target.value)}/>
          <input type='text' id="Card" placeholder='Credit Card #'/>
          <button onClick={() => handleSubmit()} >
            Pay
            </button>
        </form>
      </div>
      </div>
      )
  }
}



export default Checkout