import { useAuth } from "./AuthProvider"
import { Navigate, Outlet, useLocation } from "react-router-dom";

const RequiredAuth = ({children}) => {
    const auth = useAuth();
    const location = useLocation();

    if(!auth.accessToken){
        return <Navigate to={"/login"} state={{from: location}} replace/>;
    }

    return children? children : <Outlet></Outlet>;
}

export default RequiredAuth;