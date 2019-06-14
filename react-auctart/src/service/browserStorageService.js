const tokenName = "token";

export const setAccessToken = (token) => {
    localStorage.setItem(tokenName, token);
};

export const getAccessToken = () => {
    return localStorage.getItem(tokenName);
};

export const clearAccessToken = () => {
    localStorage.removeItem(tokenName)
};