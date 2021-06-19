import React, { Component } from "react";
import "../../css/Form.css";
import {
  Container,
  Alert,
  Button,
  Form,
  FormGroup,
  Input,
  Label,
  Row,
  Col,
} from "reactstrap";
import Authentication from "../../services/AuthenticationService";

const validateForm = (errors) => {
  let valid = true;
  Object.values(errors).forEach((val) => val.length > 0 && (valid = false));
  return valid;
};

class Register extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      firstName: "",
      middleName: "",
      lastName: "",
      ssn: "",
      successful: false,
      validForm: true,
      errors: {
        firstName: "",
        username: "",
        password: "",
      },
    };
  }

  changeHandler = (event) => {
    const { name, value } = event.target;

    let errors = this.state.errors;

    switch (name) {
      case "username":
        errors.username =
          value.length < 5 ? "Username must be 5 characters long!" : "";
        break;
      case "password":
        errors.password =
          value.length < 8 ? "Password must be 8 characters long!" : "";
        break;
      default:
        break;
    }

    this.setState({ errors, [name]: value }, () => {
      console.log(errors);
    });
  };

  signUp = (e) => {
    e.preventDefault();
    const valid = validateForm(this.state.errors);
    this.setState({ validForm: valid });
    if (valid) {
      Authentication.register(
        this.state.username,
        this.state.password,
        this.state.firstName,
        this.state.middleName,
        this.state.lastName,
        this.state.ssn
      ).then(
        (response) => {
          this.setState({
            message: response.data.message,
            successful: true,
          });
        },
        (error) => {
          console.log("Fail! Error = " + error.toString());

          this.setState({
            successful: false,
            message: error.toString(),
          });
        }
      );
    }
  };

  render() {
    const errors = this.state.errors;

    let alert = "";

    if (this.state.message) {
      if (this.state.successful) {
        alert = <Alert variant="success">{this.state.message}</Alert>;
      } else {
        alert = <Alert variant="danger">{this.state.message}</Alert>;
      }
    }

    return (
      <div>
        <Container fluid>
          <Row>
            <Col sm="12" md={{ size: 4, offset: 4 }}>
              <h2>
                Get started with us today! Create your account by filling out
                the information below.
              </h2>
              <Form onSubmit={this.signUp}>
                <FormGroup controlId="forFirstName">
                  <Label for="firstName">First Name</Label>
                  <Input
                    type="text"
                    placeholder="Enter your first name"
                    name="firstName"
                    id="firstName"
                    value={this.state.firstName}
                    autoComplete="firstName"
                    onChange={this.changeHandler}
                  />
                  {errors.firstName && (
                    <Alert variant="danger">{errors.firstName}</Alert>
                  )}
                </FormGroup>

                <FormGroup controlId="formMiddleName">
                  <Label for="middleName">Middle Name</Label>
                  <Input
                    required
                    type="text"
                    placeholder="Enter your middle name"
                    name="middleName"
                    id="middleName"
                    value={this.state.middleName}
                    autoComplete="middleName"
                    onChange={this.changeHandler}
                  />
                </FormGroup>

                <FormGroup controlId="forlastName">
                  <Label for="lastName">Last Name</Label>
                  <Input
                    type="text"
                    placeholder="Enter your last name"
                    name="lastName"
                    id="lastName"
                    value={this.state.lastName}
                    autoComplete="lastName"
                    onChange={this.changeHandler}
                  />
                </FormGroup>

                <FormGroup controlId="forssn">
                  <Label for="ssn">SSN</Label>
                  <Input
                    type="password"
                    placeholder="Enter your SSN"
                    name="ssn"
                    id="ssn"
                    value={this.state.ssn}
                    autoComplete="ssn"
                    onChange={this.changeHandler}
                  />
                </FormGroup>

                <FormGroup controlId="forUsername">
                  <Label for="username">Username</Label>
                  <Input
                    type="text"
                    placeholder="Create a new username"
                    name="username"
                    id="username"
                    value={this.state.username}
                    autoComplete="username"
                    onChange={this.changeHandler}
                  />
                  {errors.username && (
                    <Alert variant="danger">{errors.username}</Alert>
                  )}
                </FormGroup>

                <FormGroup controlId="formPassword">
                  <Label for="password">Password</Label>
                  <Input
                    required
                    type="password"
                    placeholder="Create a new password"
                    name="password"
                    id="password"
                    value={this.state.password}
                    autoComplete="password"
                    onChange={this.changeHandler}
                  />
                  {errors.password && (
                    <Alert key="errorspassword" variant="danger">
                      {errors.password}
                    </Alert>
                  )}
                </FormGroup>

                <Button variant="primary" type="submit">
                  Register
                </Button>
                {!this.state.validForm && (
                  <Alert key="validForm" variant="danger">
                    Please check the inputs again!
                  </Alert>
                )}
                {alert}
              </Form>
              <span className="form-input-login">
                Already have an account? Login <a href="/login">here</a>
              </span>
            </Col>
          </Row>
        </Container>
      </div>
    );
  }
}
export default Register;
