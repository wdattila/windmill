import CSSModules from "react-css-modules"
import styles from "./Navbar.module.css"

const Navbar = () => {
    return <div className="app">
  <aside styleName="sidebar">
         <header>
        Menu
      </header>
    <nav styleName="sidebar-nav">
 
      <ul>
        <li>
          <a href="#"><i className="fa-solid fa-right-to-bracket"></i> <span>Login</span></a>
        </li>
      </ul>
    </nav>
  </aside>
</div>   
}

export default CSSModules(Navbar, styles, {allowMultiple: true})