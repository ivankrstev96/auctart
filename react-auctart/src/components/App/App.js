import React, {Component} from 'react';
import './App.css';
import '../../assets/css/fonts.css';
import Menu from "../Menu/Menu.js";
import Intro from "../Intro/Intro";
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import Auctions from "../Auctions/Auctions";
import Login from "../Login/Login";
import Register from "../Register/Register";
import UploadAuction from "../UploadAuction/UploadAuction";
import {AuthProvider} from "../../context/AuthContext";
import PlaceBid from "../PlaceBid/PlaceBid";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentPage: 'thisPage'
        };

    }

    render() {
        return (
            <div id="global">
                <AuthProvider>
                    <Router>
                        <Menu/>
                        <Switch>
                            <Route path="/" exact component={Intro} />
                            <Route path="/Auctions" component={Auctions} />
                            <Route path="/Login" component={Login} />
                            <Route path="/Register" component={Register} />
                            <Route path="/UploadAuction" component={UploadAuction} />
                        </Switch>
                    </Router>
                </AuthProvider>
            </div>
        );
    }
}

export default App;