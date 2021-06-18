import React from "react";
import { Link } from "react-router-dom";
import { Button } from "./Button";
import "../css/Footer.css";

function Footer(props) {
  return (
    <div className="footer-container">
      <div className="footer-links">
        <div className="footer-link-wrapper">
          <div className="footer-link-items">
            <h2>Products</h2>
            <Link to="/login">Credit Cards</Link>
            <Link to="/login">Certificate of Deposit</Link>
            <Link to="/login">DBA & Personal Checking</Link>
            <Link to="/login">IRA</Link>
            <Link to="/login">Savings</Link>
          </div>
        </div>
        <div className="footer-link-wrapper">
          <div className="footer-link-items">
            <h2>About Us</h2>
            <Link to="/login">About</Link>
            <Link to="/login">Corporate Information</Link>
            <Link to="/login">Careers & Jobs</Link>
            <Link to="/login">Newsroom & Jobs</Link>
          </div>
        </div>
        <div className="footer-link-wrapper">
          <div className="footer-link-items">
            <h2>Legal</h2>
            <Link to="/login">Privacy</Link>
            <Link to="/login">Additional Disclosures</Link>
          </div>
        </div>
        <div className="footer-link-wrapper">
          <div className="footer-link-items">
            <h2>Support</h2>
            <Link to="/login">Support Center</Link>
            <Link to="/login">FAQs</Link>
            <Link to="/login">Contact Us</Link>
          </div>
        </div>
      </div>
      <section class="social-media">
        <div class="social-media-wrap">
          <div class="footer-logo">
            <Link to="/" className="social-logo">
              MeritBank
            </Link>
          </div>
          <small class="group-name">Capstone Group 2</small>
          <div class="social-icons">
            <Link
              class="social-icon-link facebook"
              to={{ pathname: "https://www.facebook.com/" }}
              target="_blank"
              aria-label="Facebook"
            >
              <i class="fab fa-facebook-f" />
            </Link>
            <Link
              class="social-icon-link instagram"
              to={{ pathname: "https://www.instagram.com/" }}
              target="_blank"
              aria-label="Instagram"
            >
              <i class="fab fa-instagram" />
            </Link>
            <Link
              class="social-icon-link youtube"
              to={{ pathname: "https://www.youtube.com/" }}
              target="_blank"
              aria-label="Youtube"
            >
              <i class="fab fa-youtube" />
            </Link>
            <Link
              class="social-icon-link twitter"
              to={{ pathname: "https://twitter.com" }}
              target="_blank"
              aria-label="Twitter"
            >
              <i class="fab fa-twitter" />
            </Link>
            <Link
              class="social-icon-link twitter"
              to={{ pathname: "https://www.linkedin.com" }}
              target="_blank"
              aria-label="LinkedIn"
            >
              <i class="fab fa-linkedin" />
            </Link>
          </div>
        </div>
      </section>
    </div>
  );
}

export default Footer;
