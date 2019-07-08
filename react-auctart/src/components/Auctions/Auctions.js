import React from 'react';
import '../../assets/css/fonts.css';
import './Auctions.css'
import img1 from '../../assets/img/main-bg.jpg';
import img2 from '../../assets/img/vertical-painting.png';
import { Link } from "react-router-dom";
import { Modal } from "semantic-ui-react";
import PlaceBid from "../PlaceBid/PlaceBid"

class Auctions extends React.Component {
    constructor(props) {
        super(props);
        this.state = {}
    }

    render() {
        return (
            <div className="container">
                <table className="table w-100 my-5">
                    <tbody>

                    <tr>
                        <td className="td-w-img">
                            <img className="cropped-image " src={img1}/>
                        </td>
                        <td>
                            <h4>
                                Name of art work&nbsp;
                                <sub>
                                    by Author
                                </sub>
                            </h4>
                            <h2>
                                $6,250
                            </h2>
                            <div>
                                <PlaceBid/>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td className="td-w-img">
                            <img className="cropped-image " src={img2}/>
                        </td>
                        <td>
                            <h4>
                                Name of art work&nbsp;
                                <sub>
                                    by Author
                                </sub>
                            </h4>
                            <h2>
                                $6,250
                            </h2>
                            <button className="btn btn-outline-secondary float-right">Place bid</button>
                        </td>
                    </tr>

                    </tbody>
                    <tfoot>
                    <tr>
                        <td colSpan="2">
                            <nav aria-label="Page navigation example">
                                <ul className="pagination pag-style mx-auto mt-3 ">
                                    <li className="page-item ">
                                        <a className="page-link text-dark" href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <li className="page-item"><a className="page-link text-dark" href="#">1</a></li>
                                    <li className="page-item"><a className="page-link text-dark" href="#">2</a></li>
                                    <li className="page-item"><a className="page-link text-dark" href="#">3</a></li>
                                    <li className="page-item">
                                        <a className="page-link text-dark" href="#" aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>

        );
    }
}


export default Auctions;
