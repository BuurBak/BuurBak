import '../../../App.css'
import { useState } from 'react'
import ProgressBar from '../ProgressBar'
import FormFooter from '../FormFooter'
import TrailerType from './TrailerType'
import DriverLicense from './DriverLicense'
import Location from './Location'
import General from './General';
import Accessoires from './Accessoires';
import PersonalInfo from './PersonalInfo';
import TrailerOverview from './TrailerOverview';

export default function TrailerForm() {
    const [formstep, setFormstep] = useState(0)
    const [trailerType, setTrailerType] = useState()
    const [license, setLicense] = useState("")
    const [description, setDescription] = useState("")

  const currentUser = {
    name: 'John Appleseed',
    email: 'johnappleseed@gmail.com',
    address: 'Koningin Wilhelminalaan 12, 3913AB, Leusden, Utrecht, Nederland',
    billing: 'NL41 RABO 8123 1239 02',
    phone: '+31632394281',
  }

    return(
        <div className="TrailerFormContainer">
            <ProgressBar formstep={formstep} setFormstep={setFormstep} trailerType={trailerType} license={license} />
            {formstep === 0 ? <TrailerType trailerType={trailerType} setTrailerType={setTrailerType} /> : null}
            {formstep === 1 ? <DriverLicense license={license} setLicense={setLicense} /> : null}
            {formstep === 2 ? <General description={description} setDescription={setDescription} /> : null}
            {formstep === 3 ? <Location /> : null}
            {formstep === 4 ? <Accessoires /> : null}
            {formstep === 5 ? <PersonalInfo currentUser={currentUser} /> : null}
            {formstep === 6 ? <TrailerOverview trailerType={trailerType} description={description} /> : null}
            <FormFooter formstep={formstep} setFormstep={setFormstep} trailerType={trailerType} />
        </div>
    )
}
