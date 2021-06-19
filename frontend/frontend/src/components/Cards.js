import React from 'react';
import CardItem from './CardItem';
import '../css/Cards.css';

function Cards() {
    return (
        <div className='cards'>
            <h1>Our Account Offerings</h1>
            <div className="cards__container">
                <div className="cards__wrapper">
                    <ul className="cards__items">
                        <CardItem 
                            src='assets/images/catthumbsupsad.png'
                            text='Explore checking account options'
                            label='Personal'
                            path='/services'
                        />
                        <CardItem 
                            src='assets/images/catthumbsupwot.png'
                            text='Explore savings account options'
                            label='Savings'
                            path='/services'
                        />
                        
                    </ul>
                    <ul className="cards__items">
                        <CardItem 
                            src='assets/images/catthumbsup.png'
                            text='Explore IRA account options'
                            label='IRA'
                            path='/services'
                        />
                        <CardItem 
                            src='assets/images/catthumbsuphappy.png'
                            text='Explore CD account options'
                            label='CD'
                            path='/services'
                        />
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default Cards;