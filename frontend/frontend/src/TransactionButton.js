import React, { useState } from "react";
import {
  ButtonDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
} from "reactstrap";
import { Link } from "react-router-dom";

const TransactionButton = (props) => {
  const [dropdownOpen, setOpen] = useState(false);

  const toggle = () => setOpen(!dropdownOpen);

  return (
    <ButtonDropdown isOpen={dropdownOpen} toggle={toggle}>
      <DropdownToggle caret>Initiate Transaction</DropdownToggle>
      <DropdownMenu>
        <DropdownItem name="withdrawTransaction" tag={Link} to="/transactiondw">
          Withdraw
        </DropdownItem>
        <DropdownItem divider />
        <DropdownItem name="depositTransaction" tag={Link} to="/transactiondw">
          Deposit
        </DropdownItem>
        <DropdownItem divider />
        <DropdownItem name="transferTransaction" tag={Link} to="/transactiont">
          Transfer
        </DropdownItem>
      </DropdownMenu>
    </ButtonDropdown>
  );
};

export default TransactionButton;
