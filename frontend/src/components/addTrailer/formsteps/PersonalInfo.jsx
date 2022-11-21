import { Avatar, FormControlLabel, IconButton, Radio, RadioGroup } from '@mui/material'
import './PersonalInfo.css'
import { FiEdit2 } from 'react-icons/fi'
import { FaEnvelope, FaMoneyBillAlt, FaPhoneAlt } from 'react-icons/fa'
import { useState } from 'react'

export default function PersonalInfo({ currentUser }) {
    const [editName, setEditName] = useState(false)
    const [editLocation, setEditLocation] = useState(false)
    const [editPhone, setEditPhone] = useState(false)
    const [editEmail, setEditEmail] = useState(false)
    const [editBillingNumber, setEditBillingNumber] = useState(false)

    return (
        <div className="formStepContainer">
            <h2>Persoonlijke gegevens</h2>
            <p>Controleer je persoonlijke gegevens als verhuurder. Dit is relevant voor het achterlaten van reviews, het bekijken van aanvragen en voor het regelen van betalingen.</p>
            <span>Verhuurt u zakelijk of als particulier?</span>
            <div style={{ marginTop: 15 }}>
                <RadioGroup
                    defaultValue="particulier"
                    name="radio-buttons-group"
                >
                    <FormControlLabel value="particulier" control={<Radio />} label="Ik verhuur als particulier" />
                    <FormControlLabel value="business" control={<Radio />} label="Ik verhuur zakelijk" />
                </RadioGroup>
            </div>
            <div className="divider" />
            <div className="personalInfoContainer">
                <Avatar className="Avatar" />
                <div>
                    <p>{currentUser.name}</p>
                    <p>{currentUser.address}</p>
                </div>
                <IconButton className="editBtn">
                    <FiEdit2 size="20px" />
                </IconButton>
            </div>
            <div className="divider" />
            <div>
                <div className="profileItem">
                    <FaPhoneAlt color="var(--text)" size=" 20px" />
                    <div>
                        <b>Telefoonnummer</b>
                        {editPhone
                            ? <input className="editInfoInput" placeholder="Telefoonnummer"></input>
                            : <p>{currentUser.phone}</p>
                        }
                    </div>
                    {editPhone
                        ? <button className="editContentAction" onClick={() => setEditPhone(false)}>Wijziging opslaan</button>
                        : <IconButton className="editBtn" onClick={() => setEditPhone(true)}>
                            <FiEdit2 size="20px" />
                        </IconButton>
                    }
                </div>
                <div className="profileItem" style={{ marginTop: 30 }}>
                    <FaEnvelope color="var(--text)" size=" 20px" />
                    <div>
                        <b>Email</b>
                        {editEmail
                            ? <input className="editInfoInput" placeholder="Email"></input>
                            : <p>{currentUser.email}</p>
                        }
                    </div>
                    {editEmail
                        ? <button className="editContentAction" onClick={() => setEditEmail(false)}>Wijziging opslaan</button>
                        : <IconButton className="editBtn" onClick={() => setEditEmail(true)}>
                            <FiEdit2 size="20px" />
                        </IconButton>
                    }
                </div>
            </div>
            <div className="divider" />
            <div className="profileItem">
                <FaMoneyBillAlt color="var(--text)" size=" 22px" />
                <div>
                    <b>Rekeningnummer</b>
                    {editBillingNumber
                            ? <input className="editInfoInput" placeholder="Email"></input>
                            : <p>{currentUser.billing}</p>
                        }
                </div>
                <IconButton className="editBtn">
                    <FiEdit2 size="20px" />
                </IconButton>
            </div>
        </div>
    )
}