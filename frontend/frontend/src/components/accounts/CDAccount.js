import React, { Component } from "react";
import { Card } from "reactstrap";
import AccountCards from "../AccountCards";

class CDAccount extends Component {
  constructor(props) {
    super();
    this.state = {
      cdAccountsList: [],
    };
  }

  render() {
    return (
      <div>
        {/* this.props.cdAccountsList.accountNumber != null */}
        {this.props.cdAccountsList !== [] &&
        this.props.cdAccountsList.accountNumber != null ? (
          <Card className="cardsContainer">
            <h2>Certificate of Deposit</h2>
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
                {this.props.cdAccountsList.map((cdAccounts) => (
                  <tr key={cdAccounts.accountNumber}>
                    <td> {cdAccounts.accountNumber}</td>
                    <td> {cdAccounts.interestRate}</td>
                    <td> {cdAccounts.openedOn}</td>
                    <td> {cdAccounts.balance}</td>
                  </tr>
                ))}
              </tbody>
            </table>
            <table className="table table-striped">
              <h2>Certificate of Deposit</h2>
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
        //   title="Certificate of Deposit"
        //   body="Click to open an CD account."
        // />
        null}
      </div>
    );
  }
}

export default CDAccount;
