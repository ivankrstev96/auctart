import React from 'react';
import './PlaceBid.css';
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";


class PlaceBid extends React.Component {
    constructor(props, context) {
        super(props, context);

        this.state = {
            show: false,
        };
    }

    handleClose = () => {
        this.setState({ show: false });
    };

    handleShow = () => {
        this.setState({ show: true });
    };

    renderModalBody = () => {
        const {auction} = this.props;

        return (
            <img className="cropped-image " src={`/api/image/public/${auction.id}`}/>
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
                    <Modal.Footer>
                        <Button variant="secondary" onClick={this.handleClose}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={this.handleClose}>
                            Save Changes
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
        );
    }
}

export default PlaceBid
