import React from 'react'
import styled from 'styled-components'
import PlacesAutocomplete, {
  geocodeByAddress,
  getLatLng,
} from 'react-places-autocomplete'

function Autocomplete({ location, setLocation, setCoordinates }) {
  const handleSelect = async (value) => {
    const results = await geocodeByAddress(value)
    const latLng = await getLatLng(results[0])
    setLocation(value)
    setCoordinates(latLng)
  }

  return (
    <div>
      <PlacesAutocomplete
        value={location}
        onChange={setLocation}
        onSelect={handleSelect}
      >
        {...({
          getInputProps,
          suggestions,
          getSuggestionItemProps,
          loading,
        }) => (
          <div>
            <Input {...getInputProps({ placeholder: 'Locatie' })} />
            <div>
              {loading ? <div>...Aan het laden</div> : null}
              {suggestions?.map((suggestion) => {
                const style = {
                  backgroundColor: suggestion.active ? '#f1f1f1' : '#fff',
                }
                return (
                  <SuggestionsContainer
                    key={suggestion.id}
                    {...getSuggestionItemProps(suggestion, { style })}
                  >
                    {suggestion.description}
                  </SuggestionsContainer>
                )
              })}
            </div>
          </div>
        )}
      </PlacesAutocomplete>
    </div>
  )
}

export default Autocomplete

const SuggestionsContainer = styled.div`
  border: 1px solid #dddddd;
  margin-top: 2px;
  width: 94%;
  height: 36px;
  cursor: pointer;
  display: flex;
  align-items: center;
  padding: 5px 10px;
  font-weight: 500;
  font-size: 15px;
  border-radius: 5px;
`

const Input = styled.input`
  margin: 10px 0;
  height: 40px;
  box-sizing: border-box;
  padding-left: 10px;
  border-radius: 5px;
  border: 1px solid var(--border);
  width: 94%;
  outline: none;
  position: absolute;
  top: 10px;
`
