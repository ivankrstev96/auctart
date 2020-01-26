import React from 'react';
import './Menu.css';
import '../../assets/css/fonts.css';
import {Link} from "react-router-dom";

import defaultuserimg from "../../assets/img/defaultuser.png";

import {withAuthContext} from "../../context/AuthContext";
import {withSearchContext} from "../../context/SearchContext";

class Menu extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    logout = () => {
        this.props.logout();
    };

    updateSearchQuery = (event) => {
        this.props.updateSearchQuery(event.target.value);
    };

    render() {
        return (
            <nav className="navbar navbar-expand-md navbar-light border-bottom menu">
                <Link to="/" className="navbar-brand logo text-dark ml-3 mr-5"><h2>AuctArt</h2></Link>
                <button className="navbar-toggler border-0 px-3 py-1" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <i className="fas fa-caret-down"></i>
                </button>

                <div className="collapse navbar-collapse" id="navbarSupportedContent">

                    <ul className="navbar-nav mr-auto">

                        <li className="nav-item">
                            <form className="form-group my-2 my-lg-0">
                                <div className="input-group ">
                                    <div className="input-group-prepend">
                                        <Link to="/Auctions" className="btn btn-outline-secondary rounded-left"
                                           id="searchBarPrepend"><i className="fas fa-search"></i></Link>
                                    </div>
                                    <input type="search" className="form-control" id="searchBar"
                                           placeholder="Search artwork" onChange={this.updateSearchQuery}/>
                                </div>
                            </form>
                        </li>
                    </ul>
                    { this.props.isAuthenticated ? (
                        <form className="form-inline my-2 float-right">

                            <div className="dropleft">
                                <Link to="#" className="" data-toggle="dropdown">
                                    <img src={defaultuserimg}  alt="user"className="img-fluid rounded user-img"/>
                                </Link>

                                <div className="dropdown-menu">
                                    <Link to="/UploadAuction" className="dropdown-item">Upload auction</Link>
                                    <Link to="/" className="dropdown-item" onClick={this.logout}>Log out</Link>
                                </div>

                            </div>
                        </form>
                    ) : (
                        <form className="form-inline my-2 float-right">
                            <Link to="/Login" className="btn btn-outline-secondary mr-2 px-4 log-in-btn">Log in</Link>
                            <Link to="/Register" className="btn btn-outline-secondary register-btn px-3">Register</Link>
                        </form>
                    )}

                </div>

            </nav>
        );
    }
}


export default withSearchContext(withAuthContext(Menu));
