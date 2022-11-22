import './NoResults.css'

export default function NoResult() {
  return (
    <div className="NoResultsContainer">
      <div className="NoResultsIllustration" />
      <p>Geen resultaten gevonden</p>
      <p>
        Er zijn helaas geen resultaten gevonden met uw huidige zoekfilter, pas
        filters aan om betere resultaten te krijgen.
      </p>
    </div>
  )
}
