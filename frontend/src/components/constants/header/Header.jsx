import './Header.css'
import Logo from '../../../data/images/logo.svg'
import { Link } from 'react-router-dom'
import { IoIosClose, IoIosMenu } from 'react-icons/io'
import { IconButton } from '@mui/material'
import { useState } from 'react'

export default function Header({ setShowLogin }) {
  const [showMenu, setShowMenu] = useState(false)

  return (
    <div>
      <div className="headerContainer">
        <Link to="/">
          <img className="headerLogo" alt="logo" src={Logo}></img>
        </Link>
        <IconButton className="menuBtn" onClick={() => setShowMenu(true)}>
          <IoIosMenu size="28px" color="#EE7B46" />
        </IconButton>
        <nav className="headerContent">
          <Link
            to="/aanbod"
            style={{ textDecoration: 'none', color: 'var(--secondary)' }}
          >
            Aanbod
          </Link>
          <Link
            to="/blog"
            style={{ textDecoration: 'none', color: 'var(--secondary)' }}
          >
            Blog
          </Link>
          <Link
            to="/verhuren"
            style={{ textDecoration: 'none', color: 'var(--secondary)' }}
          >
            <button className="headerAction">Ik wil verhuren</button>
          </Link>
          <div
            style={{
              textDecoration: 'none',
              color: 'var(--secondary)',
              cursor: 'pointer',
            }}
            onClick={() => setShowLogin(true)}
          >
            Inloggen
          </div>
          <Link
            to="/contact"
            style={{ textDecoration: 'none', color: 'var(--secondary)' }}
          >
            Contact
          </Link>
        </nav>
      </div>
      {showMenu ? (
        <div className="sidebarHeaderContainer">
          <div className="sidebarBlur" onClick={() => setShowMenu(false)}></div>
          <div className="sidebarHeader">
            <IconButton
              className="closeMenuBtn"
              onClick={() => setShowMenu(false)}
            >
              <IoIosClose size="32px" color="white" />
            </IconButton>
            <div className="headerMenuVertical">
              <Link
                to="/aanbod"
                style={{ textDecoration: 'none', color: 'white' }}
              >
                Aanbod
              </Link>
              <Link
                to="/blog"
                style={{
                  textDecoration: 'none',
                  color: 'white',
                  marginTop: 30,
                }}
              >
                Blog
              </Link>
              <Link
                to="/verhuren"
                style={{
                  textDecoration: 'none',
                  color: 'white',
                  marginTop: 30,
                }}
              >
                Ik wil verhuren
              </Link>
              <div
                style={{
                  textDecoration: 'none',
                  color: 'white',
                  marginTop: 30,
                }}
              >
                Inloggen
              </div>
              <Link
                to="/contact"
                style={{
                  textDecoration: 'none',
                  color: 'white',
                  marginTop: 30,
                }}
              >
                Contact
              </Link>
            </div>
          </div>
        </div>
      ) : null}
    </div>
  )
}
