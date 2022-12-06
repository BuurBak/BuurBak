import './HomeOffer.css'
import TrailerCard from '../trailers/trailerCard/TrailerCard.jsx'
import useAxios from '../../hooks/use-axios'
import { useEffect, useState } from 'react'

export default function HomeOffer() {
  const [trailers, setTrailers] = useState([])
  const { response, loading, error } = useAxios({
    method: 'get',
    url: '/traileroffers',
  })

  useEffect(() => {
    if (!response) {
      return
    } else {
      setTrailers(response.content)
    }
  }, [response])

  if (loading) return <p>Loading...</p>
  if (error) return <p>{error}</p>

  return (
    <div className="homeOfferContainer">
      <h2>Bekijk het aanbod</h2>
      <p>En wordt duurzaam door te delen</p>
      <div className="homeOfferGrid">
        {response?.content?.map((trailer) => (
          <TrailerCard trailer={trailer} key={trailer.id} />
        ))}
      </div>
    </div>
  )
}
