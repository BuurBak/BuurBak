import { useRef, useEffect } from 'react'
import '../formsteps/TrailerLocation.css'

const AutoComplete = ({ setLocation, setGeoCode }) => {
  const autoCompleteRef = useRef()
  const inputRef = useRef()
  const options = {
    componentRestrictions: { country: 'nl' },
    fields: ['address_components', 'geometry.location'],
  }

  useEffect(() => {
    autoCompleteRef.current = new window.google.maps.places.Autocomplete(
      inputRef.current,
      options
    )
    autoCompleteRef.current.addListener('place_changed', async function () {
      const place = await autoCompleteRef.current.getPlace()
      const lat = place.geometry.location.lat()
      const lng = place.geometry.location.lng()
      setGeoCode({ lat, lng })
      setLocation([place])
    })
  }, [])

  return (
    <div>
      <input
        ref={inputRef}
        className="locationInput"
        placeholder="Locatie..."
      />
    </div>
  )
}

export default AutoComplete
