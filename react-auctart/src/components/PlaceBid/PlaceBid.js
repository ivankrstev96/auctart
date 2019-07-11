import React from 'react';
import './PlaceBid.css';
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import {getHighestBidForAuction} from "../../service/auctionService";
import {withNotificationContext} from "../../context/NotificationContext";
import {saveBid} from "../../service/bidService";
import {withRouter} from "react-router-dom";

class PlaceBid extends React.Component {
    constructor(props, context) {
        super(props, context);
        this.calcHighestBid();

        this.state = {
            show: false,
            highestBidValue: null,
            hasBids: false,
            yourBid: null
        };
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(prevProps !== this.props) {
            this.calcHighestBid();
        }
    }

    calcHighestBid = () => {
        const {auction} = this.props;
        getHighestBidForAuction(auction.id).then((result) => {
            if (result.status === 204) {
                this.setState({
                    highestBidValue: auction.startPrice,
                    hasBids: false
                });
            } else if (result.status === 200) {
                console.log("res: ", result);
                this.setState({
                    highestBidValue: result.data.price,
                    hasBids: true
                });
            }
        }).catch(() => {

        });
    };

    componentDidUpdate(prevProps, prevState, snapshot) {
        console.log(prevState.highestBidValue, this.state.highestBidValue);
    }

    handleClose = () => {
        this.setState({show: false});
    };

    handleShow = () => {
        this.setState({show: true});
    };

    onInputChange = (event) => {
        let bid = event.target.value;
        this.setState({
            yourBid: bid
        })
    };

    handlePlaceBid = (event) => {
        event.preventDefault();
        const {auction} = this.props;
        const bid = {
            auction: auction.id,
            price: +this.state.yourBid
        };
        console.log(bid);
        saveBid(bid).then(() => {
            this.props.notificationSystem.current.addNotification({
                title: "Success",
                message: "You have successfully bid for " + auction.name,
                level: "success",
                autoDismiss: 3
            });
            this.props.updateAuctions();
        }).catch(() => {
            this.props.notificationSystem.current.addNotification({
                title: "Error",
                message: "Something went wrong",
                level: "error",
                autoDismiss: 5
            });
        });
    };

    renderModalBody = () => {
        const {auction} = this.props;
        return (
            <div className="row">
                <img className="col-12 cropped-image mx-auto" src={`/api/image/public/${auction.id}`}/>

                <div className="col-12 my-3 mx-1">
                    <h5>This auction ends on {auction.endDate.slice(0,10)}</h5>
                    <h4>{this.state.hasBids? "Current highest bid: " + this.state.highestBidValue : "Starting price: " + this.state.highestBidValue} </h4>
                    <p className="font-italic">{this.state.hasBids? "" : "Be the first to bid on this item!"}</p>
                </div>
                <div className="col-6 input-group mx-auto my-3">
                    <input type="number" min={this.state.highestBidValue} onChange={this.onInputChange}
                           className="form-control"
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

export default withNotificationContext(withRouter(PlaceBid));
