import axios from "./http";

const apiUri = "/api/auction";

export const getActiveAuctions = (query) => {
    if(query){
        return axios.get(`${apiUri}/public?query=${query}`)
            .then(response => response.data);
    }
        return axios.get(`${apiUri}/public`)
            .then(response => response.data);
};

export const getHighestBidForAuction = (auctionId) => {
    return axios.get(`${apiUri}/public/${auctionId}/bid`);
};

export const saveAuction = (auction) => {
    let bodyFormData = new FormData();
    bodyFormData.append('name', auction.name);
    bodyFormData.append('author', auction.author);
    bodyFormData.append('endDate', auction.endDate);
    bodyFormData.append('startPrice', auction.startPrice);
    bodyFormData.append('image', auction.image);
    return axios.post(`${apiUri}`, bodyFormData);
};
