import { Link } from 'react-router-dom'

export default function Logo() {
  return (
    <Link to="/">
      <img height={60} width={160} alt="logo" src={'/logo.svg'}></img>
    </Link>
  )
}
