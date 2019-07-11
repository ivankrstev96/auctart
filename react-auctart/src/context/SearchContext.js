import React from "react";

const state = {
    updateSearchQuery: () => {},
    searchQuery: ""
};

const SearchContext = React.createContext(state);

export class SearchProvider extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            updateSearchQuery: this.updateSearchQuery,
            searchQuery: ""
        };
    }

    render() {
        return (
            <SearchContext.Provider value={this.state}>
                {this.props.children}
            </SearchContext.Provider>
        );
    }

    updateSearchQuery = (searchQuery) => {
        this.setState({searchQuery: searchQuery});
    };
}

export const SearchConsumer = SearchContext.Consumer;

export const withSearchContext = (Component) => {
    return (topLevelProps) => (
        <SearchConsumer>
            {(searchProps) => {
                return <Component {...topLevelProps} {...searchProps}/>
            }}
        </SearchConsumer>
    )
};