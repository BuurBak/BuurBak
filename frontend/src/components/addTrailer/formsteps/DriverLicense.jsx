import { FaRegIdCard } from 'react-icons/fa'
import './DriverLicense.css'

export default function DriverLicense({ license, setLicense }) {
    return (
        <div className="formStepContainer">
            <h2>Rijbewijs verplichting</h2>
            <p>Voor bepaalde type aanhangers is een BE of B+ rijbewijs verplicht. Niet zeker wat dit betekent? Klik op meer lezen om te weten waar jouw aanhanger onder valt.</p>
            <div className="licenseGrid">
                <div className={license === "" ? "licenseGridItemActive" : "licenseGridItem"} onClick={() => setLicense("")}>
                    <FaRegIdCard size="32px" color="var(--primary)" />
                    <b>Geen rijbewijs verplichting</b>
                    <p>Voor dit type aanhanger is er geen rijbewijsverplichting. Dit betekent dat iedereen met een standaard B rijbewijs de aanhanger mag trekken.</p>
                    <p>Meer lezen</p>
                </div>
                <div className={license === "B+" ? "licenseGridItemActive" : "licenseGridItem"} onClick={() => setLicense("B+")}>
                    <FaRegIdCard size="32px" color="var(--primary)" />
                    <b>Rijbewijs B+ verplicht</b>
                    <p>Voor dit type aanhanger is een rijbewijs B+ verplicht. Er mag een maximum gewicht van 4250 kg getrokken worden.</p>
                    <p>Meer lezen</p>
                </div>
                <div className={license === "BE" ? "licenseGridItemActive" : "licenseGridItem"} onClick={() => setLicense("BE")}>
                    <FaRegIdCard size="32px" color="var(--primary)" />
                    <b>Rijbewijs BE verplicht</b>
                    <p>Voor dit type aanhanger is een BE rijbewijs verplicht. Er mag een maximum van 3500 kg getrokken worden.</p>
                    <p>Meer lezen</p>
                </div>
            </div>
        </div>
    )
}