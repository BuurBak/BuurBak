import { useEffect, useState } from 'react'
import './TrailerLocation.css'
import AutoComplete from '../hook/AutoComplete'
import { GoogleMap, MarkerF } from '@react-google-maps/api'
import mapStyles from '../../../data/mapStyles'

// Google map styling

const mapContainerStyle = { width: '100%', height: '100%', borderRadius: 10 }

const options = {
  styles: mapStyles,
  disableDefaultUI: true,
  clickableIcons: false,
}

export default function Location({ geoCode, setGeoCode, address, setAddress }) {
  const [location, setLocation] = useState({})
  const [showLocationDetails, setShowLocationDetails] = useState(false)
  const center = geoCode
    ? { lat: geoCode.lat, lng: geoCode.lng }
    : { lat: 52.090736, lng: 5.12142 }

  // Check google map address input

  useEffect(() => {
    if (Object.keys(location).length > 0) {
      setShowLocationDetails(true)
      setAddress({
        streetName: location[0]?.address_components[1]?.long_name,
        city: location[0]?.address_components[2]?.long_name,
        postalCode: location[0]?.address_components[6]?.long_name,
        houseNumber: location[0]?.address_components[0]?.long_name,
      })
    } else if (Object.keys(address).length > 0) {
      setShowLocationDetails(true)
      setAddress({
        streetName: address.streetName,
        city: address.city,
        postalCode: address.postalCode,
        houseNumber: address.houseNumber,
      })
    } else {
      setShowLocationDetails(false)
    }
  }, [location])

  // Manual update address

  const updateStats = (e: any) => {
    const fieldName = e.target.name
    setAddress((stats: any) => ({
      ...stats,
      [fieldName]: e.target.value,
    }))
  }

  return (
    <div className="formStepContainer">
      <h2>Locatie</h2>
      <p>
        Geef de locatie van de aanhanger die je wilt verhuren op. De gebruiker
        kan de exacte locatie niet zien totdat een reservering is geplaatst.
      </p>
      {showLocationDetails && (
        <div className="exactLocationInput">
          <div className="locationInputDivider">
            <div
              className="exactLocationInputItem"
              style={{ borderRight: '1px solid var(--border)' }}
            >
              <input
                type="text"
                value={address.streetName}
                required
                onChange={updateStats}
                id="streetName"
                name="streetName"
              />
              <span>Straatnaam</span>
            </div>
            <div className="exactLocationInputItem">
              <input
                type="number"
                required
                value={address.houseNumber}
                onChange={updateStats}
                id="houseNumber"
                name="houseNumber"
              />
              <span>Huisnummer</span>
            </div>
          </div>
          <div className="exactLocationInputItem">
            <input type="text" required />
            <span>Toevoeging (optioneel)</span>
          </div>
          <div
            className="locationInputDivider"
            style={{ gridTemplateColumns: '50% 50%' }}
          >
            <div
              className="exactLocationInputItem"
              style={{
                borderRight: '1px solid var(--border)',
                borderBottom: 'none',
              }}
            >
              <input
                type="text"
                required
                value={address.postalCode}
                onChange={updateStats}
                id="postalCode"
                name="postalCode"
              />
              <span>Postcode</span>
            </div>
            <div
              className="exactLocationInputItem"
              style={{
                borderBottom: 'none',
              }}
            >
              <input
                type="text"
                required
                value={address.city}
                onChange={updateStats}
                id="city"
                name="city"
              />
              <span>Stad</span>
            </div>
          </div>
        </div>
      )}
      <div className="mapContainer">
        <GoogleMap
          zoom={10}
          center={center}
          options={options}
          mapContainerStyle={mapContainerStyle}
        >
          {geoCode && (
            <MarkerF
              key={geoCode.lat}
              position={{
                lat: geoCode.lat,
                lng: geoCode.lng,
              }}
            />
          )}
        </GoogleMap>
        <AutoComplete setLocation={setLocation} setGeoCode={setGeoCode} />
      </div>
    </div>
  )
}
