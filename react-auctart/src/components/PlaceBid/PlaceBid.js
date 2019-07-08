import React from 'react';
import { Button, Header, Image, Modal } from 'semantic-ui-react';
import 'semantic-ui-css/semantic.min.css';


const PlaceBid = () => (
    <Modal className="modal" trigger={<Button className="btn btn-outline-secondary float-right">Show Modal</Button>}>
        <Modal.Header>Select a Photo</Modal.Header>
        <Modal.Content image>
            <Image wrapped size='medium' src='/images/avatar/large/rachel.png' />
            <Modal.Description>
                <Header>Default Profile Image</Header>
                <p>We've found the following gravatar image associated with your e-mail address.</p>
                <p>Is it okay to use this photo?</p>
            </Modal.Description>
        </Modal.Content>
    </Modal>
);

export default PlaceBid
