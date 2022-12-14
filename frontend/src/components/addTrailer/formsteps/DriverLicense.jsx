import { IconButton } from '@mui/material'
import { TbId, TbInfoCircle } from 'react-icons/tb'
import './DriverLicense.css'

export default function DriverLicense({ license, setLicense }) {
  return (
    <div
      className="formStepContainer"
      style={{ width: '40%', justifyContent: 'center', marginTop: 190 }}
    >
      <h2>Rijbewijs verplichting</h2>
      <p>
        Voor bepaalde type aanhangers is een BE of B+ rijbewijs verplicht. Is
        dit niet het geval? Klik dan op geen rijbewijs verplichting
      </p>
      <div className="licenseGrid">
        <div
          className={
            license === 'none' ? 'licenseGridItemActive' : 'licenseGridItem'
          }
          onClick={() => setLicense('none')}
        >
          <TbId className="licenseIcon" />
          <div className="licenseGridItemText">
            <b>Geen rijbewijs verplichting</b>
            <p>
              Iedereen met een standaard B rijbewijs mag deze aanhanger trekken.
            </p>
          </div>
          <IconButton className="infoIcon">
            <TbInfoCircle />
          </IconButton>
        </div>
        <div
          className={
            license === 'B+' ? 'licenseGridItemActive' : 'licenseGridItem'
          }
          onClick={() => setLicense('B+')}
        >
          <TbId className="licenseIcon" />
          <div className="licenseGridItemText">
            <b>Rijbewijs B+ verplicht</b>
            <p>
              Voor dit type aanhanger is een rijbewijs B+ verplicht. Er mag een
              maximum gewicht van 4250 kg getrokken worden.
            </p>
          </div>
          <IconButton className="infoIcon">
            <TbInfoCircle />
          </IconButton>
        </div>
        <div
          className={
            license === 'BE' ? 'licenseGridItemActive' : 'licenseGridItem'
          }
          onClick={() => setLicense('BE')}
        >
          <TbId className="licenseIcon" />
          <div className="licenseGridItemText">
            <b>Rijbewijs BE verplicht</b>
            <p>
              Voor dit type aanhanger is een BE rijbewijs verplicht. Er mag een
              maximum van 3500 kg getrokken worden.
            </p>
          </div>
          <IconButton className="infoIcon">
            <TbInfoCircle />
          </IconButton>
        </div>
      </div>
    </div>
  )
}
