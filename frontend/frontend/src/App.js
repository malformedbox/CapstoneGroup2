import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import Navbar from './components/Navbar';
import './App.css';
import Home from './components/pages/Home';
import Profile from './components/pages/Profile';
import UserPage from './components/pages/UserPage';
import Services from './components/pages/Sevices';
import AboutUs from './components/pages/AboutUs';
import Login from './components/pages/Login';
import Register from './components/pages/Register';
import Footer from './components/FooterComponent';
import UserComponent from './components/UserComponent';

function App() {
  return (
    <>
      <Router>
        <Navbar/>
        <Switch>
          <Route path='/' exact={true} component={Home}/>
          <Route path='/home' exact={true} component={Home}/>
          <Route path='/profile' exact={true} component={Profile}/>
          <Route path='/user' exact={true} component={UserPage}/>
          <Route path='/services' component={Services}/>
          <Route path='/aboutus' component={AboutUs}/>
          <Route path='/login' component={Login}/>
          <Route path='/register' component={Register}/>
        </Switch>
        <Footer/>
      </Router>
    </>
  );
}

export default App