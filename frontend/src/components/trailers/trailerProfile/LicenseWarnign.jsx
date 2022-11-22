import './LicenseWarning.css'
import { IoWarningOutline } from 'react-icons/io5'

export default function LicenseWarning() {
  return (
    <div className="warningContainer">
      <IoWarningOutline size="30px" color="#ED6C6C" />
      <div>
        <p>Voor dit type aanhanger is een BE rijbewijs verplicht</p>
        <p>Lorem ipsum dolor set amit, lorem ipsum dolor set amit</p>
      </div>
    </div>
  )
}
