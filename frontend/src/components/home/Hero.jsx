import './Hero.css'
import { FiCalendar, FiCheckCircle } from 'react-icons/fi'
import { FaCity, FaTrailer } from 'react-icons/fa'

export default function Hero() {
    return (
        <div>
            <div className="heroContainer">
                <h1>Huur en verhuur je aanhanger via BuurBak</h1>
                <div className="heroIncluded">
                    <div className="heroIncludedItem">
                        <FiCheckCircle size="26px" color="#66CC99" />
                        <p>Altijd in de buurt</p>
                    </div>
                    <div className="heroIncludedItem">
                        <FiCheckCircle size="26px" color="#66CC99" />
                        <p>Altijd duurzaam</p>
                    </div>
                    <div className="heroIncludedItem">
                        <FiCheckCircle size="26px" color="#66CC99" />
                        <p>Altijd verzekerd</p>
                    </div>
                </div>
                <div className="heroCta">
                    <div className="heroCtaItem">
                        <span>Vertreklocatie</span>
                        <div className="inputContainer">
                            <input placeholder="Stad of postcode"></input>
                            <div className="inputContainerIcon"><FaCity /></div>
                        </div>
                    </div>
                    <div className="heroCtaItem">
                        <span>Huurmoment</span>
                        <div className="inputContainer">
                            <input placeholder="Huurmoment"></input>
                            <div className="inputContainerIcon"><FiCalendar /></div>
                        </div>
                    </div>
                    <div className="heroCtaItem">
                        <span>Wat wil je huren?</span>
                        <div className="inputContainer">
                            <select>
                                <option>Aanhanger</option>
                            </select>
                            <div className="inputContainerIcon"><FaTrailer /></div>
                        </div>
                    </div>
                    <button className="ctaAction">Bekijk het aanbod</button>
                </div>
            </div>
        </div>
    )
}