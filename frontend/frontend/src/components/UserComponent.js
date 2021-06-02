import React, { Component } from 'react';
import reactDom from 'react-dom';
import UserService from '../services/UserServices';

class UserComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            users:[]
        }
    }

    //lifecycle method that will call rest api
    componentDidMount(){
        UserService.getUsers().then((response) => {
            this.setState({ users: response.data})
        });
    }

    render (){
        return (
            <div>
                <h1 className="text-center"> Account Holders List</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td> Account Holders Id</td>
                            <td> Account Holders First Name</td>
                            <td> Account Holders Middle Name</td>
                            <td> Account Holders Last Name</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.users.map(
                                user =>
                                <tr key = {user.id}>
                                    <td> {user.id}</td>
                                    <td> {user.firstName}</td>
                                    <td> {user.middleName}</td>
                                    <td> {user.lastName}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
                <h1 className="text-center"> Recent Transactions List</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td> Account Holders Id</td>
                            <td> Account Holders First Name</td>
                            <td> Account Holders Middle Name</td>
                            <td> Account Holders Last Name</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.users.map(
                                user =>
                                <tr key = {user.id}>
                                    <td> {user.id}</td>
                                    <td> {user.firstName}</td>
                                    <td> {user.middleName}</td>
                                    <td> {user.lastName}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
            
        )
    }
}
//In {user.id} or {user.firstName} these must match the private variable name in spring
export default UserComponent;