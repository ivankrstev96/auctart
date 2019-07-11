import axios from "./http";

const apiUri = "/api/bid";

export const saveBid = (bid) => {
    console.log(bid);
    return axios.post(`${apiUri}`, bid);
};
