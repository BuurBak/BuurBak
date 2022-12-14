import './Description.css'
import { TextareaAutosize } from '@mui/material'

export default function General({ description, setDescription }) {
  return (
    <div className="formStepContainer">
      <h2>Beschrijving</h2>
      <p>
        Geef een korte beschrijving van jouw aanhanger zodat potentiele huurders
        weten wat ze kunnen verwachten.
      </p>
      <TextareaAutosize
        className="textArea"
        placeholder="Beschrijving van jouw aanhanger..."
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
      <p className="textAreaCount">{description.length} / 180</p>
    </div>
  )
}
