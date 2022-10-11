import './TrailerOverview.css'
import { IoIosCalendar, IoIosClock, IoIosPin } from 'react-icons/io';

export default function TrailerOverview({ trailerDetails }) {
    return (
        <div className="reserveTrailer">
            <div className="reserveTrailerHeader">
                <div className="reservationTrailerImg" style={{ backgroundImage: `url(${trailerDetails?.[0].img})` }}></div>
                <div>
                    <p>{trailerDetails?.[0].title}</p>
                    <div className="reservationTrailerLocation">
                        <p>{trailerDetails?.[0].location}</p>
                        <IoIosPin style={{ marginLeft: 5 }} />
                    </div>
                </div>
            </div>
            <div className="divider" style={{ marginBottom: 20 }} />
            <div className="reservationTrailerDate">
                <IoIosCalendar size='24px' color='#747474' />
                <p>17-09-2022 - 17-09-2022</p>
            </div>
            <div className="reservationTrailerDate">
                <IoIosClock size='24px' color='#747474' />
                <p>Hele dag</p>
            </div>
            <div className="divider" style={{ marginTop: 20 }} />
            <div className="reservationFlex">
                <p>All risk verzekering:</p>
                <p>$20,00</p>
            </div>
            <div className="reservationFlex">
                <p>Servicefee:</p>
                <p>$20,00</p>
            </div>
            <div className="divider" />
            <div className="reservationTotal">
                <p>Totaal:</p>
                <p>$37,00</p>
            </div>
        </div>
    )
}