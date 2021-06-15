import React, { useState } from 'react'
import Register from './Register';
import '../css/Form.css';

const Form = () => {
    const [isSubmitted, setIsSubmitted] = useState(false)

    function submitForm() {
        setIsSubmitted(true);
    }

    return (
        <>
            <div className="form-container">
                {!isSubmitted ? (
                    <Register submitForm={submitForm} />) : (<Register />)}
            </div>
        </>
    );
}

export default Form
