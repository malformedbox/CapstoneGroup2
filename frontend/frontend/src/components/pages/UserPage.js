import React, { Component } from 'react';
import BackendService from '../../services/BackendService';
import AuthenticationService from '../../services/AuthenticationService';
import UserService from '../../services/UserServices';

class UserPage extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            users:[]
        }
    }

    componentDidMount(){
        UserService.getPersonalChecking().then((response) => {
            this.setState({ users: response.data})
        });
    }

    render (){
        return (
            <div>
                <h1 className="account-holder-name">Welcome back, ACCOUNTHOLDERNAME</h1>
                <h1 className="text-center"> Account Holders List</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td> Account Number</td>
                            <td> Interest Rate</td>
                            <td> Opened On</td>
                            <td> Balance</td>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td> Account Number</td>
                            <td> Interest Rate</td>
                            <td> Opened On</td>
                            <td> Balance</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        )
    }
}

export default UserPage;