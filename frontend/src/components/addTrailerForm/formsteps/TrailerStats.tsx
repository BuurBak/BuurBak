import {
  TbArrowAutofitContent,
  TbArrowAutofitHeight,
  TbArrowAutofitUp,
  TbBox,
  TbScale,
} from 'react-icons/tb'
import './TrailerStats.css'

export default function TrailerStats({ trailerStats, setTrailerStats }) {
  const updateStats = (e: any) => {
    const fieldName = e.target.name
    setTrailerStats((stats: any) => ({
      ...stats,
      [fieldName]: e.target.value,
    }))
  }

  return (
    <div className="formStepContainer">
      <h2 className="maxWidthHeader">
        Geef wat extra informatie over de aanhanger
      </h2>
      <p></p>
      <div className="statsInputItem">
        <TbArrowAutofitContent size="18px" />
        <p>Lengte in cm</p>
        <input
          value={trailerStats.length}
          onChange={updateStats}
          id="length"
          name="length"
          type="number"
          className="statInput"
          placeholder="Lengte"
        />
      </div>
      <div className="statsInputItem">
        <TbArrowAutofitHeight size="18px" />
        <p>Breedte in cm</p>
        <input
          value={trailerStats.width}
          onChange={updateStats}
          id="width"
          name="width"
          type="number"
          className="statInput"
          placeholder="Breedte"
        />
      </div>
      <div className="statsInputItem">
        <TbArrowAutofitUp size="18px" />
        <p>Hoogte in cm</p>
        <input
          value={trailerStats.height}
          onChange={updateStats}
          id="height"
          name="height"
          type="number"
          className="statInput"
          placeholder="Hoogte"
        />
      </div>
      <div className="statsInputItem">
        <TbBox size="18px" />
        <p>Gewicht in kg</p>
        <input
          value={trailerStats.weight}
          onChange={updateStats}
          id="weight"
          name="weight"
          type="number"
          className="statInput"
          placeholder="Gewicht"
        />
      </div>
      <div className="statsInputItem">
        <TbScale size="18px" />
        <p>Draagcapaciteit in kg</p>
        <input
          value={trailerStats.capacity}
          onChange={updateStats}
          id="capacity"
          name="capacity"
          type="number"
          className="statInput"
          placeholder="Draagcapaciteit"
        />
      </div>
    </div>
  )
}
