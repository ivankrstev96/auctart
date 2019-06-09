import React from 'react';
import './Menu.css';
import '../../assets/css/fonts.css';

class Menu extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <nav className="navbar navbar-expand-md navbar-light border-bottom menu">
                <a className="navbar-brand logo text-dark ml-3 mr-5" href="#"><h2>AuctArt</h2></a>
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
                                        <a href="#" className="btn btn-outline-secondary rounded-left"
                                           id="searchBarPrepend"><i className="fas fa-search"></i></a>
                                    </div>
                                    <input type="search" className="form-control" id="searchBar"
                                           placeholder="Search artwork"/>
                                </div>
                            </form>
                        </li>
                    </ul>

                    <form className="form-inline my-2 float-right">
                        <a href="#" className="btn btn-outline-secondary mr-2 log-in-btn">Log in</a>
                        <a href="#" className="btn btn-outline-secondary register-btn">Register</a>
                    </form>
                </div>

            </nav>
        );
    }
}


export default Menu;
