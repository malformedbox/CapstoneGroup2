import React, { Component } from 'react';
import UserServices from '../../services/UserServices';

class UserPage extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            users:[],
            cdAccountsList:"",
            dbaCheckingList:"",
            iraRegular:"",
            iraRollover:"",
            personalChecking:"",
            savingsAccount:"",
            userCredentials:""
        }
    }
    //gets loaded every time page loaded
    componentDidMount(){
        UserServices.getUserAccounts().then((response) => {
            console.log(response);
            this.setState({ 
                users: response.data, 
                cdAccountsList: response.data.cdAccountsList,
                dbaCheckingList: response.data.dbaCheckingList,
                iraRegular: response.data.iraRegular,
                iraRollover: response.data.iraRollover,
                personalChecking: response.data.personalChecking,
                savingsAccount: response.data.savingsAccount,
                userCredentials: response.data.userCredentials
            })
        });
         console.log('user cred', this.state.users);
    }
   
    render (){
        return (
            <div>
                <h1 className="account-holder-name">Welcome 
                    back, {this.state.users.firstName}, {this.state.users.middleName}, {this.state.users.lastName}
                </h1>
                <h1 className="text-center"> 
                    {this.state.userCredentials.username}
                's Accounts       
                </h1>
                <div>
                    <h2>Personal Checking</h2>
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
                                <td> {this.state.personalChecking.accountNumber}</td>
                                <td> {this.state.personalChecking.interestRate}</td>
                                <td> {this.state.personalChecking.openedOn}</td>
                                <td> {this.state.personalChecking.balance}</td>
                            </tr>
                        </tbody>
                    </table>
                    <table className="table table-striped">
                        <h2>Personal Checking Transactions</h2>
                        <tbody>
                            <tr>
                                <td> Date</td>
                                <td> Type of Transaction</td>
                                <td> Amount</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default UserPage;