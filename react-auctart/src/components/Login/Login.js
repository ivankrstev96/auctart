import React from 'react';
import './Login.css';
import '../../assets/css/fonts.css';
import {withAuthContext} from "../../context/AuthContext";
import {withRouter} from "react-router-dom";

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {username: "", password: "", error: null};
    }

    login = (event) => {
        event.preventDefault();

        const user = {
            username: this.state.username,
            password: this.state.password
        };
        this.props.login(user)
            .then(() => {
                this.props.history.push("/Auctions");
            })
            .catch(() => {
                this.setState({error: true});
            });
    };

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


                            {this.props.isAuthenticated ? (
                                <p>You are logged in as {this.state.username}</p>
                            ) : (
                                <form onSubmit={this.login}>
                                    <input type="text" className="form-control my-3" id="inputUsername"
                                           placeholder="Username" onChange={this.onUsernameChange}/>
                                    <input type="password" className="form-control my-3" id="inputPassword"
                                           placeholder="Password" onChange={this.onPasswordChange}/>
                                    {this.state.error && <p className="text-danger">Incorrect username or password</p>}
                                    <button type="submit" className="btn btn-secondary px-4 my-2">Log in</button>
                                </form>
                            )}

                        </div>
                    </div>

                </div>
            </div>
        );
    }
}

export default withRouter(withAuthContext(Login));
