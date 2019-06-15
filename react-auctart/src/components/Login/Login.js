import React from 'react';
import './Login.css';
import '../../assets/css/fonts.css';
import {Link} from "react-router-dom";
import {withAuthContext} from "../../context/AuthContext";

class Login extends React.Component {

    constructor(props) {
        super(props);

        console.log(props);
        this.state = {username: "", password: ""};
    }

    login = (event) => {
        event.preventDefault();

        const user = {
            username: this.state.username,
            password: this.state.password
        };
        this.props.login(user);
    }

    onUsernameChange = (event) => {
        this.setState({
            username: event.target.value
        });
    };

    onPasswordChange = (event) => {
        this.setState({
            password: event.target.value
        });
    };

    render() {
        return (
            <div className="container py-5 ">
                <div className="row">
                    <div className="card col-md-10 col-lg-6 mx-auto">
                        <div className="card-body">
                            <form onSubmit={this.login}>
                                <input type="text" className="form-control my-3" id="inputUsername"
                                       placeholder="Username" onChange={this.onUsernameChange}/>
                                <input type="password" className="form-control my-3" id="inputPassword"
                                       placeholder="Password" onChange={this.onPasswordChange}/>
                                <div className="form-check my-2">
                                    <input className="form-check-input" type="checkbox" value="" id="checkStayLogged"/>
                                    <label className="form-check-label" htmlFor="checkStayLogged">
                                        Stay logged in
                                    </label>
                                </div>
                                <button type="submit" className="btn btn-secondary px-4 my-2">Log in</button>
                                <Link to="#" className="d-block link my-2">Forgot password?</Link>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default withAuthContext(Login);
