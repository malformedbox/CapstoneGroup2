import React, { Component } from "react";
import { Card } from "reactstrap";
import TransactionButton from "../../TransactionButton";
import "../../css/UserPage.css";

class PersonalChecking extends Component {
  constructor(props) {
    super();
    this.state = {
      personalChecking: "",
    };
  }

  render() {
    console.log("data", this.props.personalChecking);
    return (
      <div>
        {this.props.personalChecking != null &&
        this.props.personalChecking.accountNumber != null ? (
          <Card className="cardsContainer">
            <h2>Personal Checking</h2>
            <div className="dropdown">
              <TransactionButton className="dropdown"></TransactionButton>
            </div>
            <table className="table table-striped">
              <thead>
                <tr>
                  <td className="tableHeader"> Account Number</td>
                  <td className="tableHeader"> Interest Rate</td>
                  <td className="tableHeader"> Opened On</td>
                  <td className="tableHeader"> Balance</td>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td> {this.props.personalChecking.accountNumber}</td>
                  <td> {this.props.personalChecking.interestRate}</td>
                  <td> {this.props.personalChecking.openedOn}</td>
                  <td> {this.props.personalChecking.balance}</td>
                </tr>
              </tbody>
            </table>
            <h2>Personal Checking Transactions</h2>
            <table className="table table-striped">
              <tbody>
                <tr>
                  <td className="tableHeader-date"> Date</td>
                  <td className="tableHeader"> Type of Transaction</td>
                  <td className="tableHeader"> Amount</td>
                </tr>
              </tbody>
              <tbody>
                {this.props.personalChecking.transactions.map(
                  (personalCheckingTransactions) => (
                    <tr key={personalCheckingTransactions.accountNumber}>
                      <td> {personalCheckingTransactions.dateOfTransaction}</td>
                      <td> {personalCheckingTransactions.transactionType}</td>
                      {personalCheckingTransactions.transactionType ===
                      "TRANSFER" ? (
                        <td>{personalCheckingTransactions.amount}</td>
                      ) : personalCheckingTransactions.transactionType ===
                        "WITHDRAWAL" ? (
                        <td className="transaction-withdraw">
                          -${personalCheckingTransactions.amount}
                        </td>
                      ) : (
                        <td className="transaction-deposit">
                          ${personalCheckingTransactions.amount}
                        </td>
                      )}
                    </tr>
                  )
                )}
              </tbody>
            </table>
          </Card>
        ) : null}
      </div>
    );
  }
}

export default PersonalChecking;
