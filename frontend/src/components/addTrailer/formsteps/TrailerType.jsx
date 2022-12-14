import './TrailerType.css'
import { TbInfoCircle, TbPlus } from 'react-icons/tb'
import { TrailerTypes } from '../../../data/dummy/TrailerTypes.jsx'
import { useEffect, useState } from 'react'
import { IconButton } from '@mui/material'

export default function TrailerType({ trailerType, setTrailerType }) {
  const [types, setTypes] = useState([])
  const [trailerInfo, setTrailerInfo] = useState(false)

  useEffect(() => {
    setTypes(TrailerTypes)
  }, [])

  return (
    <div className="formStepContainer">
      <h2>Type aanhanger</h2>
      <p>
        Selecteer het type aanhanger dat je wilt verhuren, staat jouw aanhanger
        hier niet tussen? Klik dan op anders en voeg je aanhanger handmatig toe.
      </p>
      <div className="trailerTypeGrid">
        {types.map((type) => (
          <div
            className={
              trailerType?.id === type.id
                ? 'trailerTypeIconActive'
                : 'trailerTypeIcon'
            }
            key={type.id}
            onClick={() => setTrailerType(type)}
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
        <div className="trailerTypeIconLast">
          <TbPlus size="24px" color="var(--primary)" />
          <p>Anders namelijk</p>
        </div>
        {trailerType && trailerInfo ? (
          <div className="trailerInfoContainer">
            <div
              className="backgroundBlur"
              onClick={() => setTrailerInfo(false)}
            />
            <div className="trailerTypeInfoContainer">
              <div
                className="trailerTypeInfoImage"
                style={{ backgroundImage: `url(${trailerType.image})` }}
              />
              <p>{trailerType.trailerType}</p>
              <p>{trailerType.description}</p>
              <button
                className="closeBtn"
                onClick={() => setTrailerInfo(false)}
              >
                Sluiten
              </button>
            </div>
          </div>
        ) : null}
      </div>
    </div>
  )
}
