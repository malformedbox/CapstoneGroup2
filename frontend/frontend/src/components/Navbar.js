import React, { Component } from "react";
import AuthenticationService from "../services/AuthenticationService";
import { Link } from "react-router-dom";
import "../css/Navbar.css";

import { withRouter } from "react-router-dom";

class Navbar extends Component {
  constructor(props) {
    super(props);
    this.state = { isOpen: false };
    this.toggle = this.toggle.bind(this);

    this.state = {
      login: false,
    };
  }

  componentDidMount() {
    const user = AuthenticationService.getCurrentUser();

    if (user) {
      this.setState({
        login: true,
      });
    }
  }

  signOut = () => {
    AuthenticationService.signOut();
    this.props.history.push("/home");
    window.location.reload();
  };

  toggle() {
    this.setState({
      isOpen: !this.state.isOpen,
    });
  }

  render() {
    return (
      <>
        <nav className="navbar">
          <div className="navbar-container">
            <Link to="/" className="navbar-logo">
              MeritBank
            </Link>
            <div className="menu-icon">
              <i className="fas fa-times" />
            </div>
            <ul className="nav-menu active">
              <li className="nav-item">
                <Link to="/home" className="nav-links">
                  Home
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/services" className="nav-links">
                  Services
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/profile" className="nav-links">
                  About Us
                </Link>
              </li>
              <li className="nav-item">
                <Link to="/user" className="nav-links">
                  My Account
                </Link>
              </li>
              {this.state.login ? (
                <li className="nav-item">
                  <Link to="/home" className="nav-links" onClick={this.signOut}>
                    Sign Out
                  </Link>
                </li>
              ) : (
                <li className="nav-item">
                  <Link to="/login" className="nav-links">
                    Login
                  </Link>
                </li>
              )}
            </ul>
          </div>
        </nav>
      </>
    );
  }
}

export default withRouter(Navbar);
