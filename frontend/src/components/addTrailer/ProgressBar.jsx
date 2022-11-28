import './ProgressBar.css'
import Logo from '../../data/images/logo.svg'
import {
  TbArrowsDiagonal2,
  TbCaravan,
  TbCurrencyEuro,
  TbFileDescription,
  TbGridDots,
  TbId,
  TbLocation,
  TbPolaroid,
  TbUser,
} from 'react-icons/tb'

export default function ProgressBar({ formstep, setFormstep }) {
  return (
    <div className="progressBarContainer">
      <img className="logo" alt="" src={Logo} />
      <div className="progressContainer">
        <div className="progressStepDotActive" onClick={() => setFormstep(0)}>
          <TbCaravan size="22px" />
        </div>
        <div
          className={
            formstep >= 1 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 1 ? () => setFormstep(1) : null}
        >
          <TbId size="22px" />
        </div>
        <div
          className={
            formstep >= 2 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 2 ? () => setFormstep(2) : null}
        >
          <TbPolaroid size="22px" />
        </div>
        <div
          className={
            formstep >= 3 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 3 ? () => setFormstep(3) : null}
        >
          <TbFileDescription size="22px" />
        </div>
        <div
          className={
            formstep >= 4 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 4 ? () => setFormstep(4) : null}
        >
          <TbArrowsDiagonal2 size="22px" />
        </div>
        <div
          className={
            formstep >= 5 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 5 ? () => setFormstep(5) : null}
        >
          <TbLocation size="22px" />
        </div>
        <div
          className={
            formstep >= 6 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 6 ? () => setFormstep(6) : null}
        >
          <TbCurrencyEuro size="22px" />
        </div>
        <div
          className={
            formstep >= 7 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 7 ? () => setFormstep(7) : null}
        >
          <TbGridDots size="22px" />
        </div>
        <div
          className={
            formstep >= 8 ? 'progressStepDotActive' : 'progressStepDot'
          }
          onClick={formstep > 8 ? () => setFormstep(8) : null}
        >
          <TbUser size="22px" />
        </div>
      </div>
      {/* <div className="progressBarStepActive" onClick={() => setFormstep(0)}>
        <FaTrailer size="20px" color="var(--primary)" />
        <div className="progressIndicator">
          <div
            className="progressDot"
            style={formstep >= 0 ? { backgroundColor: 'var(--primary' } : null}
          />
          <div
            className="progressLine"
            style={formstep >= 1 ? { backgroundColor: 'var(--primary' } : null}
          />
        </div>
        <p
          className={
            formstep >= 0 ? 'progressBarTextActive' : 'progressBarText'
          }
        >
          Type
        </p>
      </div>
      <div className="progressBarStep" onClick={() => setFormstep(1)}>
        <FaRegIdCard
          size="20px"
          style={
            formstep >= 1
              ? { color: 'var(--primary' }
              : { color: 'var(--border' }
          }
        />
        <div className="progressIndicator">
          <div
            className="progressDot"
            style={formstep >= 1 ? { backgroundColor: 'var(--primary' } : null}
          />
          <div
            className="progressLine"
            style={formstep >= 2 ? { backgroundColor: 'var(--primary' } : null}
          />
        </div>
        <p
          className={
            formstep >= 1 ? 'progressBarTextActive' : 'progressBarText'
          }
        >
          Rijbewijs
        </p>
      </div>
      <div className="progressBarStep" onClick={() => setFormstep(2)}>
        <FaListAlt
          size="20px"
          style={
            formstep >= 2
              ? { color: 'var(--primary' }
              : { color: 'var(--border' }
          }
        />
        <div className="progressIndicator">
          <div
            className="progressDot"
            style={formstep >= 2 ? { backgroundColor: 'var(--primary' } : null}
          />
          <div
            className="progressLine"
            style={formstep >= 3 ? { backgroundColor: 'var(--primary' } : null}
          />
        </div>
        <p
          className={
            formstep >= 2 ? 'progressBarTextActive' : 'progressBarText'
          }
        >
          Algemeen
        </p>
      </div>
      <div className="progressBarStep" onClick={() => setFormstep(3)}>
        <FaLocationArrow
          size="18px"
          style={
            formstep >= 3
              ? { color: 'var(--primary' }
              : { color: 'var(--border' }
          }
        />
        <div className="progressIndicator">
          <div
            className="progressDot"
            style={formstep >= 3 ? { backgroundColor: 'var(--primary' } : null}
          />
          <div
            className="progressLine"
            style={formstep >= 4 ? { backgroundColor: 'var(--primary' } : null}
          />
        </div>
        <p
          className={
            formstep >= 3 ? 'progressBarTextActive' : 'progressBarText'
          }
        >
          Locatie
        </p>
      </div>
      <div className="progressBarStep" onClick={() => setFormstep(4)}>
        <FaCarAlt
          size="20px"
          style={
            formstep >= 4
              ? { color: 'var(--primary' }
              : { color: 'var(--border' }
          }
        />
        <div className="progressIndicator">
          <div
            className="progressDot"
            style={formstep >= 4 ? { backgroundColor: 'var(--primary' } : null}
          />
          <div
            className="progressLine"
            style={formstep >= 5 ? { backgroundColor: 'var(--primary' } : null}
          />
        </div>
        <p
          className={
            formstep >= 4 ? 'progressBarTextActive' : 'progressBarText'
          }
        >
          Accessoires
        </p>
      </div>
      <div className="progressBarStep" onClick={() => setFormstep(5)}>
        <FaUserAlt
          size="18px"
          style={
            formstep >= 5
              ? { color: 'var(--primary' }
              : { color: 'var(--border' }
          }
        />
        <div className="progressIndicator">
          <div
            className="progressDot"
            style={formstep >= 5 ? { backgroundColor: 'var(--primary' } : null}
          />
          <div
            className="progressLine"
            style={formstep >= 6 ? { backgroundColor: 'var(--primary' } : null}
          />
        </div>
        <p
          className={
            formstep >= 5 ? 'progressBarTextActive' : 'progressBarText'
          }
        >
          Gegevens
        </p>
      </div>
      <div className="progressBarStep" onClick={() => setFormstep(6)}>
        <FaCheckCircle
          size="18px"
          style={
            formstep >= 6
              ? { color: 'var(--primary' }
              : { color: 'var(--border' }
          }
        />
        <div className="progressIndicator">
          <div
            className="progressDot"
            style={formstep >= 6 ? { backgroundColor: 'var(--primary' } : null}
          />
        </div>
        <p
          className={
            formstep >= 6 ? 'progressBarTextActive' : 'progressBarText'
          }
        >
          Klaar
        </p>
      </div>
      <div className="progressBarStep" onClick={() => setFormstep(6)}>
        <FaCheckCircle
          size="18px"
          style={
            formstep >= 6
              ? { color: 'var(--primary' }
              : { color: 'var(--border' }
          }
        />
        <div className="progressIndicator">
          <div
            className="progressDot"
            style={formstep >= 6 ? { backgroundColor: 'var(--primary' } : null}
          />
        </div>
        <p
          className={
            formstep >= 6 ? 'progressBarTextActive' : 'progressBarText'
          }
        >
          Klaar
        </p>
      </div>
      <div className="progressBarStep" onClick={() => setFormstep(6)}>
        <FaCheckCircle
          size="18px"
          style={
            formstep >= 6
              ? { color: 'var(--primary' }
              : { color: 'var(--border' }
          }
        />
        <div className="progressIndicator">
          <div
            className="progressDot"
            style={formstep >= 6 ? { backgroundColor: 'var(--primary' } : null}
          />
        </div>
        <p
          className={
            formstep >= 6 ? 'progressBarTextActive' : 'progressBarText'
          }
        >
          Klaar
        </p>
      </div> */}
      <button className="formAction">Opslaan & sluiten</button>
    </div>
  )
}
