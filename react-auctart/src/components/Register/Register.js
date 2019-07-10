import React from 'react';
import './Register.css';
import '../../assets/css/fonts.css';
import {Link, withRouter} from "react-router-dom";
import {registerUser} from "../../service/userService";
import {withNotificationContext} from "../../context/NotificationContext";

class Register extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            username: "",
            email: "",
            confirmEmail: "",
            password: "",
            confirmPassword: "",
            checked: false,

            formErrors: {
                usernameValid: null,
                emailValid: null,
                confirmEmailValid: null,
                passwordValid: null,
                confirmPasswordValid: null,

            },

        }
    }

    onUsernameChange = (event) => {
        const username = event.target.value;

        if (username && username.length > 2 && username.length <= 50) {
            this.setState({
                    username: username,
                    formErrors: {
                        usernameValid: "",
                        emailValid: this.state.formErrors.emailValid,
                        confirmEmailValid: this.state.formErrors.confirmEmailValid,
                        passwordValid: this.state.formErrors.passwordValid,
                        confirmPasswordValid: this.state.confirmPasswordValid,
                    }
                }
            )
        } else {
            this.setState({
                    username: username,
                    formErrors: {
                        usernameValid: "Username must be between 3 and 50 characters.",
                        emailValid: this.state.formErrors.emailValid,
                        confirmEmailValid: this.state.formErrors.confirmEmailValid,
                        passwordValid: this.state.formErrors.passwordValid,
                        confirmPasswordValid: this.state.confirmPasswordValid,
                    }
                }
            )
        }
    };

    onEmailChange = (event) => {
        const email = event.target.value;
        const regex = /.+@.+\..+/;

        if (email.match(regex)) {
            this.setState({
                    email: email,
                    formErrors: {
                        usernameValid: this.state.formErrors.usernameValid,
                        emailValid: "",
                        confirmEmailValid: this.state.formErrors.confirmEmailValid,
                        passwordValid: this.state.formErrors.passwordValid,
                        confirmPasswordValid: this.state.confirmPasswordValid,
                    }
                }
            )
        } else {
            this.setState({
                    email: email,
                    formErrors: {
                        usernameValid: this.state.formErrors.usernameValid,
                        emailValid: "Please enter a valid e-mail address.",
                        confirmEmailValid: this.state.formErrors.confirmEmailValid,
                        passwordValid: this.state.formErrors.passwordValid,
                        confirmPasswordValid: this.state.confirmPasswordValid,
                    }
                }
            )
        }
    };

    onReEmailChange = (event) => {
        const reEmail = event.target.value;

        if (reEmail.match(this.state.email)) {
            this.setState({
                    confirmEmail: reEmail,
                    formErrors: {
                        usernameValid: this.state.formErrors.usernameValid,
                        emailValid: this.state.formErrors.emailValid,
                        confirmEmailValid: "",
                        passwordValid: this.state.formErrors.passwordValid,
                        confirmPasswordValid: this.state.confirmPasswordValid,
                    }
                }
            )
        } else {
            this.setState({
                    confirmEmail: reEmail,
                    formErrors: {
                        usernameValid: this.state.formErrors.usernameValid,
                        emailValid: this.state.formErrors.emailValid,
                        confirmEmailValid: "Email addresses don't match.",
                        passwordValid: this.state.formErrors.passwordValid,
                        confirmPasswordValid: this.state.confirmPasswordValid,
                    }
                }
            )
        }
    };

    onPasswordChange = (event) => {
        const password = event.target.value;

        if (password && password.length >= 6 && password.length <= 50) {
            this.setState({
                    password: password,
                    formErrors: {
                        usernameValid: this.state.formErrors.usernameValid,
                        emailValid: this.state.formErrors.emailValid,
                        confirmEmailValid: this.state.formErrors.confirmEmailValid,
                        passwordValid: "",
                        confirmPasswordValid: this.state.confirmPasswordValid,
                    }
                }
            )
        } else {
            this.setState({
                    password: password,
                    formErrors: {
                        usernameValid: this.state.formErrors.usernameValid,
                        emailValid: this.state.formErrors.emailValid,
                        confirmEmailValid: this.state.formErrors.confirmEmailValid,
                        passwordValid: "Password is not valid length",
                        confirmPasswordValid: this.state.confirmPasswordValid,
                    }
                }
            )
        }
    };

    onRePasswordChange = (event) => {
        const rePassword = event.target.value;

        if (rePassword.match(this.state.password)) {
            this.setState({
                    confirmPassword: rePassword,
                    formErrors: {
                        usernameValid: this.state.formErrors.usernameValid,
                        emailValid: this.state.formErrors.emailValid,
                        confirmEmailValid: this.state.formErrors.confirmEmailValid,
                        passwordValid: this.state.formErrors.passwordValid,
                        confirmPasswordValid: "",
                    }
                }
            )
        } else {
            this.setState({
                    confirmEmail: rePassword,
                    formErrors: {
                        usernameValid: this.state.formErrors.usernameValid,
                        emailValid: this.state.formErrors.emailValid,
                        confirmEmailValid: this.state.formErrors.confirmEmailValid,
                        passwordValid: this.state.formErrors.passwordValid,
                        confirmPasswordValid: "Passwords don't match.",
                    }
                }
            )
        }
    };

    onCheckChange = (event) => {
        const checked = event.target.checked;
        this.setState({
            checked: checked,
        })
    };


    checkValid() {
        return this.state.formErrors.usernameValid === "" &&
            this.state.formErrors.emailValid === "" &&
            this.state.formErrors.confirmEmailValid === "" &&
            this.state.formErrors.passwordValid === "" &&
            this.state.formErrors.confirmPasswordValid === "" &&
            this.state.checked === true
            ;
    }


    submit = (event) => {
        event.preventDefault();
        const user = {
            username: this.state.username,
            password: this.state.password,
            email: this.state.email
        };
        registerUser(user).then(() => {
            this.props.notificationSystem.current.addNotification({
                title: "Success",
                message: "You have successfully registered",
                level: "success",
                autoDismiss: 3
            });
            this.props.history.push("/Login");
        }).catch(() => {
            this.props.notificationSystem.current.addNotification({
                title: "Error",
                message: "Something went wrong",
                level: "error",
                autoDismiss: 5
            });
        });
    };

    render() {

        return (
            <div className="container py-5 ">
                <div className="row">
                    <div className="card col-md-10 col-lg-6 mx-auto">
                        <div className="card-body ">

                            <form>
                                <input type="text" className="form-control my-3" id="inputUsername"
                                       placeholder="Username" onChange={this.onUsernameChange}/>
                                {
                                    (this.state.formErrors.usernameValid && this.state.formErrors.usernameValid === "") ?
                                        "" :
                                        (<p className="error">{this.state.formErrors.usernameValid}</p>)
                                }
                                <input type="email" className="form-control my-3" id="inputEmail" placeholder="E-mail"
                                       onChange={this.onEmailChange}/>
                                {
                                    (this.state.formErrors.emailValid && this.state.formErrors.emailValid === "") ?
                                        "" :
                                        (<p className="error">{this.state.formErrors.emailValid}</p>)
                                }
                                <input type="email" className="form-control my-3" id="inputEmailRe"
                                       placeholder="Confirm e-mail" onChange={this.onReEmailChange}/>
                                {
                                    (this.state.formErrors.confirmEmailValid && this.state.formErrors.confirmEmailValid === "") ?
                                        "" :
                                        (<p className="error">{this.state.formErrors.confirmEmailValid}</p>)
                                }
                                <input type="password" className="form-control my-3" id="inputPassword"
                                       placeholder="Password" onChange={this.onPasswordChange}/>
                                {
                                    (this.state.formErrors.passwordValid && this.state.formErrors.passwordValid === "") ?
                                        "" :
                                        (<p className="error">{this.state.formErrors.passwordValid}</p>)
                                }
                                <input type="password" className="form-control my-3" id="inputPasswordRe"
                                       placeholder="Confirm password" onChange={this.onRePasswordChange}/>
                                {
                                    (this.state.formErrors.confirmPasswordValid && this.state.formErrors.confirmPasswordValid === "") ?
                                        "" :
                                        (<p className="error">{this.state.formErrors.confirmPasswordValid}</p>)
                                }
                                <div className="form-check my-2">
                                    <input className="form-check-input " type="checkbox" id="checkStayLogged"
                                           onChange={this.onCheckChange}/>
                                    <label className="form-check-label small" htmlFor="checkStayLogged">
                                        I have read and agree to the AuctArt&nbsp;
                                        <Link to="#" className="link">Terms of Service</Link>
                                        .
                                    </label>
                                </div>
                                <button type="submit" className="btn btn-secondary px-3 my-2"
                                        disabled={!this.checkValid()}
                                        onClick={this.submit}>
                                    Register
                                </button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


export default withNotificationContext(withRouter(Register));
