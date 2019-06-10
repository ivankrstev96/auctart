import React from 'react';
import './Intro.css';
import '../../assets/css/fonts.css';
import '../../assets/img/main-bg.jpg';
import {Link} from "react-router-dom";

class Intro extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <div id="main">
                <h1 className="text-right" id="explore-link">
                    <Link to="/Auctions" className="text-white" >Explore</Link>
                </h1>
            </div>
        );
    }
}


export default Intro;
