import './HomeOffer.css'
import Data from '../../data/dummy/trailers.json'
import { useEffect, useState } from 'react'
import TrailerCard from '../trailers/trailerCard/TrailerCard.jsx'

export default function HomeOffer() {
    const [trailers, setTrailers] = useState([])

    useEffect(() => {
        setTrailers(Data)
    }, [])

    return (
        <div className="homeOfferContainer">
            <h2>Bekijk het aanbod</h2>
            <p>En wordt duurzaam door te delen</p>
            <div className="homeOfferGrid">
                {trailers.map((trailer) => (
                    <TrailerCard trailer={trailer} key={trailer.id} />
                ))}
            </div>
        </div>
    )
}