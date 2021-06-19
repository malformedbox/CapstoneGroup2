import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import "./App.css";
import Home from "./components/pages/Home";
import Profile from "./components/pages/Profile";
import UserPage from "./components/pages/UserPage";
import Services from "./components/pages/Sevices";
import AboutUs from "./components/pages/AboutUs";
import Login from "./components/pages/Login";
import Register from "./components/pages/Register";
import CreateIraRegular from "./createAccounts/CreateIraRegular";
import CreateIraRollover from "./createAccounts/CreateIraRollover";
import CreateIraRoth from "./createAccounts/CreateIraRoth";
import CreatePersonalChecking from "./createAccounts/CreatePersonalChecking";
import TransactionDW from "./components/pages/TransactionDW";
import TransactionT from "./components/pages/TransactionT";
import Footer from "./components/FooterComponent";

function App() {
  return (
    <>
      <Router>
        <Navbar />
        <Switch>
          <Route path="/" exact={true} component={Home} />
          <Route path="/home" exact={true} component={Home} />
          <Route path="/profile" exact={true} component={Profile} />
          <Route path="/user" exact={true} component={UserPage} />
          <Route path="/services" component={Services} />
          <Route path="/aboutus" component={AboutUs} />
          <Route path="/login" component={Login} />
          <Route path="/register" component={Register} />
          <Route path="/createiraregular" component={CreateIraRegular} />
          <Route path="/createirarollover" component={CreateIraRollover} />
          <Route path="/createiraroth" component={CreateIraRoth} />
          <Route
            path="/createpersonalchecking"
            component={CreatePersonalChecking}
          />
          <Route path="/transactiondw" component={TransactionDW} />
          <Route path="/transactiont" component={TransactionT} />
        </Switch>
        <Footer />
      </Router>
    </>
  );
}

export default App;
