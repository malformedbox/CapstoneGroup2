import React, { Component } from "react";
import { Card } from "reactstrap";
import AccountCards from "../AccountCards";

class PersonalChecking extends Component {
  constructor(props) {
    super();
    this.state = {
      personalChecking: "",
    };
  }

  render() {
    return (
      <div>
        {this.props.personalChecking != null &&
        this.props.personalChecking.accountNumber != null ? (
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
                  <td> {this.props.personalChecking.accountNumber}</td>
                  <td> {this.props.personalChecking.interestRate}</td>
                  <td> {this.props.personalChecking.openedOn}</td>
                  <td> {this.props.personalChecking.balance}</td>
                </tr>
              </tbody>
            </table>
            <h2>Personal Checking Transactions</h2>
            <table className="table table-striped">
              <thead>
                <tr>
                  <td> Date</td>
                  <td> Type of Transaction</td>
                  <td> Amount</td>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td> No history of transactions </td>
                  <td> </td>
                  <td> </td>
                </tr>
              </tbody>
            </table>
          </Card>
        ) : // <AccountCards
        //   title="Personal Checking"
        //   body="Click to open a personal checking account."
        // />
        null}
      </div>
    );
  }
}

export default PersonalChecking;
