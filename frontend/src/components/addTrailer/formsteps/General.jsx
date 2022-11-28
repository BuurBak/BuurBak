import './General.css'
import { TextareaAutosize } from '@mui/material'

export default function General({ description, setDescription }) {
  return (
    <div className="formStepContainer" style={{ width: '38%', marginTop: 220 }}>
      <h2>Beschrijving</h2>
      <p>
        Geef een korte omschrijving van jouw aanhanger zodat potentiele huurders
        weten wat ze kunnen verwachten.
      </p>
      <TextareaAutosize
        className="textArea"
        placeholder="Beschrijving van jouw aanhanger..."
        value={description}
        onChange={(e) => setDescription(e.target.value)}
      />
    </div>
  )
}
