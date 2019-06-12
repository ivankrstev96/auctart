import React, {Component} from 'react';
import './App.css';
import '../../assets/css/fonts.css';
import Menu from "../Menu/Menu.js";
import Intro from "../Intro/Intro";
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import Auctions from "../Auctions/Auctions";
import LogIn from "../LogIn/LogIn";
import Register from "../Register/Register";



class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentPage: 'thisPage'
        };

    }

    render() {
        var currentLocation = window.location.pathname;
        console.log(currentLocation);

        let conditionalMenu;
        if(currentLocation !== '/Intro') {
            conditionalMenu = <Menu/>;
        }

        return (
            <div id="global">
                <Router>
                    <Menu/>
                    <Switch>
                        <Route path="/" exact component={Intro} />
                        <Route path="/Auctions" component={Auctions} />
                        <Route path="/LogIn" component={LogIn} />
                        <Route path="/Register" component={Register} />

                    </Switch>
                </Router>
            </div>

        );
    }
}

export default App;