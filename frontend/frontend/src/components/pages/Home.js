import React from 'react';
import '../../App.css';
import Cards from '../Cards';
import { Jumbotron } from 'reactstrap';

function Home() {
  return (
    <div>
      <Jumbotron fluid>
        <h1>Fluid jumbotron</h1>
        <p className="jumbotron-text">This is a modified jumbotron that occupies the entire horizontal space of
            its parent.
        </p>
      </Jumbotron>
      <Cards />
    </div>
  );
}

export default Home;