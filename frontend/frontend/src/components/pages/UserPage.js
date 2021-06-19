import React from "react";
import { Link } from "react-router-dom";
import { Card, CardBody, CardText, CardTitle } from "reactstrap";
import UserServices from "../../services/UserServices";
import CDAccount from "../accounts/CDAccount";
import DbaChecking from "../accounts/DbaChecking";
import IraRegular from "../accounts/IraRegular";
import IraRollover from "../accounts/IraRollover";
import IraRoth from "../accounts/IraRoth";
import PersonalChecking from "../accounts/PersonalChecking";
import SavingsAccount from "../accounts/SavingsAccount";
import "../../css/UserPage.css";

class UserPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      users: [],
      cdAccountsList: [],
      dbaCheckingList: [],
      iraRegular: "",
      iraRollover: "",
      iraRoth: "",
      personalChecking: "",
      savingsAccount: "",
      userCredentials: "",
    };
  }
  //gets loaded every time page loaded
  componentDidMount() {
    UserServices.getUserAccounts().then((response) => {
      this.setState({
        users: response.data,
        cdAccountsList: response.data.cdAccountsList,
        dbaCheckingList: response.data.dbaCheckingList,
        iraRegular: response.data.iraRegular,
        iraRollover: response.data.iraRollover,
        iraRoth: response.data.iraRoth,
        personalChecking: response.data.personalChecking,
        savingsAccount: response.data.savingsAccount,
        userCredentials: response.data.userCredentials,
      });
    });
  }

  render() {
    return (
      <div>
        <div className="container">
          <div className="row">
            <div className="col-8">
              <CDAccount cdAccountsList={this.state.cdAccountsList} />
              <DbaChecking dbaCheckingList={this.state.dbaCheckingList} />
              <IraRegular iraRegular={this.state.iraRegular} />
              <IraRollover iraRollover={this.state.iraRollover} />
              <IraRoth iraRoth={this.state.iraRoth} />
              <PersonalChecking
                personalChecking={this.state.personalChecking}
              />
              <SavingsAccount savingsAccount={this.state.savingsAccount} />
            </div>
            <div className="col-4">
              <Card>
                <CardBody>
                  <CardTitle>
                    <div className="account-holder-name">
                      Welcome back, {this.state.users.firstName}
                    </div>
                  </CardTitle>
                  <CardText>
                    Your Accounts
                    <ul>
                      <li>
                        {this.state.cdAccountsList !== [] &&
                        this.state.cdAccountsList.accountNumber != null ? (
                          <p>CD Account</p>
                        ) : (
                          <Link to="home">Click to open a CD account.</Link>
                        )}
                      </li>
                      <li>
                        {this.state.dbaCheckingList !== [] &&
                        this.state.dbaCheckingList.accountNumber != null ? (
                          <p>Dba Checking</p>
                        ) : (
                          <Link to="home">
                            Click to open a DBA checking account.
                          </Link>
                        )}
                      </li>
                      <li>
                        {this.state.iraRegular != null &&
                        this.state.iraRegular.accountNumber != null ? (
                          <p>Ira Regular</p>
                        ) : (
                          <Link to="/createiraregular">
                            Click to open a IRA Regular account.
                          </Link>
                        )}
                      </li>
                      <li>
                        {this.state.iraRollover != null &&
                        this.state.iraRegular.accountNumber != null ? (
                          <p>Ira Rollover</p>
                        ) : (
                          <Link to="/createirarollover">
                            Click to open a IRA Rollover account.
                          </Link>
                        )}
                      </li>
                      <li>
                        {this.state.iraRoth != null &&
                        this.state.iraRoth.accountNumber != null ? (
                          <p>Ira Roth</p>
                        ) : (
                          <Link to="/createiraroth">
                            Click to open a IRA Roth account.
                          </Link>
                        )}
                      </li>
                      <li>
                        {this.state.personalChecking != null &&
                        this.state.personalChecking.accountNumber != null ? (
                          <p>Personal Checking</p>
                        ) : (
                          <Link to="/createpersonalchecking">
                            Click to open a personal checking account.
                          </Link>
                        )}
                      </li>
                      <li>
                        {this.state.savingsAccount != null &&
                        this.state.savingsAccount.accountNumber != null ? (
                          <p>Savings</p>
                        ) : (
                          <Link to="home">
                            Click to open a savings account.
                          </Link>
                        )}
                      </li>
                    </ul>
                  </CardText>
                </CardBody>
              </Card>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default UserPage;
