import React from "react";
import "../../App.css";
import { Link } from "react-router-dom";
import clipArtBank from "../../assets/images/clipArtBank.png";
import clipArtApp from "../../assets/images/clipArtApp.png";
import clipArtScore from "../../assets/images/clipArtScore.png";
import { Button } from "reactstrap";

function Home() {
  return (
    <div>
      <div class="jumbotron text-center">
        <div class="jumbotronContainer">
          <h1 class="jumbotronHeader">Get started with us today</h1>
          <p class="jumbotronText">
            We offer many accounts. Choose one that fits your needs.
          </p>
          <p>
            <Link to="/login">
              <Button variant="jumbotron-primary">Get Started</Button>
            </Link>
          </p>
        </div>
      </div>
      <div className="wrapper">
        <div className="wrapper-container">
          <img alt="" src={clipArtBank} className="clipArt" />
          <h2 className="wrapper-title">Start saving</h2>
          <p className="wrapper-container-text">
            Manage your accounts with online banking.
          </p>
          <Link to="/user" className="wrapper-container-link">
            Go To Your Accounts →
          </Link>
        </div>
        <div className="wrapper-container">
          <img alt="" src={clipArtApp} className="clipArt" />
          <h2 className="wrapper-title">Mobile banking</h2>
          <p className="wrapper-container-text">
            Download our app for a convenient banking experience.
          </p>
          <Link to="/user" className="wrapper-container-link">
            Download →
          </Link>
        </div>
        <div className="wrapper-container">
          <img alt="" src={clipArtScore} className="clipArt" />
          <h2 className="wrapper-title">Credit score</h2>
          <p className="wrapper-container-text">Get your credit score.</p>
          <Link to="/user" className="wrapper-container-link">
            Credit Score →
          </Link>
        </div>
      </div>
    </div>
  );
}

export default Home;
