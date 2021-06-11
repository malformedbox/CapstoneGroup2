import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Button, Container, Alert } from 'reactstrap';
import AuthenticationService from '../../services/AuthenticationService';

class Profile extends Component {
    constructor(props) {
        super(props);
        this.state = {user : undefined};
    }

    componentDidMount() {
        const user = AuthenticationService.getCurrentUser();
        this.setState({user: user});
    }

    render() {
        let userInfo = "";
        const user = this.state.user;

        //login
        if(user && user.accessToken){
            let roles = "";
            user.authorities.forEach(authority => {
                roles = roles + " " + authority.authority
            });

            userInfo = (
                <div style={{marginTop:"20px"}}>
                    <Alert variant="info">
                        <h2>User Info</h2>
                        <ul>
                            <li>Username: {user.username}</li>
                            <li>Access Token: {user.accessToken}</li>
                            <li>Authorities: {roles}</li>
                        </ul>
                    </Alert>
                </div>
            );
        } else { // not logged in
            userInfo = 
                <div style={{marginTop:"20px"}}>
                    <Alert variant="primary">
                        <h2>Login to access your account information.</h2>
                        <Button color="success">
                            <Link to="/login">
                                <span style={{color:"white"}}>Login</span>
                            </Link>
                        </Button>
                    </Alert>
                </div>
        }

        return (
            <div>
                <Container fluid>
                    {userInfo}
                </Container>
            </div>
        );
    }
}

export default Profile;