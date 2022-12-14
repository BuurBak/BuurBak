import { useState } from 'react'
import { TbCaravan, TbKey } from 'react-icons/tb'
import Map from '../../trailers/offer/Map'
import './Location.css'
import { Autocomplete } from '@react-google-maps/api'

export default function Location() {
  const [activeOption, setActiveOption] = useState(true)
  const [location, setLocation] = useState('')

  return (
    <div className="formStepContainer" style={{ width: '35%' }}>
      <h2 onClick={() => console.log(location)}>Locatie</h2>
      <p>
        Geef de locatie van de aanhanger die je wilt verhuren op. De gebruiker
        kan de exacte locatie niet zien totdat een reservering is geplaatst.
      </p>
      <div className="locationOptionsContainer">
        <div
          className={
            activeOption ? 'locationOptionsItemActive' : 'locationOptionsItem'
          }
          onClick={() => setActiveOption(true)}
        >
          <TbKey size="22px" />
          <p>Aanhanger kan zonder contact worden meegenomen</p>
          <div className="locationOptionSelect">
            {activeOption ? (
              <div className="locationOptionSelectActive" />
            ) : null}
          </div>
        </div>
        <div
          className={
            activeOption ? 'locationOptionsItem' : 'locationOptionsItemActive'
          }
          onClick={() => setActiveOption(false)}
        >
          <TbCaravan size="22px" />
          <p>Aanhanger slot moet worden opgehaald</p>
          <div className="locationOptionSelect">
            {activeOption ? null : (
              <div className="locationOptionSelectActive" />
            )}
          </div>
        </div>
      </div>
      <div className="mapContainer">
        <Map />
        <Autocomplete>
          <input
            value={location}
            onChange={(e) => setLocation(e.target.value)}
            className="locationInput"
            placeholder="Locatie..."
          />
        </Autocomplete>
      </div>
    </div>
  )
}
