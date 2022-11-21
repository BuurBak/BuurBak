import { FaPlus, FaTrailer } from 'react-icons/fa'
import './TrailerType.css'
import { TbInfoCircle, TbShoppingCart } from 'react-icons/tb'
import TypeOfTrailers from '../../../data/dummy/trailerTypes.json'
import { useEffect, useState } from 'react'
import { IconButton } from '@mui/material'

export default function TrailerType({ trailerType, setTrailerType }) {
    const [types, setTypes] = useState([])
    const [trailerInfo, setTrailerInfo] = useState(false)
    const activeTrailerInfo = trailerType.id === types.filter((type) => type.id)

    useEffect(() => {
        setTypes(TypeOfTrailers)
    }, [TypeOfTrailers])

    return (
        <div className="formStepContainer">
            <h2>Type aanhanger</h2>
            <p>Selecteer het type aanhanger dat je wilt verhuren, staat jouw aanhanger hier niet tussen? Klik dan op anders en voeg je aanhanger handmatig toe.</p>
            <div className="trailerTypeGrid">
                {types.map((type) => (
                    <>
                        <div className="trailerTypeIcon" key={type.id} onClick={() => setTrailerType(type)}>
                            <FaTrailer size="28px" color="var(--text)" />
                            <p>{type.trailerType}</p>
                            <IconButton className="infoIcon" onClick={() => setTrailerInfo(true)}>
                                <TbInfoCircle />
                            </IconButton>
                        </div>
                        {trailerInfo
                            ? <div className="trailerInfoContainer">
                                <div className="backgroundBlur" onClick={() => setTrailerInfo(false)} />
                                <div className="trailerTypeInfoContainer">
                                    <div className="trailerTypeInfoImage" />
                                </div>
                            </div>
                            : null
                        }
                    </>
                ))}
                <div className="trailerTypeIconLast">
                    <FaPlus size="26px" color="var(--white)" />
                    <p>Anders namelijk</p>
                </div>
            </div>
        </div>
    )
}