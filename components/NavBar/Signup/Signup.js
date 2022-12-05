import {React, useState} from 'react'
import UserInput from '../UserInput/UserInput';


const SignUp = (props) => {
    const [open, setOpen] = useState(false);

    const handleOpen = () => {
        setOpen(!open);
    }

  return (
    <div>
        <button onClick={handleOpen}>
            Sign Up
        </button>
        {open ? (
            <UserInput setCurrentUser={props.setCurrentUser}  signUp={1} />) : null
        }
        
    </div>
  )
}

export default SignUp