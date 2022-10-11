import { useEffect, useState } from 'react'
import './OfferResults.css'
import Data from '../../../data/dummy/trailers.json'
import TrailerCard from '../trailerCard/TrailerCard'

export default function OfferRestults({ filteredTrailers }) {
    return (
        <div className="offerResultsContainer">
            {filteredTrailers.map((trailer) => (
                <TrailerCard key={trailer.id} trailer={trailer} />
            ))}
        </div>
    )
}