import { Link } from 'react-router-dom'
import './Logo.css'

export default function Logo() {
  return (
    <>
      <Link to="/" className="smallLogoContainer">
        <img
          className="logoSmall"
          height={40}
          width={40}
          alt="logo"
          src={'/smallLogo.svg'}
        ></img>
      </Link>
      <Link to="/" className="largeLogoContainer">
        <img
          className="logoLarge"
          height={40}
          width={160}
          alt="logo"
          src={'/logo.svg'}
        ></img>
      </Link>
    </>
  )
}
