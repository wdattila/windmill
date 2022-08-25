import React, { useState } from "react";
import CSSModules from "react-css-modules";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../Context/AuthProvider";
import style from "./LoginForm.module.css";

// Define the Login form component
const LoginForm = () => {
    const [emailOrUsername, setEmailOrUsername] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();
    const location = useLocation();
    const auth = useAuth();

    const handleSubmit = async (event) => {
        event.preventDefault();
        await auth.signIn(emailOrUsername, password, () => {
            navigate(location.state?.form?.pathname || "/", {replace: true});
        });
    }

    return (
        <div styleName="login-box">
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <div styleName="user-box">
                    <input name="" type="text" required
                            value={emailOrUsername}
                            onChange={e => setEmailOrUsername(e.target.value)}></input>
                    <label>
                        Username
                    </label>
                </div>
                <div styleName="user-box" className="user-box">
                    <input name="" type="password" required
                            value={password}
                            onChange={e => setPassword(e.target.value)}></input>
                    <label>
                        Password
                    </label>
                </div>
                <button type="submit">Log in</button>
            </form>
        </div>
    );
    
}

export default CSSModules(LoginForm, style, {allowMultiple: true});