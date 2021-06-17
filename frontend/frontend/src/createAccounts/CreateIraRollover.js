import React, { Component } from "react";
import axios from "axios";
import {
  Container,
  Button,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
  Col,
} from "reactstrap";

class CreateIraRollover extends Component {
  constructor(props) {
    super(props);
    this.state = {
      balance: "",
      successful: false,
      validForm: true,
      errors: {
        balance: "",
      },
    };
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
    axios
      .post("http://localhost:8080/user/irarollover", this.state, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
      })
      .then((data) => {
        console.log(data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  render() {
    return (
      <div>
        <Container fluid>
          <Row>
            <Col sm="12" md={{ size: 4, offset: 4 }}>
              <h2>Create an IRA Rollover account.</h2>
              <Form onSubmit={this.create}>
                <FormGroup controlId="forBalance">
                  <Label for="balance">New Ira Rollover Balance</Label>
                  <Input
                    type="text"
                    placeholder="Enter a balance"
                    name="balance"
                    id="balanceIra"
                    value={this.state.balance}
                    autoComplete="balance"
                    onChange={this.changeHandler}
                  />
                </FormGroup>
                <Button variant="primary" type="submit">
                  Create
                </Button>
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

export default CreateIraRollover;
