import React from 'react';
import './Button.css'
import { Link } from 'react-router-dom';

const STYLES = ['btn--primary', 'btn--outline'];

const SIZES = ['btn--medium', 'btn--large'];

export const Button = ({
    children, 
    type, 
    onClick, 
    buttonStyle, 
    buttonSize}) => {
        //if STYLES.includes has a buttonStyle then use the buttonStyle created for it; 
        //if not true, set it to first STYLES in array (btn-primary is default)
        const checkButtonStyle = STYLES.includes(buttonStyle) 
            ? buttonStyle 
            : STYLES[0]

            const checkButtonSize = SIZES.includes(buttonSize) ? buttonSize : SIZES[0];

            return(
                <Link to='/login' className='btn-mobile'>
                    <button
                        className={`btn ${checkButtonStyle} ${checkButtonSize}`}
                        onClick={onClick}
                        type={type}
                    >
                        {children}
                    </button>
                </Link>
            )
    };