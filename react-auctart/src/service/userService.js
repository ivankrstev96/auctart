import axios from "./http";

const apiUri = "/api/user";

export const getUserByUsername = (userName) => {
    return axios.get(`${apiUri}/${userName}`);
};

export const getAuthenticatedUser = () => {
    return axios.get(`${apiUri}/current`)
}