import React, { Component } from "react";
import { Card } from "reactstrap";

class DbaChecking extends Component {
  constructor(props) {
    super();
    this.state = {
      dbaCheckingList: [],
    };
  }

  render() {
    return (
      <div>
        {this.props.dbaCheckingList !== [] &&
        this.props.dbaCheckingList.accountNumber != null ? (
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
                {this.props.dbaCheckingList.map((dbaChecking) => (
                  <tr key={dbaChecking.accountNumber}>
                    <td> {dbaChecking.accountNumber}</td>
                    <td> {dbaChecking.interestRate}</td>
                    <td> {dbaChecking.openedOn}</td>
                    <td> {dbaChecking.balance}</td>
                  </tr>
                ))}
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
        ) : null}
      </div>
    );
  }
}

export default DbaChecking;
