// src/App.js
import React, { useState } from 'react';
import './App.css'; // Importing the CSS file

const FuelNestRegistration = () => {
  const [otpVisible, setOtpVisible] = useState(false);
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [email, setEmail] = useState('');
  const [mobile, setMobile] = useState('');
  const [otp, setOtp] = useState('');
  const [passwordValid, setPasswordValid] = useState({
    length: false,
    uppercase: false,
    number: false,
    specialChar: false,
  });

  const handlePasswordChange = (e) => {
    const value = e.target.value;
    setPassword(value);

    // Validate password
    setPasswordValid({
      length: value.length >= 8,
      uppercase: /[A-Z]/.test(value),
      number: /\d/.test(value),
      specialChar: /[!@#$%^&*]/.test(value),
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Handle form submission logic
    console.log({
      email,
      mobile,
      password,
      confirmPassword,
      otp,
    });
  };

  const handleOtpSend = () => {
    setOtpVisible(true);
    // Logic to send OTP
  };

  const handleOtpVerify = () => {
    // Logic to verify OTP
    console.log('OTP Verified:', otp);
  };

  const handleGoBack = () => {
    // Logic to go back to the previous page
    console.log('Go Back');
  };

  const handleGoLogin = () => {
    // Logic to go to login page
    console.log('Go to Login');
  };

  return (
    <div className="container">
      <div className="left-section">
        <h1>Welcome to FuelNest!</h1>
        <p>Your trusted fuel and repair service provider</p>
        <button id="signupButton" onClick={handleGoLogin}>Login</button>
        <button id="backButton" style={{ marginTop: '20px' }} onClick={handleGoBack}>Back</button>
      </div>

      <div className="right-section">
        <div className="form-container">
          <h2>Fuel Station Register</h2>
          <form onSubmit={handleSubmit}>
            <div className="form-row">
              <div className="form-group">
                <label htmlFor="first-name">First Name <span className="required">*</span></label>
                <input
                  type="text"
                  id="first-name"
                  placeholder="Enter first name"
                  required
                />
              </div>
              <div className="form-group">
                <label htmlFor="last-name">Last Name <span className="required">*</span></label>
                <input
                  type="text"
                  id="last-name"
                  placeholder="Enter last name"
                  required
                />
              </div>
            </div>
            <div className="form-row">
              <div className="form-group">
                <label htmlFor="email">Email Address <span className="required">*</span></label>
                <input
                  type="email"
                  id="email"
                  placeholder="Enter email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
                <button
                  type="button"
                  id="sendOtpButton"
                  onClick={handleOtpSend}
                >
                  Send Email OTP
                </button>
              </div>
              <div className="form-group">
                <label htmlFor="mobile">Mobile Number <span className="required">*</span></label>
                <input
                  type="tel"
                  id="mobile"
                  placeholder="Enter mobile number"
                  value={mobile}
                  onChange={(e) => setMobile(e.target.value)}
                  required
                />
              </div>
            </div>

            {otpVisible && (
              <div className="form-row otp">
                <div className="form-group">
                  <label htmlFor="otp">OTP <span className="required">*</span></label>
                  <input
                    type="text"
                    id="otp"
                    value={otp}
                    onChange={(e) => setOtp(e.target.value)}
                    placeholder="Enter OTP"
                  />
                  <button
                    id="verifyOtpButton"
                    type="button"
                    onClick={handleOtpVerify}
                  >
                    Verify OTP
                  </button>
                </div>
              </div>
            )}

            <div className="form-group">
              <label htmlFor="password">Password<span className="required">*</span></label>
              <input
                type="password"
                id="password"
                value={password}
                onChange={handlePasswordChange}
                required
              />
            </div>

            <div id="password-validation" className="password-validation">
              <p className={passwordValid.length ? 'valid' : 'invalid'}>
                Password must be at least 8 characters long.
              </p>
              <p className={passwordValid.uppercase ? 'valid' : 'invalid'}>
                Password must contain at least one uppercase letter.
              </p>
              <p className={passwordValid.number ? 'valid' : 'invalid'}>
                Password must contain at least one number.
              </p>
              <p className={passwordValid.specialChar ? 'valid' : 'invalid'}>
                Password must contain at least one special character (!@#$%^&*).
              </p>
            </div>

            <div className="form-group">
              <label htmlFor="confirm-password">Confirm Password <span className="required">*</span></label>
              <input
                type="password"
                id="confirm-password"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                required
              />
            </div>

            <div className="form-row">
              <button type="submit">Register</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default FuelNestRegistration;
