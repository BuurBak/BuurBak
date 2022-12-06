import Introduction from './formsteps/general/Introduction'
import TrailerFormFooter from './TrailerFormFooter'
import { useMultistepForm } from './useMultiStepForm'
import './TrailerForm.css'
import TrailerType from './formsteps/TrailerType'
import { useState } from 'react'
import DriverLicense from './formsteps/DriversLicense'
import Description from './formsteps/Description'
import TrailerStats from './formsteps/TrailerStats'
import TrailerLocation from './formsteps/TrailerLocation'
import PriceTime from './formsteps/PriceTime'
import Complete from './formsteps/general/Complete'
import TrailerFormHeader from './TrailerFormHeader'
import ImageUpload from './formsteps/ImageUpload'
import { object, string, TypeOf } from 'zod'

const locationSchema = object({
  postalCode: string()
    .min(6, 'Postcode is verplicht')
    .max(6, 'Postcode mag niet langer dan zes characters zijn'),
  houseNumber: string().min(1, 'Huisnummer is verplicht').max(100),
  streetName: string().min(1, 'Straat is verplicht').max(100),
  city: string()
    .min(1, 'Plaatsnaam is verplicht')
    .max(100, 'Plaatsnaam mag niet langer dan 100 characters zijn'),
})

type LocationInput = TypeOf<typeof locationSchema>

interface GeoCode {
  lat: number
  lng: number
}

const averagePrice = 32

export default function TrailerForm() {
  const [trailerType, setTrailerType] = useState([])
  const [license, setLicense] = useState('none')
  const [description, setDescription] = useState<string>('')
  const [trailerImage, setTrailerImage] = useState()
  const [trailerStats, setTrailerStats] = useState({
    length: 0,
    width: 0,
    height: 0,
    weight: 0,
    capacity: 0,
  })
  const [address, setAddress] = useState<LocationInput | null>({})
  const [geoCode, setGeoCode] = useState<GeoCode | null>(null)
  const [price, setPrice] = useState<number>(averagePrice)

  const { currentStepIndex, isFirstStep, back, next, isLastStep } =
    useMultistepForm([
      <Introduction />,
      <TrailerType trailerType={trailerType} setTrailerType={setTrailerType} />,
      <DriverLicense license={license} setLicense={setLicense} />,
      <ImageUpload
        trailerType={trailerType}
        trailerImage={trailerImage}
        setTrailerImage={setTrailerImage}
      />,
      <Description description={description} setDescription={setDescription} />,
      <TrailerStats
        trailerStats={trailerStats}
        setTrailerStats={setTrailerStats}
      />,
      <TrailerLocation
        geoCode={geoCode}
        setGeoCode={setGeoCode}
        address={address}
        setAddress={setAddress}
      />,
      <PriceTime
        geoCode={geoCode}
        price={price}
        setPrice={setPrice}
        averagePrice={averagePrice}
      />,
      <TrailerType trailerType={trailerType} setTrailerType={setTrailerType} />,
      <Complete />,
    ])

  return (
    <form className="trailerFormContainer">
      <TrailerFormHeader currentStepIndex={currentStepIndex} />
      {currentStepIndex === 0 && <Introduction />}
      {currentStepIndex === 1 && (
        <TrailerType
          trailerType={trailerType}
          setTrailerType={setTrailerType}
        />
      )}
      {currentStepIndex === 2 && (
        <DriverLicense license={license} setLicense={setLicense} />
      )}
      {currentStepIndex === 3 && (
        <Description
          description={description}
          setDescription={setDescription}
        />
      )}
      {currentStepIndex === 4 && (
        <ImageUpload
          trailerType={trailerType}
          trailerImage={trailerImage}
          setTrailerImage={setTrailerImage}
        />
      )}
      {currentStepIndex === 5 && (
        <TrailerStats
          trailerStats={trailerStats}
          setTrailerStats={setTrailerStats}
        />
      )}
      {currentStepIndex === 6 && (
        <TrailerLocation
          geoCode={geoCode}
          setGeoCode={setGeoCode}
          address={address}
          setAddress={setAddress}
        />
      )}
      {currentStepIndex === 7 && (
        <PriceTime
          geoCode={geoCode}
          price={price}
          setPrice={setPrice}
          averagePrice={averagePrice}
        />
      )}
      {currentStepIndex === 8 && (
        <TrailerType
          trailerType={trailerType}
          setTrailerType={setTrailerType}
        />
      )}
      {currentStepIndex === 9 && <Complete />}
      <TrailerFormFooter
        isFirstStep={isFirstStep}
        back={back}
        next={next}
        isLastStep={isLastStep}
        currentStepIndex={currentStepIndex}
        trailerType={trailerType}
        license={license}
        description={description}
        trailerStats={trailerStats}
        address={address}
      />
    </form>
  )
}
