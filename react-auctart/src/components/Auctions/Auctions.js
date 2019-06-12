import React from 'react';
import '../../assets/css/fonts.css';
import './Auctions.css'
import img1 from '../../assets/img/main-bg.jpg';
import img2 from '../../assets/img/vertical-painting.png';

class Auctions extends React.Component {

    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <div className="container py-5">
                <div className="row">

                    <div className="card">
                        <div className="card-body row">
                            <div className="col-4">
                                <div className="img-holder">
                                    <img className="card-img" src={img1}/>
                                </div>
                            </div>
                            <div className="col-8 img-holder">
                                Soemthing
                            </div>
                        </div>
                    </div>

                    <div className="card">
                        <div className="card-body row">
                            <div className="col-4">
                                <div className="img-holder">
                                    <img className="card-img" src={img2}/>
                                </div>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        );
    }
}


export default Auctions;
