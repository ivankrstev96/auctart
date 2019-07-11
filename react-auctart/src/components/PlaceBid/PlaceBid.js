import React from 'react';
import './PlaceBid.css';
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Intro from "../Intro/Intro";
import Register from "../Register/Register";


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

    render() {
        return (
            <>
                <Button variant="primary" onClick={this.handleShow}>
                    Launch demo modal
                </Button>

                <Modal size="lg" show={this.state.show} onHide={this.handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Modal heading</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Register></Register>
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
