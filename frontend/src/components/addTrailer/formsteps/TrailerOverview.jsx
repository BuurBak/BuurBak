import {
  TbArrowBarToRight,
  TbArrowBarToUp,
  TbCurrencyEuro,
  TbLocation,
  TbStars,
} from 'react-icons/tb'
import './TrailerOverview.css'

export default function TrailerOverview({ trailerType, description }) {
  return (
    <div className="formStepContainer">
      <h2>Overzicht aanhanger</h2>
      <p>
        Controleer de vacature zodat alle velden correct zijn ingevuld en pas de
        informatie aan waar nodig. Als alles correct is ingevuld vind je je
        aanhanger binnen seconden online terug.
      </p>
      <div className="overviewImageGrid"></div>
      <div className="overviewTrailerHeader">
        <div>
          <h2>{trailerType?.trailerType}</h2>
          <div>
            <TbStars />
            <p>Reviews</p>
            <span>•</span>
            <TbCurrencyEuro />
            <p>Gemiddeld geprijst</p>
            <span>•</span>
            <TbLocation />
            <p>{trailerType?.trailerType}</p>
          </div>
        </div>
        <div className="priceContainer">35,00</div>
      </div>
      <div className="overviewTrailerGrid">
        <div style={{ width: '100%' }}>
          <div className="divider" style={{ marginBottom: 15 }} />
          <div className="overviewTrailerMeasurements">
            <div>
              <TbArrowBarToRight size="20px" color="var(--subheader)" />
              <div>
                <p>Lengte</p>
                <p>2.15 meter</p>
              </div>
            </div>
            <div className="verticalDivider" />
            <div>
              <TbArrowBarToUp size="20px" color="var(--subheader)" />
              <div>
                <p>Lengte</p>
                <p>2.15 meter</p>
              </div>
            </div>
            <div className="verticalDivider" />
            <div>
              <p>M3</p>
              <div>
                <p>Lengte</p>
                <p>2.15 meter</p>
              </div>
            </div>
          </div>
          <b>Omschrijving</b>
          <p>{description}</p>
          <div className="divider" style={{ marginTop: 30 }} />
        </div>
        <div className="trailerOwnerContainer"></div>
      </div>
    </div>
  )
}
