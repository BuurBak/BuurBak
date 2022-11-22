import { BsImages } from 'react-icons/bs'
import './General.css'
import { TextareaAutosize } from '@mui/material'

export default function General({ description, setDescription}) {
  return (
    <div className="formStepContainer">
      <h2>Algemeen</h2>
      <p>
        Geef de algemene gegevens van de aanhanger op zoals de foto's, het
        gewicht en de draagcapaciteit. Ook kan je een korte beschrijving geven
        over de staat van de aanhanger.
      </p>
      <span>Foto's uploaden</span>
      <p>Upload foto's van elk aanzicht van je aanhanger.</p>
      <div className="flexbox">
        <div className="uploadImageContainer">
          <BsImages size="32px" color="var(--text)" />
          <p>Foto's uploaden</p>
          <p>Ten minste 4 foto's</p>
        </div>
        <div className="uploadImageContainer">
          <BsImages size="32px" color="var(--text)" />
          <p>Foto's uploaden</p>
          <p>Ten minste 4 foto's</p>
        </div>
        <div className="uploadImageContainer">
          <BsImages size="32px" color="var(--text)" />
          <p>Foto's uploaden</p>
          <p>Ten minste 4 foto's</p>
        </div>
        <div className="uploadImageContainer">
          <BsImages size="32px" color="var(--text)" />
          <p>Foto's uploaden</p>
          <p>Ten minste 4 foto's</p>
        </div>
      </div>
      <span>Afmetingen</span>
      <p>Geef de afmetingen en de draagcapaciteit van je aanhanger op.</p>
      <div className="flexbox">
        <div className="spanItem">
          <span>Lengte</span>
          <input className="secondaryInput" placeholder="Lengte"></input>
        </div>
        <div className="spanItem">
          <span>Breedte</span>
          <input className="secondaryInput" placeholder="Lengte"></input>
        </div>
        <div className="spanItem">
          <span>Inhoud</span>
          <input className="secondaryInput" placeholder="Lengte"></input>
        </div>
        <div className="spanItem">
          <span>Draagcapaciteit</span>
          <input className="secondaryInput" placeholder="Lengte"></input>
        </div>
      </div>
      <span>Beschrijving</span>
      <TextareaAutosize
        style={{ marginTop: 20 }}
        className="textArea"
        placeholder="Beschrijving..."
        value={description} 
        onChange={(e) => setDescription(e.target.value)}
      />
    </div>
  )
}
