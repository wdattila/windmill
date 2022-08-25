import React, { useState } from "react";
import CSSModules from "react-css-modules";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../Context/AuthProvider";
import style from "./RegisterForm.module.css";

const RegisterForm = () => {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();
    const location = useLocation();
    const auth = useAuth();

    const handleSubmit = async (event) => {
        event.preventDefault();
        await auth.signUp(username, email, password, () => {
            navigate(location.state?.form?.pathname || "/", {replace: true});
        });
    }

    return (
        <div styleName="login-box">
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <div styleName="user-box">
                    <input name="" type="text" required
                            value={username}
                            onChange={e => setUsername(e.target.value)}></input>
                    <label>
                        Username
                    </label>
                </div>
                <div styleName="user-box">
                    <input name="" type="text" required
                            value={email}
                            onChange={e => setEmail(e.target.value)}></input>
                    <label>
                        E-mail
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
                <button type="submit">Register</button>
            </form>
        </div>
    );
    
}

export default CSSModules(RegisterForm, style, {allowMultiple: true});