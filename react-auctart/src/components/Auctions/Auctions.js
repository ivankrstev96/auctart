import React from 'react';
import '../../assets/css/fonts.css';
import './Auctions.css'
import PlaceBid from "../PlaceBid/PlaceBid"
import {getActiveAuctions} from "../../service/auctionService";

class Auctions extends React.Component {
    constructor(props) {
        getActiveAuctions().then(auctions => {
            this.setState({
                auctions: auctions,
                currentPage: 1,
                auctionsPerPage: 3
            });

        });
        super(props);
        this.state = {auctions: []}
    }

    handlePageClick = (e) => {
        console.log(e.target.id);
        this.setState({
            auctions: this.state.auctions,
            currentPage: Number(e.target.id),
            auctionsPerPage: this.state.auctionsPerPage
        });
    };

    preview = (auctions) => {
        auctions = this.state.auctions;
        let table = [];

        for (let i = 0; i < auctions.length; i++) {

         //   table.push(<tr key={i}>{children}</tr>)
        }

        //return table;
    };

    previewAuction = (auction, auctions) => {
        const children = [];
        const index = auctions.indexOf(auction);
        children.push(
            <td key={"td1_"+index} className="td-w-img d-table-cell align-middle">
                <img className="cropped-image " src={`/api/image/public/${auction.id}`}/>
            </td>
        );
        children.push(
            <td key={"td2_"+index} className="py-4">
                <h4>
                    {auction.name}&nbsp;
                </h4>
                <h3>
                    <sub>
                        by {auction.author}
                    </sub>
                </h3>
                <h2>
                    ${auction.startPrice}
                </h2>
                <div className="float-to-bottom float-right">
                    <PlaceBid/>
                </div>
            </td>
        );
        return children;
    };

    renderPreviews = () => {

    };



    render() {
        console.log(this.state);

        const { auctions, currentPage, auctionsPerPage } = this.state;

        const indexOfLast = currentPage * auctionsPerPage;
        const indexOfFirst = indexOfLast - auctionsPerPage;
        const currentAuctions = auctions.slice(indexOfFirst, indexOfLast);

        const renderAuctions = currentAuctions.map((auction, index) => {
            return <tr key={"pg_"+index}>{this.previewAuction(auction, auctions)}</tr>;
        });

        //

        const pageNumbers = [];
        for(let i = 1; i <= Math.ceil(auctions.length / auctionsPerPage); i++) {
            pageNumbers.push(i);
        }

        const renderPageNumbers = pageNumbers.map(number => {
            return(
                <li
                    className="page-item"
                    key={"pg_"+number}

                    onClick={this.handlePageClick}
                >

                    <a href="#" id={number} className="page-link text-dark">{number}</a>

                </li>
            );
        });




        return (
            <div className="container">
                <table className="table w-100 my-5">
                    <tbody>

                        {renderAuctions}

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

                                    {renderPageNumbers}

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
