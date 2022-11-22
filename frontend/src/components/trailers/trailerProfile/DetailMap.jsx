import Map from '../offer/Map'
import './DetailMap.css'

export default function DetailMap({ trailerDetails }) {
  return (
    <div className="detailInfoContainer">
      <div className="detailMapContainer">
        <Map trailerDetails={trailerDetails} />
      </div>
      <p className="detailMapComment">
        *Exacte locatie wordt vrijgegeven bij reservering
      </p>
    </div>
  )
}
