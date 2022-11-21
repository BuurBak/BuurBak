import { FaPlus, FaTrailer } from 'react-icons/fa'
import './TrailerType.css'
import { TbInfoCircle, TbShoppingCart } from 'react-icons/tb'
import TypeOfTrailers from '../../../data/dummy/trailerTypes.json'
import { useEffect, useState } from 'react'
import { IconButton } from '@mui/material'

export default function TrailerType({ trailerType, setTrailerType }) {
    const [types, setTypes] = useState([])
    const [trailerInfo, setTrailerInfo] = useState(false)

    useEffect(() => {
        setTypes(TypeOfTrailers)
    }, [TypeOfTrailers])

    return (
        <div className="formStepContainer">
            <h2>Type aanhanger</h2>
            <p>Selecteer het type aanhanger dat je wilt verhuren, staat jouw aanhanger hier niet tussen? Klik dan op anders en voeg je aanhanger handmatig toe.</p>
            <div className="trailerTypeGrid">
                {types.map((type) => (
                    <div className={trailerType?.id === type.id ? "trailerTypeIconActive" : "trailerTypeIcon"} key={type.id} onClick={() => setTrailerType(type)}>
                        <FaTrailer size="28px" />
                        <p>{type.trailerType}</p>
                        <IconButton className="infoIcon" onClick={() => setTrailerInfo(true)}>
                            <TbInfoCircle />
                        </IconButton>
                    </div>
                ))}
                <div className="trailerTypeIconLast">
                    <FaPlus size="26px" color="var(--white)" />
                    <p>Anders namelijk</p>
                </div>
                {trailerType && trailerInfo
                    ? <div className="trailerInfoContainer">
                        <div className="backgroundBlur" onClick={() => setTrailerInfo(false)} />
                        <div className="trailerTypeInfoContainer">
                            <div className="trailerTypeInfoImage" style={{ backgroundImage: `url(${trailerType.image})` }} />
                            <p>{trailerType.trailerType}</p>
                            <p>{trailerType.description}</p>
                            <button className="closeBtn" onClick={() => setTrailerInfo(false)}>Sluiten</button>
                        </div>
                    </div>
                    : null
                }
            </div>
        </div>
    )
}