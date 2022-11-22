import './FormFooter.css'

export default function FormFooter({ formstep, setFormstep, trailerType }) {

    function handleSubmit() {
        if (formstep === 0) {
            if (!trailerType || trailerType.length <=0) {
                setFormstep(0)
                return console.log('Kies een trailer type om door te gaan');
            } else {
                setFormstep(formstep + 1);
            }
        } else if (formstep === 2) {
            // if (!license || license.length <= 1) {
            //     return alert('Kies een trailer type om door te gaan');
            // } else {
            //     setFormstep(formstep + 1);
            // }
        } else if (formstep === 3) {
            // set page === 0 , and clear fields
        } else setFormstep(formstep + 1);
    }
    
    return (
        <div className="formFooter">
            {formstep === 0
                ? null
                : <button className="secondaryBtn" onClick={() => setFormstep(formstep - 1)}>Vorige stap</button>
            }
            {formstep === 7
                ? <button className="primaryBtn" onClick={() => setFormstep(formstep => formstep + 1)}>Aanhanger plaatsen</button>
                : <button className="primaryBtn" onClick={() => handleSubmit()}>Volgende stap</button>
            }
        </div>
    )
}