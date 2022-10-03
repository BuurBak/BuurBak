import { useEffect, useState } from 'react'
import './OfferResults.css'
import Data from '../../../data/dummy/trailers.json'
import TrailerCard from '../trailerCard/TrailerCard'

export default function OfferRestults() {
    const [trailers, setTrailers] = useState([])

    useEffect(() => {
        setTrailers(Data)
    }, [])

    return (
        <div className="offerResultsContainer">
            {trailers.map((trailer) => (
                <TrailerCard key={trailer.id} trailer={trailer} />
            ))}
        </div>
    )
}