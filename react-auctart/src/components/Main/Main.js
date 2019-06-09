import React from 'react';
import './Main.css';
import '../../assets/css/fonts.css';
import '../../assets/img/main-bg.jpg';

class Main extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <div id="main">
                <h1 className="text-right" id="explore-link"><a className="text-white" href="#">Explore</a></h1>
            </div>
        );
    }
}


export default Main;
