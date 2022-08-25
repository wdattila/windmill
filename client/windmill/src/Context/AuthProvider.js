import { createContext, useContext, useState } from "react";
import { login } from "../Api/UserApi";

let AuthContext = createContext(null);

export const useAuth = () => {
    return useContext(AuthContext);
}

let AuthProvider = ({children}) => {
    const [accessToken, setAccessToken] = useState(localStorage.getItem('access_token'));

    const signIn = async (username, password) => {
        let token = await login(username, password);
        if(token){
            setAccessToken(token);
            localStorage.setItem('access_token', token);
        }
    }

    const signOut = () => {
        localStorage.removeItem('access_token');
        setAccessToken(localStorage.getItem('access_token'));
    }

    const values = {accessToken, signIn, signOut}

    return <AuthContext.Provider value={values}>
        {children}
    </AuthContext.Provider>
}

export default AuthProvider;