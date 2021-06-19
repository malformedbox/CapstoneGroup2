import React, { Component } from "react";
import { Card } from "reactstrap";
import TransactionButton from "../../TransactionButton";
import "../../css/UserPage.css";

class IraRegular extends Component {
  constructor(props) {
    super();
    this.state = {
      iraRegular: "",
    };
  }

  render() {
    console.log("data", this.props.iraRegular);
    return (
      <div>
        {this.props.iraRegular != null &&
        this.props.iraRegular.accountNumber != null ? (
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
                  <td> {this.props.iraRegular.accountNumber}</td>
                  <td> {this.props.iraRegular.interestRate}</td>
                  <td> {this.props.iraRegular.openedOn}</td>
                  <td> {this.props.iraRegular.balance}</td>
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
                {this.props.iraRegular.transactions.map(
                  (iraRegularTransactions) => (
                    <tr key={iraRegularTransactions.accountNumber}>
                      <td> {iraRegularTransactions.dateOfTransaction}</td>
                      <td> {iraRegularTransactions.transactionType}</td>
                      {iraRegularTransactions.transactionType === "TRANSFER" ? (
                        <td>{iraRegularTransactions.amount}</td>
                      ) : iraRegularTransactions.transactionType ===
                        "WITHDRAWAL" ? (
                        <td className="transaction-withdraw">
                          -${iraRegularTransactions.amount}
                        </td>
                      ) : (
                        <td className="transaction-deposit">
                          ${iraRegularTransactions.amount}
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

export default IraRegular;
