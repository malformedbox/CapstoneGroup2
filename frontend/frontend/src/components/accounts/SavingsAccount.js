import React, { Component } from "react";
import { Card } from "reactstrap";
import AccountCards from "../AccountCards";

class SavingsAccount extends Component {
  constructor(props) {
    super(props);
    this.state = {
      savingsAccount: "",
    };
  }

  render() {
    return (
      <div>
        {this.props.savingsAccount != null &&
        this.props.savingsAccount.accountNumber != null ? (
          <Card className="cardsContainer">
            <h2>Savings Account</h2>
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
                  <td className="td-transaction" id="td-transaction-withdraw">
                    {this.props.savingsAccount.accountNumber}
                  </td>
                  <td> {this.props.savingsAccount.interestRate}</td>
                  <td> {this.props.savingsAccount.openedOn}</td>
                  <td> {this.props.savingsAccount.balance}</td>
                </tr>
              </tbody>
            </table>
            <table className="table table-striped">
              <h2>Savings Transactions</h2>
              <tbody>
                <tr>
                  <td> Date</td>
                  <td> Type of Transaction</td>
                  <td> Amount</td>
                </tr>
              </tbody>
            </table>
          </Card>
        ) : // <AccountCards
        //   title="Savings"
        //   body="Click to open a savings account."
        // />
        null}
      </div>
    );
  }
}

export default SavingsAccount;
