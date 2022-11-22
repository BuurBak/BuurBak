import { FaTrailer, FaRecycle } from 'react-icons/fa'
import './HeroInfo.css'
import { BsShieldFillPlus } from 'react-icons/bs'

export default function HeroInfo() {
  return (
    <div className="heroInfoContainer">
      <div className="heroInfoItem">
        <div className="heroInfoIcon">
          <FaTrailer />
        </div>
        <b>Altijd duurzaam door te delen</b>
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
          eiusmod tempor incididunt ut labore et dolore magna
        </p>
      </div>
      <div className="heroInfoItem">
        <div
          className="heroInfoIcon"
          style={{ borderColor: '#EE7B46', color: '#EE7B46' }}
        >
          <FaRecycle />
        </div>
        <b style={{ color: '#EE7B46' }}>Altijd duurzaam door te delen</b>
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
          eiusmod tempor incididunt ut labore et dolore magna
        </p>
      </div>
      <div className="heroInfoItem">
        <div className="heroInfoIcon">
          <BsShieldFillPlus size="28px" />
        </div>
        <b>Altijd duurzaam door te delen</b>
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
          eiusmod tempor incididunt ut labore et dolore magna
        </p>
      </div>
    </div>
  )
}
