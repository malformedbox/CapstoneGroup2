import React, { Component } from "react";
import axios from "axios";
import {
  Alert,
  Container,
  Button,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
  Col,
} from "reactstrap";

const validateForm = (errors) => {
  let valid = true;
  Object.values(errors).forEach((val) => val.length > 0 && (valid = false));
  return valid;
};

class TransactionT extends Component {
  constructor(props) {
    super(props);
    this.state = {
      amount: "",
      transactionType: "TRANSFER",
      sourceAccountNumber: "",
      targetAccountNumber: "",
      successful: false,
      validForm: true,
      errors: {
        balance: "",
      },
    };
    this.handleDropdownChange = this.handleDropdownChange.bind(this);
  }

  handleDropdownChange(e) {
    this.setState({ transactionType: e.target.value });
  }

  changeHandler = (event) => {
    const { name, value } = event.target;

    let errors = this.state.errors;

    switch (name) {
      case "0":
        errors.balance = value === 0 ? "Cannot deposit 0 balance" : "";
        break;
      case "-":
        errors.balance = value < 0 ? "Cannot deposit a negative balance!" : "";
        break;
      default:
        break;
    }

    this.setState({ errors, [name]: value }, () => {
      console.log(errors);
    });
  };

  create = (e) => {
    e.preventDefault();
    const valid = validateForm(this.state.errors);
    if (valid) {
      axios
        .post("http://localhost:8080/user/transaction", this.state, {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
        })
        .then((data) => {
          this.setState({
            message: true,
            successful: true,
          });
        })
        .catch((err) => {
          console.log(err);
          this.setState({
            successful: false,
            message: err.toString(),
          });
        });
    }
  };

  render() {
    const errors = this.state.errors;
    let alert = "";

    if (this.state.message) {
      if (this.state.successful) {
        alert = <Alert variant="success">Transaction completed.</Alert>;
      } else {
        alert = <Alert variant="danger">Unable to process transaction</Alert>;
      }
    }

    return (
      <div>
        <Container fluid>
          <Row>
            <Col sm="12" md={{ size: 4, offset: 4 }}>
              <h2>Enter the amount and account numbers for transfer</h2>
              <Form onSubmit={this.create}>
                <FormGroup controlId="forAmount">
                  <Label for="amount">Amount</Label>
                  <Input
                    type="text"
                    placeholder="Enter an amount"
                    name="amount"
                    id="transactionAmount"
                    value={this.state.amount}
                    autoComplete="amount"
                    onChange={this.changeHandler}
                  />
                </FormGroup>
                <FormGroup controlId="transfer">
                  <Label for="amount">Transaction Type</Label>
                  <Input
                    type="text"
                    placeholder="Enter an amount"
                    name="amount"
                    id="transactionAmount"
                    value={this.state.transactionType}
                    autoComplete="amount"
                  />
                </FormGroup>
                <FormGroup controlId="forSourceAccount">
                  <Label for="account">
                    Where do you want to transfer money from?
                  </Label>
                  <Input
                    type="text"
                    placeholder="Enter source account number"
                    name="sourceAccountNumber"
                    id="sourceAccountNumber"
                    value={this.state.sourceAccountNumber}
                    autoComplete="account"
                    onChange={this.changeHandler}
                  />
                </FormGroup>
                <FormGroup controlId="forTargetAccount">
                  <Label for="account">
                    Where do you want to transfer money to?
                  </Label>
                  <Input
                    type="text"
                    placeholder="Enter target account number"
                    name="targetAccountNumber"
                    id="targetAccountNumber"
                    value={this.state.targetAccountNumber}
                    autoComplete="account"
                    onChange={this.changeHandler}
                  />
                </FormGroup>
                <Button variant="primary" type="submit">
                  Submit
                </Button>
                {alert}
              </Form>
              <span className="form-input-login">
                Change your mind? Return to your <a href="/user">account</a>
              </span>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}

export default TransactionT;
