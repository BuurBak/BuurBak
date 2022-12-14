import '../../../App.css'
import { useState } from 'react'
import ProgressBar from '../ProgressBar'
import FormFooter from '../FormFooter'
import TrailerType from './TrailerType'
import DriverLicense from './DriverLicense'
import Location from './Location'
import General from './General'
import Accessoires from './Accessoires'
import PersonalInfo from './PersonalInfo'
import TrailerOverview from './TrailerOverview'
import ImageUpload from './ImageUpload'
import Introduction from '../formInformation/Introduction'

export default function TrailerForm() {
  const [formstep, setFormstep] = useState(0)
  const [trailerType, setTrailerType] = useState()
  const [license, setLicense] = useState('none')
  const [description, setDescription] = useState('')
  const [trailerImage, setTrailerImage] = useState()

  const currentUser = {
    name: 'John Appleseed',
    email: 'johnappleseed@gmail.com',
    address: 'Koningin Wilhelminalaan 12, 3913AB, Leusden, Utrecht, Nederland',
    billing: 'NL41 RABO 8123 1239 02',
    phone: '+31632394281',
  }

  return (
    <div className="addTrailerPage">
      {formstep === 0 ? null : (
        <ProgressBar
          formstep={formstep}
          setFormstep={setFormstep}
          trailerType={trailerType}
          license={license}
        />
      )}
      {formstep === 0 ? <Introduction /> : null}
      <div className="TrailerFormContainer">
        {formstep === 1 ? (
          <TrailerType
            trailerType={trailerType}
            setTrailerType={setTrailerType}
          />
        ) : null}
        {formstep === 2 ? (
          <DriverLicense license={license} setLicense={setLicense} />
        ) : null}
        {formstep === 3 ? (
          <General description={description} setDescription={setDescription} />
        ) : null}
        {formstep === 4 ? (
          <ImageUpload
            trailerImage={trailerImage}
            setTrailerImage={setTrailerImage}
            trailerType={trailerType}
          />
        ) : null}
        {formstep === 5 ? <Location /> : null}
        {formstep === 6 ? <Accessoires /> : null}
        {formstep === 7 ? <PersonalInfo currentUser={currentUser} /> : null}
        {formstep === 8 ? (
          <TrailerOverview
            trailerType={trailerType}
            description={description}
          />
        ) : null}
      </div>
      <FormFooter
        formstep={formstep}
        setFormstep={setFormstep}
        trailerType={trailerType}
        license={license}
        description={description}
      />
    </div>
  )
}
