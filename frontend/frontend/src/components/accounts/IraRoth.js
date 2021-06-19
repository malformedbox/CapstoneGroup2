import React, { Component } from "react";
import { Card } from "reactstrap";

class IraRoth extends Component {
  constructor(props) {
    super();
    this.state = {
      iraRoth: "",
    };
  }

  render() {
    return (
      <div>
        {this.props.iraRoth != null &&
        this.props.iraRoth.accountNumber != null ? (
          <Card className="cardsContainer">
            <h2>IRA Roth</h2>
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
                  <td> {this.props.iraRoth.accountNumber}</td>
                  <td> {this.props.iraRoth.interestRate}</td>
                  <td> {this.props.iraRoth.openedOn}</td>
                  <td> {this.props.iraRoth.balance}</td>
                </tr>
              </tbody>
            </table>
            <table className="table table-striped">
              <h2>IRA Roth Transactions</h2>
              <tbody>
                <tr>
                  <td> Date</td>
                  <td> Type of Transaction</td>
                  <td> Amount</td>
                </tr>
              </tbody>
            </table>
          </Card>
        ) : null}
      </div>
    );
  }
}

export default IraRoth;
