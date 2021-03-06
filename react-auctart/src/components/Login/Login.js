import React from 'react';
import './Login.css';
import '../../assets/css/fonts.css';
import {withAuthContext} from "../../context/AuthContext";
import {withRouter} from "react-router-dom";
import {withNotificationContext} from "../../context/NotificationContext";

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {username: "", password: ""};
    }

    login = (event) => {
        event.preventDefault();

        const user = {
            username: this.state.username,
            password: this.state.password
        };
        this.props.login(user)
            .then(() => {
                this.props.notificationSystem.current.addNotification({
                    title: "Success",
                    message: "Login was successful",
                    level: "success",
                    autoDismiss: 3
                });
                this.props.history.push("/Auctions");
            })
            .catch(() => {
                this.props.notificationSystem.current.addNotification({
                    title: "Failure",
                    message: "Incorrect username or password",
                    level: "error",
                    autoDismiss: 5
                });
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

export default withNotificationContext(withRouter(withAuthContext(Login)));
