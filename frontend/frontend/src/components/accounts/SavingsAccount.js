import React, { Component } from "react";
import { Card } from "reactstrap";
import TransactionButton from "../../TransactionButton";
import "../../css/UserPage.css";

class SavingsAccount extends Component {
  constructor(props) {
    super();
    this.state = {
      savingsAccount: "",
    };
  }

  render() {
    console.log("data", this.props.savingsAccount);
    return (
      <div>
        {this.props.savingsAccount != null &&
        this.props.savingsAccount.accountNumber != null ? (
          <Card className="cardsContainer">
            <h2>IRA Regular</h2>
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
                  <td> {this.props.savingsAccount.accountNumber}</td>
                  <td> {this.props.savingsAccount.interestRate}</td>
                  <td> {this.props.savingsAccount.openedOn}</td>
                  <td> {this.props.savingsAccount.balance}</td>
                </tr>
              </tbody>
            </table>
            <h2>IRA Regular Transactions</h2>
            <table className="table table-striped">
              <tbody>
                <tr>
                  <td className="tableHeader-date"> Date</td>
                  <td className="tableHeader"> Type of Transaction</td>
                  <td className="tableHeader"> Amount</td>
                </tr>
              </tbody>
              <tbody>
                {this.props.savingsAccount.transactions.map(
                  (savingsAccountTransactions) => (
                    <tr key={savingsAccountTransactions.accountNumber}>
                      <td> {savingsAccountTransactions.dateOfTransaction}</td>
                      <td> {savingsAccountTransactions.transactionType}</td>
                      {savingsAccountTransactions.transactionType ===
                      "TRANSFER" ? (
                        <td>{savingsAccountTransactions.amount}</td>
                      ) : savingsAccountTransactions.transactionType ===
                        "WITHDRAWAL" ? (
                        <td className="transaction-withdraw">
                          -${savingsAccountTransactions.amount}
                        </td>
                      ) : (
                        <td className="transaction-deposit">
                          ${savingsAccountTransactions.amount}
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

export default SavingsAccount;
