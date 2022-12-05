import {React} from 'react'
import UserInput from './UserInput/UserInput'
import Login from './Login/Login'
import Signup from './Signup/Signup'
import './NavBar.css'



const NavBar = (props) => {
  if(props.currentUser){
    return(
      <div className="navBar">
        <div className='title'>
          Movie Services Website
          </div>
        <button onClick={() => props.setCurrentUser(null)}>
          SignOut
        </button>
        </div>
    )
  }

    
    else{
      return (
        <div className="navBar">
            <div className='title'>
                Movie Services Website
            </div>
            <div>
              <Login  setCurrentUser={props.setCurrentUser} /> 
            </div>
            <div>
              <Signup setCurrentUser={props.setCurrentUser} />
            </div>
            
        </div>
      )    
}
}

export default NavBar