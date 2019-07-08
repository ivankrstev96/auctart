import React from 'react';
import ImageUploader from 'react-images-upload';
import {saveAuction} from "../../service/auctionService";

class UploadAuction extends React.Component {

    constructor(props) {
        super(props);
        this.state = { picture: null };
        this.onDrop = this.onDrop.bind(this);
    }

    onDrop(picture) {
        this.setState({
            picture: picture[picture.length-1],
        });
    }

    submit = () => {
        const auction = {
            name: "test1",
            author: "test1",
            endDate: "23/09/2019 12:30",
            startPrice: 123,
            image: this.state.picture
        };
        console.log("REQUEST", auction);
        saveAuction(auction).then(response => {
            console.log(response);
        });
    };

    render() {
        return (

            <div className="">

            <ImageUploader
                withIcon={true}
                buttonText='Choose images'
                onChange={this.onDrop}
                imgExtension={['.jpg', '.gif', '.png']}
                maxFileSize={5242880}
            />
                <button onClick={this.submit} className="btn btn-outline-secondary">Submit</button>

            </div>
        );
    }
}

export default UploadAuction;