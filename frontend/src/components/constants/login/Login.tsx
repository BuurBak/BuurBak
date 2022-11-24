import { IoIosClose } from 'react-icons/io'
import './Login.css'
import { IconButton, TextField } from '@mui/material'
import { useEffect, useState } from 'react'
import { AiFillApple, AiOutlineGooglePlus } from 'react-icons/ai'
import { FaFacebookF } from 'react-icons/fa'
import useAxios from '../../../hooks/use-axios'
import { useAuth } from '../../../hooks/use-auth'
import { PaginatedResponse } from '../../../types/PaginatedResponse'
import { User } from '../../../types/User'
import { CreateAddress } from '../../../types/CreateAddress'
import React from 'react'

export default function Login({ setShowLogin }) {
  const { login, register } = useAuth()

  // Formdata
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [name, setName] = useState('')
  const [number, setNumber] = useState('')
  const [address, setAddress] = useState<CreateAddress>({
    city: 'Utrecht',
    number: '69',
    postal_code: '1107HF',
    street_name: 'de wallen',
  })

  // logic data
  const [userExists, setUserExists] = useState(false)

  // request
  const { response, loading, error, refetch } = useAxios<
    PaginatedResponse<User>
  >({
    method: 'GET',
    url: '/customers',
    params: {
      email,
    },
  })

  useEffect(() => {
    refetch()
  }, [email])

  // check if user exists
  useEffect(() => {
    if (!response) return

    setUserExists(response.content.length > 0 && email.length > 0)
  }, [response])

  const handleLogin = async (e) => {
    e.preventDefault()
    try {
      await login({ username: email, password })
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
      register({ email, password, name, number, address })
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
          size="medium"
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
              size="medium"
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
                        size="medium"
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
                      size="medium"
                      label="Postcode"
                      variant="outlined"
                    />
                    <TextField
                      className="secondaryInput"
                      type="number"
                      size="medium"
                      label="Huis nr."
                      variant="outlined"
                    />
                    <TextField
                      className="secondaryInput"
                      size="medium"
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
                    size="medium"
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
                    size="medium"
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
