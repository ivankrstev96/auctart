import React from 'react';
import '../../assets/css/fonts.css';
import Menu from "../App/App";
import {BrowserRouter as Router} from "react-router-dom";

class Auctions extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <div>
                <Menu/>
                <h1>This is auctions page</h1>
            </div>
        );
    }
}


export default Auctions;
