import { IoIosClose } from 'react-icons/io'
import './Login.css'
import { IconButton, TextField } from '@mui/material'
import { useEffect, useState } from 'react'
import Users from '../../../data/dummy/users.json'
import { AiFillApple, AiOutlineGooglePlus } from 'react-icons/ai'
import { FaFacebookF } from 'react-icons/fa'
import AuthService from '../../../data/services/auth.service'

export default function Login({ setShowLogin }) {
    const [email, setEmail] = useState("")
    const [users] = useState(Users)
    const [userExists, setUserExists] = useState()
    const [password, setPassword] = useState("")
    const [firstName, setFirstName] = useState("")
    const [lastName, setLastName] = useState("")
    const [number, setNumber] = useState("")
    const [address, setAddress] = useState()

    useEffect(() => {
        const checkIfUserExists = users.filter((user) => user.email === email)
        if (checkIfUserExists) {
            setUserExists(checkIfUserExists)
        } else {
            setUserExists(null)
        }
    }, [email, users])

    const handleLogin = async (e) => {
        e.preventDefault()
        try {
            await AuthService.login(email, password).then(
                () => {
                    setShowLogin(false)
                },
                (error) => {
                    console.log(error)
                }

            )
        } catch (err) {
            console.log(err)
        }
    }

    const handleSignup = async (e) => {
        e.preventDefault()
        try {
            await AuthService.signUp(email, password, firstName, lastName, number, address).then(
                (response) => {
                    setShowLogin(false)
                    console.log("Sign up succesfull", response)
                },
                (error) => {
                    console.log(error)
                }
            )
        } catch (err) {
            console.log(err)
        }
    }

    return (
        <div className="pageContainer">
            <div className="backgroundBlur" onClick={() => setShowLogin(false)}></div>
            <div className="popUpContainer">
                <p><span>Log in</span> of <span>Meld je aan</span> met je email adres</p>
                <TextField className='primaryInput' size="large" label="Email" value={email} variant="outlined" onChange={(e) => setEmail(e.target.value)} />
                {!email ?
                    <p style={{ textAlign: 'center', marginLeft: 'auto', marginRight: 'auto' }}><span>Smart login</span> - type je email adres in, als je al een account hebt wordt je automatisch doorverwezen naar de login pagina. Als het email adres niet bij ons bekend is zetten we account op!</p>
                    : null}
                {userExists?.length > 0 ? <p>Welkom terug <span>{userExists?.[0]?.firstName}</span>. Laten we inloggen!</p> : null}
                {!userExists?.length && email ? <p>Hmm je email wordt niet herkend, laten we een account aanmaken!</p> : null}
                {!email ?
                    <>
                        <div className="line"><p>Of</p></div>
                        <div className="alternateLoginBtn" style={{ backgroundColor: '#4664BE' }}><FaFacebookF color="white" size="20px" style={{ marginRight: 15 }} />Inloggen met Facebook</div>
                        <div className="alternateLoginBtn" style={{ backgroundColor: '#D15D5D' }}><AiOutlineGooglePlus size="26px" color="white" style={{ marginRight: 15 }} />Inloggen met Google</div>
                        <div className="alternateLoginBtn"><AiFillApple color="white" size="22px" style={{ marginRight: 15 }} />Inloggen met Apple</div>
                    </>
                    : null}
                {userExists?.length > 0
                    ? <form className="loginContent" onSubmit={handleLogin}>
                        <TextField className='primaryInput' type="password" value={password} size="large" label="Wachtwoord" variant="outlined" onChange={(e) => setPassword(e.target.value)} />
                        <button type="submit" className="loginCta">Inloggen</button>
                    </form>
                    : <>
                        {email ?
                            <form className="registerContent" onSubmit={handleSignup}>
                                <div className="flexBox">
                                    <div style={{ marginRight: '5%' }}>
                                        <p>Voornaam</p>
                                        <div className="inputItem">
                                            <TextField className='secondaryInput' value={firstName} size="large" label="Voornaam" variant="outlined" onChange={(e) => setFirstName(e.target.value)} />
                                        </div>
                                    </div>
                                    <div style={{ marginLeft: '5%' }}>
                                        <p>Achternaam</p>
                                        <div className="inputItem">
                                            <TextField className='secondaryInput' value={lastName} size="large" label="Achternaam" variant="outlined" onChange={(e) => setLastName(e.target.value)} />
                                        </div>
                                    </div>
                                </div>
                                <div className="registerItem">
                                    <p>Adres</p>
                                    <div className="addressInput">
                                        <TextField className='secondaryInput' size="large" label="Postcode" variant="outlined" />
                                        <TextField className='secondaryInput' size="large" label="Huis nr." variant="outlined" />
                                        <TextField className='secondaryInput' size="large" label="Woonplaats" variant="outlined" />
                                    </div>
                                </div>
                                <div className="registerItem">
                                    <p>Telefoonnummer</p>
                                    <TextField className='secondaryInput' value={number} type="tel" size="large" label="Nummer" variant="outlined" onChange={(e) => setNumber(e.target.value)} />
                                </div>
                                <div className="registerItem">
                                    <p>Wachtwoord</p>
                                    <TextField className='secondaryInput' value={password} size="large" type="password" label="Wachtwoord" variant="outlined" onChange={(e) => setPassword(e.target.value)} />
                                </div>
                                <button type="submit" className="loginCta">Registreren</button>
                            </form>
                            : null}
                    </>
                }
                <IconButton className="closeIcon" onClick={() => setShowLogin(false)}>
                    <IoIosClose size="30px" color="black" />
                </IconButton>
            </div>
        </div>
    )
}