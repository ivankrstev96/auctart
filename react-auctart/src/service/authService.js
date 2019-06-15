import axios from "./http";

const apiUri = "/auth";
export const authenticateUser = (user) => {
    return axios.post(apiUri, user, {withCredentials: true});
};