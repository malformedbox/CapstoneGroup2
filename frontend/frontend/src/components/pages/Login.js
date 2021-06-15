import React, { Component } from 'react';
import '../../css/Form.css';
import { Container, Alert } from 'reactstrap';
import { Button, Form, FormGroup, Input, Label, Row, Col } from "reactstrap";
import AuthenticationService from '../../services/AuthenticationService';

class Login extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            error: ""
        };
    }

    changeHandler = (event) => {
        let nam = event.target.name;
        let val = event.target.value;
        this.setState({[nam]: val});
    }

    doLogin = async (event) => {
        event.preventDefault();

        AuthenticationService
            .login(this.state.username, this.state.password)
            .then(
                () => {this.props.history.push('/user');
                window.location.reload();
            },
            error => {
                console.log("Login fail: error = { " + error.toString() + " }");
                this.setState({error: "Cannot login. Please check username or password."});
            }
        );
    }

    render () {
        return ( 
            <div>
              <Container fluid className="fluid-form-container">
                <Row>
                  <Col sm="12" md={{ size: 4, offset: 4 }}>
                    <h2>
                      Login
                    </h2>
                    <Form onSubmit={this.doLogin}>
                        <FormGroup controlId="forUsername" className="form-inputs">
                            <Label for="username">Username</Label>
                            <Input autoFocus 
                                type="text"
                                name="username" id="username"
                                value={this.state.username}
                                placeholder="Enter your username"
                                autoComplete="username"
                                onChange={this.changeHandler}
                            />
                        </FormGroup>
    
                        <FormGroup controlId="formPassword" className="form-inputs">
                            <Label for="password">Password</Label>
                            <Input type="password" 
                                name="password" id="password"
                                value={this.state.password}
                                placeholder="Enter your password"
                                autoComplete="password"
                                onChange={this.changeHandler}
                            />
                        </FormGroup>
  
                        <Button type="submit" variant="primary" block>
                            Login
                        </Button>
                        {
                            this.state.error && (
                                <Alert color="danger">
                                    {this.state.error}
                                </Alert>
                            )
                        }
                    </Form>
                    <span className='form-input-login'>
                      Don't have an account? Register <a href='/register'>here</a>
                    </span>
                  </Col>
                </Row>
              </Container>
            </div>
        );
    }
}

export default Login;