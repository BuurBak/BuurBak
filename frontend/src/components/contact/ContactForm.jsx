import { useState } from 'react'
import './ContactForm.css'

export default function ContactForm() {
    const [typeOfUser, setTypeOfUser] = useState()
    const [email, setEmail] = useState("")
    const [topic, setTopic] = useState()
    const [question, setQuestion] = useState("")
    const [description, setDescription] = useState("")
    const [url, setUrl] = useState("")

    return (
        <div className="formContainer">
            <b>Niet gevonden wat je zocht? <span>Neem contact op!</span></b>
            <p>Geen antwoord gekregen op je vraag? Neem contact op en we komen zo snel mogelijk bij je terug.</p>
            <form className="formQuestions">
                <span>Bent u huurder of verhuurder?</span>
                <div className="formSelect" style={{ width: '48.5%' }}>
                    <select value={typeOfUser} onChange={(e) => setTypeOfUser(e.target.value)}>
                        <option>Huurder</option>
                        <option>Verhuurder</option>
                    </select>
                </div>
                <div style={{ display: 'flex', marginTop: '30px' }}>
                    <div className="formInputItem">
                        <span>Email</span>
                        <div className="formSelect" style={{ width: '97%' }}>
                            <input placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)}></input>
                        </div>
                    </div>
                    <div className="formInputItem">
                        <span style={{ marginLeft: '3%' }}>Onderwerp</span>
                        <div className="formSelect" style={{ width: '97%', marginLeft: '3%' }}>
                            <select value={topic} onChange={(e) => setTopic(e.target.value)}>
                                <option>Waar gaat je vraag over?</option>
                            </select>
                        </div>
                    </div>
                </div>
                <span>Mijn vraag</span>
                <div className="formSelect" style={{ width: '100%' }}>
                    <input placeholder="Wat is jouw vraag?" value={question} onChange={(e) => setQuestion(e.target.value)}></input>
                </div>
                <span>Eventuele toevoegingen bij uw vraag</span>
                <div className="formSelect" style={{ width: '100%', height: 200 }}>
                    <textarea placeholder="Wat is jouw vraag?" value={description} onChange={(e) => setDescription(e.target.value)}></textarea>
                </div>
                <span>Gaat jouw vraag over een specifieke verhuurder/aanhanger? Vul hier de URL van de verhuurpagina in</span>
                <div className="formSelect" style={{ width: '100%' }}>
                    <input placeholder="Http://www.buurbak.nl/aanbod" value={url} onChange={(e) => setUrl(e.target.value)}></input>
                </div>
                <button className="formCta">Vraag verzenden</button>
            </form>
        </div >
    )
}