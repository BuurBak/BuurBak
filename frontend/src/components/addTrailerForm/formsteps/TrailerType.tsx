import './TrailerType.css'
import { TbAddressBook, TbInfoCircle, TbPlus, TbX } from 'react-icons/tb'
import { TrailerTypes } from '../../../data/dummy/TrailerTypes.jsx'
import { useEffect, useState } from 'react'
import { IconButton } from '@mui/material'

export default function TrailerType({ trailerType, setTrailerType }) {
  const [types, setTypes] = useState([])
  const [trailerInfo, setTrailerInfo] = useState(false)
  const [customTrailer, setCustomTrailer] = useState(false)
  const [customTrailerTitle, setCustomTrailerTitle] = useState('')
  const extraTrailerInfo = types.filter(
    (trailer) => trailer.trailerType === trailerType
  )

  useEffect(() => {
    setTypes(TrailerTypes)
  }, [])

  return (
    <div className="formStepContainer">
      <h2>Selecteer jouw aanhanger type</h2>
      <p>
        Selecteer het type aanhanger dat je wilt verhuren, staat jouw aanhanger
        hier niet tussen? Klik dan op anders en voeg je aanhanger handmatig toe.
      </p>
      <div className="trailerTypeGrid">
        {types.map((type) => (
          <div
            className={
              trailerType === type.trailerType
                ? 'trailerTypeIconActive'
                : 'trailerTypeIcon'
            }
            key={type.id}
            onClick={() => {
              setTrailerType(type.trailerType)
              setCustomTrailerTitle('')
            }}
          >
            {type.icon}
            <p>{type.trailerType}</p>
            <IconButton
              className="infoIcon"
              onClick={() => setTrailerInfo(true)}
            >
              <TbInfoCircle />
            </IconButton>
          </div>
        ))}
        <div
          className={
            customTrailerTitle ? 'customTrailerActive' : 'trailerTypeIconLast'
          }
          onClick={() => setCustomTrailer(true)}
        >
          {customTrailerTitle.length > 0 ? (
            <TbAddressBook size="24px" color="var(--white)" />
          ) : (
            <TbPlus size="24px" color="var(--primary)" />
          )}
          {customTrailerTitle ? (
            <p>{customTrailerTitle}</p>
          ) : (
            <p>Anders namelijk</p>
          )}
        </div>

        {/* Add custom trailer type */}
        {customTrailer && (
          <div>
            <div
              className="backgroundBlur"
              onClick={() => setCustomTrailer(false)}
            />
            <div className="addCustomTrailerContainer">
              <img
                className="addTrailerImg"
                alt="illustration"
                src={'/customTrailer.svg'}
              />
              <div className="addTrailerContent">
                <IconButton
                  className="closePopup"
                  onClick={() => setCustomTrailer(false)}
                >
                  <TbX />
                </IconButton>
                <p>Ander aanhanger type</p>
                <p>
                  Staat jouw aanhanger type er niet tussen? Geef de titel van
                  jouw aanhanger op.
                </p>
                <input
                  className="customTrailerInput"
                  placeholder="Aanhanger type"
                  onChange={(e) => setCustomTrailerTitle(e.target.value)}
                  value={customTrailerTitle}
                  maxLength={22}
                />
                <button
                  className="saveCustomTrailer"
                  onClick={() => {
                    setTrailerType(customTrailerTitle)
                    setCustomTrailer(false)
                  }}
                  type="button"
                >
                  Opslaan
                </button>
              </div>
            </div>
          </div>
        )}

        {/* More trailer info + img */}
        {trailerType && trailerInfo && (
          <div className="trailerInfoContainer">
            <div
              className="backgroundBlur"
              onClick={() => setTrailerInfo(false)}
            />
            <div className="trailerTypeInfoContainer">
              <div
                className="trailerTypeInfoImage"
                style={{ backgroundImage: `url(${extraTrailerInfo[0].image})` }}
              />
              <p>{extraTrailerInfo[0].trailerType}</p>
              <p>{extraTrailerInfo[0].description}</p>
              <button
                className="closeBtn"
                onClick={() => setTrailerInfo(false)}
              >
                Sluiten
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}
