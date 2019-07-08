import React from 'react';
import ImageUploader from 'react-images-upload';
import {saveAuction} from "../../service/auctionService";

class UploadAuction extends React.Component {

    constructor(props) {
        super(props);
        this.state = {picture: null};
        this.onDrop = this.onDrop.bind(this);
    }

    onDrop(picture) {
        this.setState({
            picture: picture[picture.length - 1],
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

            <div className="container">

                <ImageUploader
                    withIcon={true}
                    buttonText='Choose images'
                    onChange={this.onDrop}
                    imgExtension={['.jpg', '.gif', '.png']}
                    maxFileSize={5242880}
                    withPreview={true}
                    singleImage={true}
                />

                <form>
                    <div className="row">

                        <div className="col-md-6 form-group">
                            <label htmlFor="nameAuction">name author enddate startprice</label>
                            <input type="text" className="form-control" id="nameAuction"/>
                        </div>
                        <div className="col-md-6 form-group">
                            <label htmlFor="authorAuction">name author enddate startprice</label>
                            <input type="text" className="form-control" id="nameAuction"/>
                        </div>
                        <div className="col-md-6 form-group">
                            <label htmlFor="authorAuction">name author enddate startprice</label>
                            <input type="text" className="form-control" id="nameAuction"/>
                        </div>

                        <div className="col-12">
                            <button onClick={this.submit} className="btn btn-outline-secondary">Submit</button>
                        </div>
                    </div>

                </form>

            </div>
        );
    }
}

export default UploadAuction;