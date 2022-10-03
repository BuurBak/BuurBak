import LicenseWarning from '../trailerProfile/LicenseWarnign'
import './ReservationForm.css'

export default function ReservationForm() {
    return (
        <div>
            <div className="reservationContainer">
                <form className="reservationForm">
                    <p>Reserveer bakwagen ongeremd</p>
                    <div className="divider" style={{ backgroundColor: '#66CC99', marginTop: 15, width: '50%' }}></div>
                    <div className="form">
                        <LicenseWarning />
                        <p>Controleer je gegevens om je reservering van “Bakwagen ongeremd” compleet te
                            maken. Je kan een bericht achterlaten voor de verhuurder met eventuele vragen of
                            opmerkingen, dit is niet verplicht.</p>
                    </div>
                </form>
            </div>
        </div>
    )
}