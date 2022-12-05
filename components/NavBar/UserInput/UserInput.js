import React from 'react'
import UserService from '../../services/UserService';
import './UserInput.css';

class UserInput extends React.Component {
    constructor(props){
        super(props);
        this.state={
            name: "",
            card: "",
            address: "",
            email: "",
            password: "",
        }
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    }

    render(){

        return (
            
        <div>
            <div className = 'input__form'>
                <form onSubmit={this.handleSubmit}>
                    {this.props.login ? (
                    <div className="loginValues">
                        <input type='text' id="email" placeholder='Email' onChange={this.handleChange}/>
                        <input type='text' id="Password" placeholder='Password' onChange={this.handleChange}/>
                    </div>)
                    :
                    <div className="SignupValues">
                        <input type='text' id="name" placeholder='Name' onChange={this.handleChange}/>
                        <input type='text' id="card" placeholder='Credit Card Number' onChange={this.handleChange}/>
                        <input type='text' id="address" placeholder='Address' onChange={this.handleChange}/>
                        <input type='text' id="email" placeholder='Email' onChange={this.handleChange}/>
                        <input type='text' id="Password" placeholder='Password' onChange={this.handleChange}/>
                    </div>
                    }
                    <input type='submit' value='Submit'/> 
                </form> 
            </div> 
        </div>
    )
   
    }


    handleChange(event){
        if(event.target.id === 'name'){
            this.setState({name: event.target.value});
        }
        else if(event.target.id === 'card'){
            this.setState({card: event.target.value});
        }
        else if(event.target.id === 'address'){
            this.setState({address: event.target.value});
        }
        else if(event.target.id === 'email'){
            this.setState({email: event.target.value});
        }
        else if(event.target.id === 'Password'){
            this.setState({password: event.target.value});
        }
        
    }


    async handleSubmit(event){
        event.preventDefault();
        
        //API POST HERE
        if(this.props.login){
            if(this.state.email && this.state.password){
                //let oldUsers = UserService.get();
                UserService.postLogin(this.state.email, this.state.password);
                await UserService.getUsers().then((response => 
                    (
                        response.data.email == this.state.email ? 
                    (this.props.setCurrentUser(response.data)) : null
                )))
                

                

                //if(oldUsers !== UserService.get()){
                //this.props.setLoggedIn(true);
            }
        }
        else{
            if(this.state.name && this.state.card && this.state.address && this.state.email && this.state.password){
                let newUser = {name: this.state.name, address: this.state.address, email: this.state.email, password: this.state.password, card: this.state.card}
                UserService.post(newUser)
                this.props.setCurrentUser(newUser)
                
            }
        }
        this.props.setUpdate(this.props.update + 1);
        }
        
    }
        
        /*
        fetch('/api', {
            method: 'POST',
            headers:{
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin':  'http://127.0.0.1:3000',
                'Access-Control-Allow-Methods': 'POST',
                'Access-Control-Allow-Headers': 'Content-Type, Authorization' 
            },
            body: JSON.stringify(this.state.value)
            }).then(response => (response.json()))
            .catch(error => console.log(error))
        */   
    

export default UserInput