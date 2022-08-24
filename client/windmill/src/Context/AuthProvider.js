import { createContext, useState } from "react";
import { login } from "../Api/UserApi";

let AuthContext = createContext(null);

let AuthProvider = ({children}) => {
    const [accessToken, setAccessToken] = useState(localStorage.getItem('access_token'));

    const signIn = async (username, password) => {
        let token = await login(password, username);
        if(token){
            setAccessToken(token);
            localStorage.setItem('access_token', token);
        }
    }

    const values = {accessToken, signIn}

    return <AuthContext.Provider value={values}>
        {children}
    </AuthContext.Provider>
}