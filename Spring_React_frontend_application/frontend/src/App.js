// src/App.js

import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Register from './components/Register';
import Login from './components/Login';
import Dashboard from './components/Dashboard'; // Ensure you have this component
import './App.css'; // Create this file for any global styles

const App = () => {
    return (
        <Router>
            <nav className="navbar">
                <div className="nav-container">
                    <Link to="/" className="nav-logo">MyApp</Link>
                    <ul className="nav-menu">
                        <li className="nav-item">
                            <Link to="/register" className="nav-links">Register</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/login" className="nav-links">Login</Link>
                        </li>
                    </ul>
                </div>
            </nav>
            <div className="container">
                <Routes>
                    <Route path="/register" element={<Register />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/dashboard" element={<Dashboard />} />
                </Routes>
            </div>
        </Router>
    );
};

export default App;
