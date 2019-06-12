import React from 'react';
import './LogIn.css';
import '../../assets/css/fonts.css';
import {Link} from "react-router-dom";

class LogIn extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <div className="container py-5 ">
                <div className="row">
                    <div className="card col-md-10 col-lg-6 mx-auto">
                        <div className="card-body">
                            <form>
                                <input type="text" className="form-control my-3" id="inputUsername"
                                       placeholder="Username"/>
                                <input type="password" className="form-control my-3" id="inputPassword"
                                       placeholder="Password"/>
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


export default LogIn;
