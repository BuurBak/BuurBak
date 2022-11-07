import './OfferResults.css'
import TrailerCard from '../trailerCard/TrailerCard'
import NoResults from '../../trailers/trailerCard/NoResults.jsx'

export default function OfferRestults({ filteredTrailers }) {

    return (
        <div className="offerResultsContainer">
            {filteredTrailers.length > 0
                ? <>
                    {
                        filteredTrailers.map((trailer) => (
                            <TrailerCard key={trailer.id} trailer={trailer} />
                        ))
                    }
                </>
                : <NoResults />
            }
        </div>
    )
}