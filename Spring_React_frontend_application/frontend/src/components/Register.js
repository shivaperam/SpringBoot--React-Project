// src/components/Register.js

import React, { useState } from 'react';
import API from '../api';
import './Register.css';

const Register = () => {
    const [form, setForm] = useState({
        name: '',
        email: '',
        password: '',
    });
    const [message, setMessage] = useState('');
    const [variant, setVariant] = useState('success'); // For Alert variant

    const handleChange = e => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            const response = await API.post('/register', form);
            setVariant('success');
            setMessage(response.data);
            setForm({ name: '', email: '', password: '' }); // Reset form
        } catch (error) {
            setVariant('danger');
            if (error.response && error.response.data) {
                setMessage(error.response.data);
            } else {
                setMessage('An error occurred during registration.');
            }
        }
    };

    return (
        <div className="register-container">
            <h2>Register</h2>
            {message && <div className={`alert alert-${variant}`} role="alert">{message}</div>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Name</label>
                    <input
                        type="text"
                        name="name"
                        value={form.name}
                        onChange={handleChange}
                        placeholder="Enter your name"
                        required
                        minLength="2"
                        className="form-control"
                    />
                </div>

                <div className="form-group">
                    <label>Email address</label>
                    <input
                        type="email"
                        name="email"
                        value={form.email}
                        onChange={handleChange}
                        placeholder="Enter email"
                        required
                        className="form-control"
                    />
                </div>

                <div className="form-group">
                    <label>Password</label>
                    <input
                        type="password"
                        name="password"
                        value={form.password}
                        onChange={handleChange}
                        placeholder="Password"
                        required
                        minLength="6"
                        className="form-control"
                    />
                </div>

                <button type="submit" className="btn-submit">Register</button>
            </form>
        </div>
    );
};

export default Register;
