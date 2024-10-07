// src/components/Login.js

import React, { useState } from 'react';
import API from '../api';
import './Login.css';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [form, setForm] = useState({
        email: '',
        password: '',
    });
    const [message, setMessage] = useState('');
    const [variant, setVariant] = useState('success'); // For Alert variant
    const navigate = useNavigate();

    const handleChange = e => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async e => {
        e.preventDefault();
        setMessage(''); // Clear previous messages
        try {
            const response = await API.post('/login', form);
            setVariant('success');
            setMessage(response.data);
            // Optionally, handle authentication tokens here
            // Redirect to a protected route
            navigate('/dashboard'); // Ensure you have a Dashboard component
        } catch (error) {
            setVariant('danger');
            if (error.response && error.response.data) {
                setMessage(error.response.data);
            } else {
                setMessage('An error occurred during login.');
            }
        }
    };

    return (
        <div className="login-container">
            <h2>Login</h2>
            {message && <div className={`alert alert-${variant}`} role="alert">{message}</div>}
            <form onSubmit={handleSubmit}>
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
                        className="form-control"
                    />
                </div>

                <button type="submit" className="btn-submit">Login</button>
            </form>
        </div>
    );
};

export default Login;
