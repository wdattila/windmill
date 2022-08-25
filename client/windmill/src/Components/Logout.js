import { Navigate, useLocation } from "react-router-dom";
import { useAuth } from "../Context/AuthProvider"

const Logout = () => {
    const auth = useAuth();
    const location = useLocation();
    
    auth.signOut();
    return <Navigate to={'/'} state={{from: location}} replace></Navigate>
}

export default Logout;