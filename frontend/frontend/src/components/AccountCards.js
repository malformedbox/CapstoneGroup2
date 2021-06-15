import React from 'react';
import { Link } from 'react-router-dom';
import AccountCardsCSS from '../css/AccountCardsCSS.css';

function AccountCards({path, title, body}) {
    return (
        <div className='card-container'>
            <div className="card-content">
                <div className="card-title">
                    <h3>{title}</h3>
                </div>
                <div className="card-body">
                    <p>{body}</p>
                </div>
            </div>
            <div className="btn">
                <button>
                    <Link to='/home'>
                        Add
                    </Link>
                </button>
            </div>
        </div>
    );
}
export default AccountCards;