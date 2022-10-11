import './TrailerCard.css'
import { IoIosPin } from 'react-icons/io'
import { FaTrailer } from 'react-icons/fa'
import { Link } from 'react-router-dom'

export default function TrailerCard({ trailer }) {
    return (
        <div className="trailerCard">
            <Link to={`/aanbod/${trailer.id}`} style={{ textDecoration: 'none' }}>
                {trailer.img ?
                    <div className="trailerCardImage" style={{ backgroundImage: `url(${trailer.img})` }}></div>
                    :
                    <div className="trailerCardImage"><FaTrailer color="white" size="70px" /></div>
                }
                <div className="trailerCardContent">
                    <div className="trailerCardContentInfo">
                        <b>{trailer.title}</b>
                        <div>
                            <p>€{trailer.price},- per dag</p>
                            <div className="trailerCardContentLocation">
                                <IoIosPin />
                                <p>{trailer.location}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </Link>

        </div>
    )
}