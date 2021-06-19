import React, { Component } from "react";
import { Card } from "reactstrap";

class IraRollover extends Component {
  constructor(props) {
    super();
    this.state = {
      iraRollover: "",
    };
  }

  render() {
    return (
      <div>
        {this.props.iraRollover != null &&
        this.props.iraRollover.accountNumber != null ? (
          <Card className="cardsContainer">
            <h2>IRA Rollover</h2>
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
                  <td> {this.props.iraRollover.accountNumber}</td>
                  <td> {this.props.iraRollover.interestRate}</td>
                  <td> {this.props.iraRollover.openedOn}</td>
                  <td> {this.props.iraRollover.balance}</td>
                </tr>
              </tbody>
            </table>
            <table className="table table-striped">
              <h2>IRA Rollover Transactions</h2>
              <tbody>
                <tr>
                  <td className="tableHeader"> Date</td>
                  <td className="tableHeader"> Type of Transaction</td>
                  <td className="tableHeader"> Amount</td>
                </tr>
              </tbody>
            </table>
          </Card>
        ) : null}
      </div>
    );
  }
}

export default IraRollover;
