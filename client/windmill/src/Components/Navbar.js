import CSSModules from "react-css-modules"
import { Link } from "react-router-dom"
import styles from "./Navbar.module.css"

const Navbar = () => {
  return <>
    <aside styleName="sidebar">
      <header>Menu</header>
      <nav styleName="sidebar-nav">
        <ul>
          <li>
            <Link to='/' className="link"><i class="fa-solid fa-house"></i> <span>Home</span></Link>
          </li>
          <li>
            <Link to='/login' className="link"><i className="fa-solid fa-right-to-bracket"></i> <span>Login</span></Link>
          </li>
        </ul>
      </nav>
    </aside></>
}

export default CSSModules(Navbar, styles, {allowMultiple: true})