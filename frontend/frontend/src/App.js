import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import Navbar from './components/Navbar';
import './App.css';
import Home from './components/pages/Home';
import Services from './components/pages/Sevices';
import AboutUs from './components/pages/AboutUs';
import Login from './components/pages/Login';
import Footer from './components/FooterComponent';
import UserComponent from './components/UserComponent';

function App() {
  return (
    <>
      <Router>
        <Navbar/>
        <Switch>
          <Route path='/' exact component={Home}/>
          <Route path='/services' component={Services}/>
          <Route path='/aboutus' component={UserComponent}/>
          <Route path='/login' component={Login}/>
        </Switch>
        <Footer/>
      </Router>
    </>
  );
}

export default App