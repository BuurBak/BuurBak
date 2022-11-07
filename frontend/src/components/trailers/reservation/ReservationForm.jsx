import { Checkbox, FormControlLabel, TextareaAutosize } from '@mui/material'
import { useEffect, useState } from 'react'
import LicenseWarning from '../trailerProfile/LicenseWarnign'
import './ReservationForm.css'
import { useParams } from "react-router-dom";
import Data from '../../../data/dummy/trailers.json'
import TrailerOverview from './TrailerOverview';
import { FiMail, FiPhone, FiUser } from 'react-icons/fi'
import { IoIosStar } from 'react-icons/io';
import Footer from '../../constants/footer/Footer';

export default function ReservationForm() {
    const [firstName, setFirstName] = useState("")
    const { id } = useParams()
    const [trailers, setTrailers] = useState(Data)
    const trailerDetails = trailers.filter((trailer) => {
        return trailer.id === id
    })

    return (
        <div>
            <div className="reservationContainer">
                <form className="reservationForm">
                    <h3>Reserveer {trailerDetails?.[0].title}</h3>
                    <p>Toch niet helemaal waar je naar opzoek bent? <span>Zoek verder</span></p>
                    <div className="divider" />
                    <div className="reservationContainerOwner">
                        <div className="ownerContainerHeader">
                            <div className="avatar"></div>
                            <div className="ownerContainerHeaderInfo">
                                <p>Verhuurder: Andries Regenhout</p>
                                <div>
                                    <IoIosStar />
                                    <IoIosStar />
                                    <IoIosStar />
                                    <IoIosStar />
                                    <IoIosStar />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="divider" />
                    <div className="form">
                        <p>Controleer je gegevens om je reservering van “Bakwagen ongeremd” compleet te
                            maken. Je kan een bericht achterlaten voor de verhuurder met eventuele vragen of
                            opmerkingen, dit is niet verplicht.
                        </p>
                        <LicenseWarning />
                        <div style={{ display: 'flex', width: '100%' }}>
                            <div className="iconInputContainer" style={{ marginRight: '5%' }}>
                                <span>Voornaam</span>
                                <div className="iconInput">
                                    <div className="iconContainer"><FiUser /></div>
                                    <input placeholder="Voornaam"></input>
                                </div>
                            </div>
                            <div className="iconInputContainer">
                                <span>Achternaam</span>
                                <div className="iconInput">
                                    <div className="iconContainer"><FiUser /></div>
                                    <input placeholder="Achternaam"></input>
                                </div>
                            </div>
                        </div>
                        <div className="iconInputContainer">
                            <span>Email</span>
                            <div className="iconInput">
                                <div className="iconContainer"><FiMail /></div>
                                <input placeholder="Email"></input>
                            </div>
                        </div>
                        <div className="iconInputContainer">
                            <span>Telefoonnummer</span>
                            <div className="iconInput">
                                <div className="iconContainer"><FiPhone /></div>
                                <input placeholder="Telefoonnummer"></input>
                            </div>
                        </div>
                        <div className="iconInputContainer">
                            <span>Bericht aan de verhuurder</span>
                            <div className="iconInput">
                                <TextareaAutosize className="textArea" placeholder="Uw bericht..." />
                            </div>
                        </div>
                        <div className="termsAndConditions">
                            <FormControlLabel className="termsAndConditions" control={<Checkbox />} label="Ik heb de algemene voorwaarden gelezen en ga hiermee akkoord." />
                        </div>
                        <button className="cta">Reserveren</button>
                    </div>
                </form>
                <TrailerOverview trailerDetails={trailerDetails} />
            </div>
            <Footer />
        </div>
    )
} 