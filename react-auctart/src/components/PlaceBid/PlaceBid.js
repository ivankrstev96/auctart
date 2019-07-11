import React from 'react';
import './PlaceBid.css';
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import {getHighestBidForAuction} from "../../service/auctionService";


class PlaceBid extends React.Component {
    constructor(props, context) {
        super(props, context);
        const {auction} = props;

        getHighestBidForAuction(auction.id).then((result) => {
            if(result.status === 204) {
                this.setState({
                    highestBidValue: auction.startPrice,
                    hasBids: false
                });
            } else if(result.status === 200) {
                this.setState({
                    highestBidValue: result.price,
                    hasBids: true
                });
            }
        }).catch(() => {

        });

        this.state = {
            show: false,
            highestBidValue: null,
            hasBids: false
        };
    }

    handleClose = () => {
        this.setState({show: false});
    };

    handleShow = () => {
        this.setState({show: true});
    };

    handlePlaceBid = () => {
        
    };

    renderModalBody = () => {
        const {auction} = this.props;
        return (
            <div>
                <img className="cropped-image mx-auto" src={`/api/image/public/${auction.id}`}/>
                <div className="input-group mb-3">
                    <input type="number" min={this.state.highestBidValue} className="form-control"
                           placeholder="Place your bid here"/>
                    <div className="input-group-append">
                        <button className="btn btn-outline-secondary" onClick={this.handlePlaceBid}>
                            Bid
                        </button>
                    </div>
                </div>


            </div>
        );
    };

    render() {
        const {auction} = this.props;
        return (
            <>
                <button className="btn btn-outline-secondary" onClick={this.handleShow}>Place bid</button>

                <Modal size="lg" show={this.state.show} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>
                            <h3 className="d-inline-block">{auction.name}</h3>
                            <h5 className="d-inline-block pl-2">by {auction.author}</h5>
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        {this.renderModalBody()}

                    </Modal.Body>
                </Modal>
            </>
        );
    }

}

export default PlaceBid
