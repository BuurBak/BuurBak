import Map from '../../trailers/offer/Map'
import './Location.css'

export default function Location() {
    return (
        <div className="formStepContainer">
            <h2>Locatie</h2>
            <p>Geef  de locatie van de aanhanger die je wilt verhuren op. De gebruiker kan de exacte locatie niet zien totdat een reservering is geplaatst.</p>
            <div className="mapContainer">
                <div className="mapOverlay" />
                <Map />
                <div className="locationInputContainer">
                    <div className="flexbox">
                        <div className="inputContainer" style={{marginRight: 15}}>
                            <input className="inputContainer" placeholder="Postcode" />
                        </div>
                        <div className="inputContainer" style={{marginRight: 15}}>
                            <input className="inputContainer" placeholder="Huisnummer" />
                        </div>
                        <div className="inputContainer">
                            <input className="inputContainer" placeholder="Toevoeging" />
                        </div>
                    </div>
                    <div className="inputContainer" style={{width: '100%', marginTop: 20}}>
                        <input className="inputContainer" placeholder="Woonplaats" />
                    </div>
                </div>
            </div>
        </div>
    )
}