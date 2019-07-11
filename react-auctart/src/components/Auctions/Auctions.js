import React from 'react';
import '../../assets/css/fonts.css';
import './Auctions.css'
import PlaceBid from "../PlaceBid/PlaceBid"
import {getActiveAuctions} from "../../service/auctionService";
import {withNotificationContext} from "../../context/NotificationContext";

class Auctions extends React.Component {
    constructor(props) {
        super(props);
        this.state = {auctions: []};
        getActiveAuctions().then(auctions => {
            this.setState({
                auctions: auctions,
                currentPage: 1,
                auctionsPerPage: 3
            })
        }).catch(() => {
            this.props.notificationSystem.current.addNotification({
                title: "Error",
                message: "Something went wrong",
                level: "error",
                autoDismiss: 3
            });
        });
    }

    handlePageFirst = (e) => {
        this.setState({
            currentPage: 1,
        });
    };

    handlePageLast = (e) => {
        const lastPage = Math.ceil(this.state.auctions.length / this.state.auctionsPerPage);
        this.setState({
            currentPage: lastPage,
        });
    };

    handlePageClick = (e) => {
        this.setState({
            currentPage: Number(e.target.id),
        });
    };

    previewAuction = (auction, auctions) => {
        const children = [];
        const index = auctions.indexOf(auction);
        children.push(
            <td key={"td1_" + index} className="td-w-img d-table-cell align-middle">
                <img className="cropped-image " src={`/api/image/public/${auction.id}`}/>
            </td>
        );
        children.push(
            <td key={"td2_" + index} className="py-4">
                <h3>
                    {auction.name}&nbsp;
                </h3>
                <h3>
                    <sub>
                        by {auction.author}
                    </sub>
                </h3>
                <h5>
                    Active until: {auction.endDate.slice(0, 10)}
                </h5>
                <div className="float-to-bottom float-right">
                    <PlaceBid/>
                </div>
            </td>
        );
        return children;
    };

    render() {
        const {auctions, currentPage, auctionsPerPage} = this.state;

        const indexOfLast = currentPage * auctionsPerPage;
        const indexOfFirst = indexOfLast - auctionsPerPage;
        const currentAuctions = auctions.slice(indexOfFirst, indexOfLast);

        const renderAuctions = currentAuctions.map((auction, index) => {
            return <tr key={"pg_" + index}>{this.previewAuction(auction, auctions)}</tr>;
        });

        //

        const pageNumbers = [];
        for (let i = 1; i <= Math.ceil(auctions.length / auctionsPerPage); i++) {
            pageNumbers.push(i);
        }

        const renderPageNumbers = pageNumbers.map(number => {
            let returnCode = [];
            returnCode.push(
                <li
                    className="page-item"
                    key={"pg_" + number}
                    onClick={this.handlePageClick}
                >
                    <a href="#" id={number} className="page-link text-dark">{number}</a>

                </li>
            );

            let currentPage = this.state.currentPage;
            const lastPage = Math.ceil(auctions.length / auctionsPerPage);

            if (currentPage == 1) {
                if (number == 1 || number == 2 || number == 3) {
                    return returnCode;
                }
            } else if (currentPage == lastPage) {
                if (number == lastPage - 2 || number == lastPage - 1 || number == lastPage) {
                    return returnCode;
                }
            } else {
                if (number == currentPage - 1 || number == currentPage || number == currentPage + 1) {
                    return returnCode;
                }
            }

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
                                        <a name="first" onClick={this.handlePageFirst} className="page-link text-dark"
                                           href="#" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>

                                    {renderPageNumbers}

                                    <li className="page-item">
                                        <a name="last" onClick={this.handlePageLast} className="page-link text-dark"
                                           href="#" aria-label="Next">
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
