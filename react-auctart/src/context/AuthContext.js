import React from "react";
import {authenticateUser} from "../service/authService";
import {clearAccessToken, getAccessToken, setAccessToken} from "../service/browserStorageService";
import {getAuthenticatedUser, getUserByUsername} from "../service/userService";

const state = {
    login: () => {
    },
    logout: () => {
    },
    isAuthenticated: () => {
    },
    loggedInUser: null
};

const AuthContext = React.createContext(state);

export class AuthProvider extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            login: this.login,
            logout: this.logout,
            isAuthenticated: this.isAuthenticated,
            loggedInUser: null
        };
        if(this.isAuthenticated()){
            getAuthenticatedUser().then(response => {
               this.setState({
                   loggedInUser: response.data
               });
            });
        }
    }

    render() {
        return (
            <AuthContext.Provider value={this.state}>
                {this.props.children}
            </AuthContext.Provider>
        );
    }

    login = (user) => {
        authenticateUser(user).then(response => {
            const bearer = response.headers.authorization;
            setAccessToken(bearer);
            getAuthenticatedUser().then(userResponse => {
                this.setState({
                    loggedInUser: userResponse.data
                });
            });
        });
    };

    logout() {
        clearAccessToken();
    }

    isAuthenticated() {
        return !!getAccessToken();
    }
}

export const AuthConsumer = AuthContext.Consumer;

export const withAuthContext = (Component) => {
    return (topLevelProps) => (
        <AuthConsumer>
            {(authProps) => {
                return <Component {...topLevelProps} {...authProps}/>
            }}
        </AuthConsumer>
    )
};