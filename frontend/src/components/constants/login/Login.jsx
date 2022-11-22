import { IoIosClose } from 'react-icons/io'
import './Login.css'
import { IconButton, TextField } from '@mui/material'
import { useEffect, useState } from 'react'
import { AiFillApple, AiOutlineGooglePlus } from 'react-icons/ai'
import { FaFacebookF } from 'react-icons/fa'
import AuthService from '../../../data/services/auth.service'
import useAxios from '../../../data/useAxios'
import _ from 'lodash'

export default function Login({ setShowLogin }) {
  const [email, setEmail] = useState('')
  const [userExists, setUserExists] = useState(false)
  const [password, setPassword] = useState('')
  const [name, setName] = useState('')
  const [number, setNumber] = useState('')
  const [address, setAddress] = useState()
  const { response, loading, error, refetch } = useAxios({
    method: 'GET',
    url: '/customers',
    params: {
      email,
    },
  })

  useEffect(() => {
    refetch()
  }, [email])

  useEffect(() => {
    if (loading || error || !response) return

    setUserExists(response.content.length > 0 && email)
  }, [response, loading, error])

  const handleLogin = async (e) => {
    e.preventDefault()
    try {
      await AuthService.login(email, password)
      setShowLogin(false)
    } catch (error) {
      console.error('Error logging in')
      console.error(error)
    } finally {
    }
  }

  const handleSignup = async (e) => {
    e.preventDefault()
    try {
      await AuthService.signUp(email, password, name, number, address)
      setShowLogin(false)
    } catch (error) {
      console.error(error)
    } finally {
    }
  }

  if (loading) return <p>Loading...</p>
  if (error) return <p>{error.message}</p>

  return (
    <div className="pageContainer">
      <div className="backgroundBlur" onClick={() => setShowLogin(false)}></div>
      <div className="popUpContainer">
        <p>
          <span>Log in</span> of <span>Meld je aan</span> met je email adres
        </p>
        <TextField
          className="primaryInput"
          size="large"
          label="Email"
          value={email}
          variant="outlined"
          onChange={(e) => setEmail(e.target.value)}
        />
        {!email ? (
          <p
            style={{
              textAlign: 'center',
              marginLeft: 'auto',
              marginRight: 'auto',
            }}
          >
            <span>Smart login</span> - typ je email adres in, als je al een
            account hebt wordt je automatisch doorverwezen naar de login pagina.
            Als het email adres niet bij ons bekend is zetten we account op!
          </p>
        ) : null}
        {userExists ? (
          <p>
            Welkom terug <span>{userExists?.[0]?.firstName}</span>. Laten we
            inloggen!
          </p>
        ) : null}
        {!userExists && email ? (
          <p>Hmm je email wordt niet herkend, laten we een account aanmaken!</p>
        ) : null}
        {!email ? (
          <>
            <div className="line">
              <p>Of</p>
            </div>
            <div
              className="alternateLoginBtn"
              style={{ backgroundColor: '#4664BE' }}
            >
              <FaFacebookF
                color="white"
                size="20px"
                style={{ marginRight: 15 }}
              />
              Inloggen met Facebook
            </div>
            <div
              className="alternateLoginBtn"
              style={{ backgroundColor: '#D15D5D' }}
            >
              <AiOutlineGooglePlus
                size="26px"
                color="white"
                style={{ marginRight: 15 }}
              />
              Inloggen met Google
            </div>
            <div className="alternateLoginBtn">
              <AiFillApple
                color="white"
                size="22px"
                style={{ marginRight: 15 }}
              />
              Inloggen met Apple
            </div>
          </>
        ) : null}
        {userExists ? (
          <form className="loginContent" onSubmit={handleLogin}>
            <TextField
              className="primaryInput"
              type="password"
              value={password}
              size="large"
              label="Wachtwoord"
              variant="outlined"
              onChange={(e) => setPassword(e.target.value)}
            />
            <p>
              Wachtwoord vergeten? <span>Klik hier</span>
            </p>
            <button type="submit" className="loginCta">
              Inloggen
            </button>
          </form>
        ) : (
          <>
            {email ? (
              <form className="registerContent" onSubmit={handleSignup}>
                <div>
                  <div className="registerItem">
                    <p>Volledige naam</p>
                    <div className="inputItem">
                      <TextField
                        className="secondaryInput"
                        value={name}
                        size="large"
                        label="Volledige naam"
                        variant="outlined"
                        onChange={(e) => setName(e.target.value)}
                      />
                    </div>
                  </div>
                </div>
                <div className="registerItem">
                  <p>Adres</p>
                  <div className="addressInput">
                    <TextField
                      className="secondaryInput"
                      size="large"
                      label="Postcode"
                      variant="outlined"
                    />
                    <TextField
                      className="secondaryInput"
                      type="number"
                      size="large"
                      label="Huis nr."
                      variant="outlined"
                    />
                    <TextField
                      className="secondaryInput"
                      size="large"
                      label="Woonplaats"
                      variant="outlined"
                    />
                  </div>
                </div>
                <div className="registerItem">
                  <p>Telefoonnummer</p>
                  <TextField
                    className="secondaryInput"
                    type="number"
                    value={number}
                    size="large"
                    label="Nummer"
                    variant="outlined"
                    onChange={(e) => setNumber(e.target.value)}
                  />
                </div>
                <div className="registerItem">
                  <p>Wachtwoord</p>
                  <TextField
                    className="secondaryInput"
                    type="password"
                    value={password}
                    size="large"
                    label="Wachtwoord"
                    variant="outlined"
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
                <button type="submit" className="loginCta">
                  Registreren
                </button>
              </form>
            ) : null}
          </>
        )}
        <IconButton className="closeIcon" onClick={() => setShowLogin(false)}>
          <IoIosClose size="30px" color="black" />
        </IconButton>
      </div>
    </div>
  )
}
