import CSSModules from "react-css-modules"
import { Link } from "react-router-dom"
import { useAuth } from "../Context/AuthProvider"
import styles from "./Navbar.module.css"

const Navbar = () => {
  const auth = useAuth();

  return <>
    <aside styleName="sidebar">
      <header>Menu</header>
      <nav styleName="sidebar-nav">
        <ul>
          <li>
            <Link to='/' className="link"><i className="fa-solid fa-house"></i> <span>Home</span></Link>
          </li>
          {auth.accessToken?
            <>
            <li>
              <Link to='/logout' className="link"><i className="fa-solid fa-right-from-bracket"></i> <span>Logout</span></Link>
            </li>
            </>
            :
            <>
            <li>
              <Link to='/login' className="link"><i className="fa-solid fa-right-to-bracket"></i> <span>Login</span></Link>
            </li>
            <li>
              <Link to='/register' className="link"><i className="fa-solid fa-address-card"></i> <span>Register</span></Link>
            </li>
            </>
          }  
        </ul>
      </nav>
    </aside></>
}

export default CSSModules(Navbar, styles, {allowMultiple: true})