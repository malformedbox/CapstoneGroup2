import React from 'react';
import { Card } from 'reactstrap';
import UserServices from '../../services/UserServices';
import AccountCards from '../AccountCards';
import UserPageCSS from '../../css/UserPage.css';

class UserPage extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            users:[],
            cdAccountsList:"",
            dbaCheckingList:[],
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
        console.log('check if personal checking exists', this.personalChecking);
        return (
            <div>
                <h1 className="account-holder-name">Welcome 
                    back, {this.state.users.firstName}
                </h1>
                {/* <h1 className="text-center"> 
                    {this.state.userCredentials.username}
                's Accounts       
                </h1> */}

                <div className="container">
                    <div className="col-8">
                        {this.state.dbaCheckingList != [] ? (
                            <Card className="cardsContainer">
                                <h2>DBA Checking</h2>
                                <table className="table table-striped">
                                    <thead>
                                        <tr>
                                            <td className="tableHeader"> Account Number</td>
                                            <td> Interest Rate</td>
                                            <td> Opened On</td>
                                            <td> Balance</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            this.state.dbaCheckingList.map(dbaChecking => 
                                            <tr key={dbaChecking.accountNumber}>
                                                <td> {dbaChecking.accountNumber}</td>
                                                <td> {dbaChecking.interestRate}</td>
                                                <td> {dbaChecking.openedOn}</td>
                                                <td> {dbaChecking.balance}</td>
                                            </tr>
                                            )
                                        }
                                    </tbody>
                                </table>
                                <table className="table table-striped">
                                    <h2>DBA Checking Transactions</h2>
                                    <tbody>
                                        <tr>
                                            <td> Date</td>
                                            <td> Type of Transaction</td>
                                            <td> Amount</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </Card> 
                            ) : <AccountCards
                                title='DBA Checking'
                                body='Click to open a DBA checking account.'
                                /> 
                        }

                        {this.state.personalChecking != null ? (
                            <Card className="cardsContainer">
                                <h2>Personal Checking</h2>
                                <table className="table table-striped">
                                    <thead>
                                        <tr>
                                            <td className="tableHeader"> Account Number</td>
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
                            </Card> 
                            ) : <AccountCards
                                title='Personal Checking'
                                body='Click to open a personal checking account.'
                                /> 
                        }

                        {this.state.savingsAccount != null ? (
                            <Card className="cardsContainer">
                                <h2>Savings</h2>
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
                                            <td> {this.state.savingsAccount.accountNumber}</td>
                                            <td> {this.state.savingsAccount.interestRate}</td>
                                            <td> {this.state.savingsAccount.openedOn}</td>
                                            <td> {this.state.savingsAccount.balance}</td>
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
                            </Card> 
                            ) : <AccountCards
                                path='/home'
                                title='Savings Account'
                                body='Click to open a savings account.'
                                /> 
                        }
                    </div>
                    <div className="col-sm">
                        <Card>
                            filler box
                        </Card>
                    </div>
                </div>
                
            </div>
        )
    }
}

export default UserPage;