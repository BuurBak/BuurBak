import './HomeOffer.css'
import Data from '../../data/dummy/trailers.json'
import { useEffect, useState } from 'react'
import TrailerCard from '../trailers/trailerCard/TrailerCard.jsx'
import useAxios from '../../data/useAxios'

export default function HomeOffer() {
  const { response, loading, error } = useAxios({
    method: 'get',
    url: '/traileroffers',
  })
  const [trailers, setTrailers] = useState([])

  useEffect(() => {
    if (response !== null) {
      setTrailers(response['content'])
    }
  }, [response])

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
