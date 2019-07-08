import React from 'react';
import { Button, Header, Image, Modal } from 'semantic-ui-react';
import './PlaceBid.css';


const PlaceBid = () => (
    <Modal className="modal" centered trigger={<Button className="btn btn-outline-secondary float-right">Show Modal</Button>}>
        <Modal.Header>Select a Photo</Modal.Header>
        <Modal.Content image>
            <Image wrapped size='medium' src='' />
            <Modal.Description>
                <Header>Default Profile Image</Header>

                <p>Current highest bid</p>
                <p>Insert your bid:</p>


            </Modal.Description>
        </Modal.Content>
    </Modal>
);

export default PlaceBid
