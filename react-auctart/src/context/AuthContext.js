import React from "react";
import {authenticateUser} from "../service/userService";
import {clearAccessToken, getAccessToken, setAccessToken} from "../service/browserStorageService";


const state = {
    login: () => {},
    logout: () => {},
    isAuthenticated: () => {},
    getLoggedInUser: () => {}
};

const AuthContext = React.createContext(state);

export class AuthProvider extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            login: this.login,
            logout: this.logout,
            isAuthenticated: this.isAuthenticated,
            getLoggedInUser: this.getLoggedInUser
        };

    }

    render() {
        return (
            <AuthContext.Provider value={this.state}>
                {this.props.children}
            </AuthContext.Provider>
        );
    }

    login(user) {
        authenticateUser(user).then(response => {
            console.log(response);
            const bearer = response.headers.authorization;
            setAccessToken(bearer);
        });
    }

    logout() {
        clearAccessToken();
    }

    isAuthenticated() {
        return !!getAccessToken();
    }

    getLoggedInUser() {
    }
}

export const AuthConsumer = AuthContext.Consumer;

export const withAuthContext = (Component) => {

    console.log("called");
    return (topLevelProps) => (
        <AuthConsumer>
            {(authProps) => {
                return <Component {...topLevelProps} {...authProps}/>
            }}
        </AuthConsumer>
    )
};