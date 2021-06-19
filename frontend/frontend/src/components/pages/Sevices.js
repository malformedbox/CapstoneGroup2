import React from 'react';
import '../../App.css';
import '../../css/Services.css';


function Services() {
  return (
    <div>
      <div class="jumbotronService text-center">
        <div class="jumbotronContainerService">
          <h1 class="jumbotronHeaderService">Account Services</h1>
          {/* <p class="jumbotronTextService">
            We offer many accounts. Choose one that fits your needs.
          </p> */}
        </div>
      </div>

      <div className="accountServices">
        <div className="account">
          <h2 className="accountHeader">Certicate of Deposit (CD) Accounts</h2>< hr/>
          <p className="accountInfo">A Certified Deposit account can be opened from a selection of offerings with terms ranging from one to ten years with diverse
            and competitive interest rates making a cd account a great way to lock in and save for your future while your money does all the hard work for you. 
             Open as many Certified Deposit accounts as fit your needs! </p>
          <ul>
            <li>Terms & Interest Rates</li>
            <ul>
              <li> 1 Year at 1.8%</li>
              <li> 3 Years at 2.0%</li>
              <li> 5 Years at 2.3%</li>
              <li> 10 Years at 2.6%</li>
            </ul>
          </ul>
        </div>
        <div className="account">
          <h2 className="accountHeader">Checking Accounts</h2>< hr/>
          <p className="accountInfo">We offer both Personal Checking and DBA Checking accounts to fill all your personal and professional needs in one place. A
          Personal Checking account is a great way to have quick and easy access to your money wherever and whenever you need it while a DBA checking can provide
          you the same easy access to your business accounts.</p>
          <ul>
            <li>Interest Rates</li>
            <ul>
              <li>Personal Checking</li>
              <ul>
                <li> 1.0%</li>
              </ul>
              <li>DBA Checking</li>
              <ul>
                <li> 2.0%</li>
              </ul>
            </ul>
          </ul>
        </div>
        <div className="account">
          <h2 className="accountHeader">Savings Accounts</h2>< hr/>
          <p className="accountInfo">Our savings accounts come with great interest rates and more flexibility than our IRA account so you still have access to 
          your money and the ability to deposit, withdraw and transfer all while still gaining interest on your current balance so start saving with us today
          with a simple savings account.
          </p>
          <ul>
            <li>Interest Rate</li>
            <ul>
              <li> 2.0%</li>
            </ul>
          </ul>
        </div>
        <div className="account">
          <h2 className="accountHeader">IRA Accounts</h2>< hr/>
          <p className="accountInfo">We offer both Roth and Regular Individual Retirement Accounts and can Rollover an existing account.
          </p>
          <ul>
            <li>Interest Rates</li>
            <ul>
              <li>IRA Regular</li>
              <ul><li> 6.5%</li></ul>
              <li>IRA Roth</li>
              <ul><li> 8.0%</li></ul>
              <li>IRA Rollover</li>
              <ul><li> 8.0%</li></ul>
            </ul>
          </ul>
        </div>
      </div>
    </div>
  );
}

export default Services;