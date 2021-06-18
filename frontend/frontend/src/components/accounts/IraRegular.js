import React, { Component } from "react";
import { Card } from "reactstrap";
import AccountCards from "../AccountCards";

class IraRegular extends Component {
  constructor(props) {
    super();
    this.state = {
      iraRegular: "",
    };
  }

  render() {
    return (
      <div>
        {this.props.iraRegular != null &&
        this.props.iraRegular.accountNumber != null ? (
          <Card className="cardsContainer">
            <h2>IRA Regular</h2>
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
                  <td> {this.props.iraRegular.accountNumber}</td>
                  <td> {this.props.iraRegular.interestRate}</td>
                  <td> {this.props.iraRegular.openedOn}</td>
                  <td> {this.props.iraRegular.balance}</td>
                </tr>
              </tbody>
            </table>
            <table className="table table-striped">
              <h2>IRA Regular Transactions</h2>
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
        //   title="IRA Regular"
        //   body="Click to open an IRA Regular account."
        // />
        null}
      </div>
    );
  }
}

export default IraRegular;
