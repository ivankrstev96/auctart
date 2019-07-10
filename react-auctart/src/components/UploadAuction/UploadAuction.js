import React from 'react';
import ImageUploader from 'react-images-upload';
import {saveAuction} from "../../service/auctionService";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./UploadAuction.css";

class UploadAuction extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            image: null,
            name: null,
            author: null,
            endDate: null,
            startPrice: null
        };
        this.onDrop = this.onDrop.bind(this);
        this.onDateChange = this.onDateChange.bind(this);
        this.onNameChange = this.onNameChange.bind(this);
        this.onAuthorChange = this.onAuthorChange.bind(this);
        this.onPriceChange = this.onPriceChange.bind(this);
    }

    onDrop(image) {
        this.setState({
            name: this.state.name,
            author: this.state.author,
            image: image[image.length - 1],
            endDate: this.state.endDate,
            startPrice: this.state.startPrice
        });
    }

    onNameChange(e) {
        let name = e.target.value;
        this.setState({
            name: name,
            author: this.state.author,
            image: this.state.image,
            endDate: this.state.endDate,
            startPrice: this.state.startPrice
        })
    }

    onAuthorChange(e) {
        let author = e.target.value;
        this.setState({
            name: this.state.name,
            author: author,
            image: this.state.image,
            endDate: this.state.endDate,
            startPrice: this.state.startPrice
        })
    }

    onDateChange(date) {
        this.setState({
            name: this.state.name,
            author: this.state.author,
            image: this.state.image,
            endDate: date,
            startPrice: this.state.startPrice
        });
    }

    onPriceChange(e) {
        let price = e.target.value;
        this.setState({
            name: this.state.name,
            author: this.state.author,
            image: this.state.image,
            endDate: this.state.endDate,
            startPrice: price
        });
    }

    submit = (e) => {
        e.preventDefault();
        const auction = {
                image: this.state.image,
                name: this.state.name,
                author: this.state.author,
                endDate: this.formatDate(this.state.endDate),
                startPrice: this.state.startPrice
            };
        console.log("REQUEST", auction);
        saveAuction(auction).then(response => {
            console.log(response);
        }).catch(() => {

        });
    };

    disableButton = () => {
        return(
            this.state.image === null ||
            this.state.name === null || this.state.name === "" ||
            this.state.author === null || this.state.author === "" ||
            this.state.endDate === null || this.state.startPrice === null
        );
    };

    formatDate = (d) => {
        return ("0" + d.getDate()).slice(-2) + "/" + ("0"+(d.getMonth()+1)).slice(-2) + "/" +
            d.getFullYear() + " " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2);
    };

    render() {
        console.log(this.disableButton());
        return (

            <div className="container">

                <ImageUploader
                    className="my-4"
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
                            <label htmlFor="nameAuction">Name/description of artwork</label>
                            <input type="text" className="form-control" id="nameAuction" onChange={this.onNameChange}/>
                        </div>
                        <div className="col-md-6 form-group">
                            <label htmlFor="authorAuction">Author of artwork</label>
                            <input type="text" className="form-control" id="authorAuction" onChange={this.onAuthorChange}/>
                        </div>

                        <div className="col-md-6 form-group">
                            <label htmlFor="enddateAuction">End date of auction</label>
                            <DatePicker
                                className="form-control"
                                id="enddateAuction"
                                selected={this.state.endDate}
                                onChange={this.onDateChange}
                                dateFormat='dd/MM/yyyy'
                                minDate={new Date()}
                            />
                        </div>
                        <div className="col-md-6 form-group">
                            <label htmlFor="startpriceAuction">Starting price</label>
                            <input type="number" className="form-control" placeholder="USD"
                                   id="startpriceAuction" onChange={this.onPriceChange}/>
                        </div>

                        <div className="col-12">
                            <button onClick={this.submit} className="btn btn-outline-secondary my-3" disabled={this.disableButton()}>
                                Upload auction
                            </button>
                        </div>
                    </div>

                </form>

            </div>
        );
    }

}

export default UploadAuction;