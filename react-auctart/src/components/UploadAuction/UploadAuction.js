import React from 'react';
import ImageUploader from 'react-images-upload';

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

            </div>
        );
    }
}

export default UploadAuction;