import './HomeOffer.css'
import TrailerCard from '../trailers/trailerCard/TrailerCard.jsx'
import useAxios from '../../data/useAxios'

export default function HomeOffer() {
  const { response, loading, error } = useAxios({
    method: 'get',
    url: '/traileroffers',
  })

  if (loading) return <p>Loading...</p>
  if (error) return <p>{error}</p>

  return (
    <div className="homeOfferContainer">
      <h2>Bekijk het aanbod</h2>
      <p>En wordt duurzaam door te delen</p>
      <div className="homeOfferGrid">
        {response.content.map((trailer) => (
          <TrailerCard trailer={trailer} key={trailer.id} />
        ))}
      </div>
    </div>
  )
}
