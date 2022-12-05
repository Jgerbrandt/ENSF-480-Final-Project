import {React, useState} from 'react'
import UserInput from '../UserInput/UserInput';


const Login = (props) => {
    const [open, setOpen] = useState(false);

    const handleOpen = () => {
        setOpen(!open);
    }


  return (
    <div>
        <button onClick={handleOpen}>
            Login
        </button>
        {open ? (
            <UserInput  setCurrentUser={props.setCurrentUser}  login={1}  />) : null
        }
        
    </div>
  )
}

export default Login