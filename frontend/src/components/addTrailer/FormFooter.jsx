import './FormFooter.css'

export default function FormFooter({ formstep, setFormstep }) {
  return (
    <div className="formFooter">
      {formstep === 0 ? null : (
        <button
          className="secondaryBtn"
          onClick={() => setFormstep(formstep - 1)}
        >
          Vorige stap
        </button>
      )}
      {formstep === 7 ? (
        <button
          className="primaryBtn"
          onClick={() => setFormstep((formstep) => formstep + 1)}
        >
          Aanhanger plaatsen
        </button>
      ) : (
        <button
          className="primaryBtn"
          onClick={() => setFormstep((formstep) => formstep + 1)}
        >
          Volgende stap
        </button>
      )}
    </div>
  )
}
