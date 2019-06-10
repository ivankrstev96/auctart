import React, {Component} from 'react';
import './App.css';
import '../../assets/css/fonts.css';
import Menu from "../Menu/Menu.js";
import Intro from "../Intro/Intro";
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import Auctions from "../Auctions/Auctions";



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
                    <Switch>
                        <Route path="/Intro" exact component={Intro} />
                        <Route path="/Auctions" component={Auctions} />
                    </Switch>
                </Router>
            </div>

        );
    }
}

export default App;