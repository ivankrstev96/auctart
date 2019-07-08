import axios from "./http";

const apiUri = "/api/bid";

export const saveBid = (bid) => {
    return axios.post(`${apiUri}`, bid)
        .then(response => response.data);
};
