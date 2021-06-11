import React from 'react';
import '../../App.css';
import { Link } from 'react-router-dom';
import { Button, } from 'reactstrap';

function Home() {
  return (
    <div>
      <div class="jumbotron jumbotron-fluid">
        <div class="container">
          <h1 class="display-3">Services We Offer</h1>
            <p class="lead">We offer many accounts. Choose an account 
              that fits your needs.
            </p>
            <p>
              <Link to='/services'>
                <Button variant="jumbotron-primary">
                  Learn more
                </Button>
              </Link>
            </p>
        </div>
      </div>
    </div>
  );
}

export default Home;