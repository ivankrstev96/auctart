import React from 'react';
import './Register.css';
import '../../assets/css/fonts.css';
import {Link} from "react-router-dom";

class Register extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <div className="container py-5 ">
                <div className="row">
                    <div className="card col-md-10 col-lg-6 mx-auto">
                        <div className="card-body ">
                            <form>
                                <input type="text" className="form-control my-3" id="inputUsername"
                                       placeholder="Username"/>
                                <input type="email" className="form-control my-3" id="inputEmail" placeholder="E-mail"/>
                                <input type="email" className="form-control my-3" id="inputEmailRe"
                                       placeholder="Confirm e-mail"/>
                                <input type="password" className="form-control my-3" id="inputPassword"
                                       placeholder="Password"/>
                                <input type="password" className="form-control my-3" id="inputPasswordRe"
                                       placeholder="Confirm password"/>
                                <div className="form-check my-2">
                                    <input className="form-check-input " type="checkbox" value="" id="checkStayLogged"/>
                                    <label className="form-check-label small" htmlFor="checkStayLogged">
                                        I have read and agree to the AuctArt&nbsp;
                                        <Link to="#" className="link">Terms of Service</Link>
                                        .
                                    </label>
                                </div>
                                <button type="submit" className="btn btn-secondary px-3 my-2">Register</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


export default Register;
