import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useAuth } from "../Context/AuthProvider"

const Logout = () => {
    const auth = useAuth();
    const location = useLocation();
    const navigate = useNavigate();

    useEffect(() => {
        auth?.signOut?.();
        navigate('/', {state: {from: location}, replace: true});
    }, [auth, location, navigate])

    return <></>
}

export default Logout;