import React from "react";
import NotificationSystem from 'react-notification-system';


const state = {
    notificationSystem: null
};

const NotificationContext = React.createContext(state);

export class NotificationProvider extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            notificationSystem: React.createRef()
        };
    }

    render() {
        return (
            <NotificationContext.Provider value={this.state}>
                {this.props.children}
                <NotificationSystem ref={this.state.notificationSystem}/>
            </NotificationContext.Provider>
        );
    }
}

export const NotificationConsumer = NotificationContext.Consumer;

export const withNotificationContext = (Component) => {
    return (topLevelProps) => (
        <NotificationConsumer>
            {(notificationSystemProps) => {
                return <Component {...topLevelProps} {...notificationSystemProps}/>
            }}
        </NotificationConsumer>
    )
};