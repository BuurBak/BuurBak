import { IconButton } from '@mui/material'
import { useState } from 'react'
import { TbCalendarEvent, TbChevronRight, TbPig, TbUsers } from 'react-icons/tb'
import TrailerPreview from '../TrailerPreview'
import './Complete.css'

export default function Complete() {
  const [showPreview, setShowPreview] = useState<boolean>(false)

  return (
    <div className="formInformationContainer">
      <div className="formInformationContent">
        <div className="introductionTitle">
          <p>Laatste stap</p>
          <h2>Controleer je advertentie</h2>
          <p>
            Controleer of je advertentie volledig kloppend is ingevuld. Als
            alles klopt klik je op advertentie plaatsen en ben je klaar om te
            verhuren!
          </p>
          <div
            className="previewTrailerProfile"
            onClick={() => setShowPreview(true)}
          >
            <img className="previewTrailerImg" alt="trailer" />
            <div>
              <p>Paarden trailer</p>
              <p>€35.00 per dag • Harderwijk</p>
            </div>
            <IconButton className="previewIcon">
              <TbChevronRight />
            </IconButton>
          </div>
          <button className="completeTrailerForm" type="submit">
            Advertentie voltooien
          </button>
        </div>
        <div className="nextStepContainer">
          <p>Wat moet nog gebeuren</p>
          <div className="nextStepItem">
            <TbPig className="nextIcon" />
            <div>
              <p>Voeg je bankrekening nummer toe</p>
              <p>
                Voordat je advertentie online terug te vinden is moet je nog een
                bankrekening nummer toevoegen aan je account om te kunnen
                verhuren.
              </p>
              <button className="addCredit" type="button">
                Bankrekening toevoegen
              </button>
            </div>
          </div>
          <div className="nextStepItem">
            <TbCalendarEvent className="nextIcon" />
            <div>
              <p>Gegevens aanpassen</p>
              <p>
                Nadat je de advertentie voltooid hebt kan je je aanhanger terug
                vinden onder mijn account. Hier kan je de advertentie aanpassen
                en de beschikbaarheid van de aanhanger veranderen.
              </p>
            </div>
          </div>
          <div className="nextStepItem">
            <TbUsers className="nextIcon" />
            <div>
              <p>Huurders controleren</p>
              <p>
                Wanneer een huurder jouw aanhanger reserveert heb je nog de
                optie deze te annuleren op basis van reviews van verhuurders.
              </p>
            </div>
          </div>
        </div>
      </div>
      {showPreview && <TrailerPreview setShowPreview={setShowPreview} />}
    </div>
  )
}
