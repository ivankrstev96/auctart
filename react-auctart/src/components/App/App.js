import React, {Component} from 'react';
import './App.css';
import '../../assets/css/fonts.css';
import Menu from "../Menu/Menu.js";
import Main from "../Main/Main";

class App extends Component {
    constructor(props) {
        super(props);

    }

    render() {
        return (
            <div id="global">
                <Main/>
            </div>

        );
    }
}

export default App;