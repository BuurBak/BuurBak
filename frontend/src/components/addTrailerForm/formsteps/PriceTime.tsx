import './PriceTime.css'
import { TbCircleMinus, TbCirclePlus } from 'react-icons/tb'
import { useState } from 'react'
import { LocalizationProvider, TimePicker } from '@mui/x-date-pickers'
import dayjs, { Dayjs } from 'dayjs'
import { TextField } from '@mui/material'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import { GoogleMap } from '@react-google-maps/api'
import mapStyles from '../../../data/mapStyles'

const days = [
  { id: 0, day: 'Maandag' },
  { id: 1, day: 'Dinsdag' },
  { id: 2, day: 'Woensdag' },
  { id: 3, day: 'Donderdag' },
  { id: 4, day: 'Vrijdag' },
  { id: 5, day: 'Zaterdag' },
  { id: 6, day: 'Zondag' },
  { id: 7, day: 'Feestdagen' },
]

const mapContainerStyle = { width: '100%', height: '100%', borderRadius: 10 }

const options = {
  styles: mapStyles,
  disableDefaultUI: true,
  clickableIcons: false,
}

export default function PriceTime({ geoCode, price, setPrice, averagePrice }) {
  const [availableDays, setAvailableDays] = useState([])
  const [pickupStart, setPickupStart] = useState<Dayjs | null>(
    dayjs('2014-08-18T21:11:54')
  )
  const [pickupEnd, setPickupEnd] = useState<Dayjs | null>(
    dayjs('2014-08-18T21:11:54')
  )
  const [returnStart, setReturnStart] = useState<Dayjs | null>(
    dayjs('2014-08-18T21:11:54')
  )
  const [returnEnd, setReturnEnd] = useState<Dayjs | null>(
    dayjs('2014-08-18T21:11:54')
  )

  const center = geoCode
    ? { lat: geoCode.lat, lng: geoCode.lng }
    : { lat: 52.090736, lng: 5.12142 }

  function addDay(day: any) {
    if (availableDays.includes(day)) {
      return
    } else {
      setAvailableDays((days) => [...days, day])
    }
  }

  function removeDay(day: any) {
    const newArray = availableDays?.filter((item) => item.id !== day.id)
    setAvailableDays(newArray)
  }

  return (
    <div className="formStepContainer">
      <h2>Prijs en beschikbaarheid</h2>
      <p>
        Geef de gewenste prijs en beschikbaarheid van je aanhanger op. Dit kan
        later nog worden aangepast via je advertentie.
      </p>
      <div className="priceDetailsContainer">
        <div className="priceSelectContainer">
          <b>Gewenste prijs</b>
          <p>De prijs wordt verminderd met 20% service fee.</p>
          <p>€ {price}.00</p>
          <p>Gem. prijs in omgeving: €{averagePrice}.00</p>
          <div className="priceActions">
            <button
              type="button"
              className="priceAction"
              onClick={() => setPrice(price - 1)}
            >
              <TbCircleMinus />
            </button>
            <button
              type="button"
              className="priceAction"
              onClick={() => setPrice(price + 1)}
            >
              <TbCirclePlus />
            </button>
          </div>
        </div>
        <div className="priceCompareContainer">
          <GoogleMap
            zoom={11}
            center={center}
            options={options}
            mapContainerStyle={mapContainerStyle}
          />
        </div>
      </div>
      <b>Verhuren kan op</b>
      <div className="trailerFormPickup">
        {days.map((day) => (
          <>
            {availableDays.filter((item) => item.id === day.id).length > 0 ? (
              <button
                key={day.id}
                className="dateSelectItemActive"
                type="button"
                onClick={() => removeDay(day)}
              >
                <p>{day.day}</p>
              </button>
            ) : (
              <button
                key={day.id}
                className="dateSelectItem"
                type="button"
                onClick={() => addDay(day)}
              >
                <p>{day.day}</p>
              </button>
            )}
          </>
        ))}
      </div>
      <b>Verhuur momenten</b>
      <div className="trailerFormTimePicker">
        <div className="timeSelectItem">
          <p>Ophalen mogelijk tussen</p>
          <div className="timeSelectItemContent">
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <TimePicker
                label="Van"
                className="startTime"
                value={pickupStart}
                onChange={(newValue: Dayjs | null) => {
                  setPickupStart(newValue)
                }}
                renderInput={(params) => <TextField size="small" {...params} />}
                ampm={false}
              />
              <div className="smallDivider" />
              <TimePicker
                label="Tot"
                className="startTime"
                value={pickupEnd}
                onChange={(newValue: Dayjs | null) => {
                  setPickupEnd(newValue)
                }}
                renderInput={(params) => <TextField size="small" {...params} />}
                ampm={false}
              />
            </LocalizationProvider>
          </div>
        </div>
        <div className="timeSelectItem">
          <p>Terugbrengen mogelijk tussen</p>
          <div className="timeSelectItemContent">
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <TimePicker
                label="Van"
                className="startTime"
                value={returnStart}
                onChange={(newValue: Dayjs | null) => {
                  setReturnStart(newValue)
                }}
                renderInput={(params) => <TextField size="small" {...params} />}
                ampm={false}
              />
              <div className="smallDivider" />
              <TimePicker
                label="Tot"
                className="startTime"
                value={returnEnd}
                onChange={(newValue: Dayjs | null) => {
                  setReturnEnd(newValue)
                }}
                renderInput={(params) => <TextField size="small" {...params} />}
                ampm={false}
              />
            </LocalizationProvider>
          </div>
        </div>
      </div>
    </div>
  )
}
