import { BsCheckCircle, BsClock } from 'react-icons/bs'
import { IoIosStar } from 'react-icons/io'
import './Owner.css'

export default function Owner() {
    return (
        <div className="actionContainer">
            <div className="ownerContainerHeader">
                <div className="avatar"></div>
                <div className="ownerContainerHeaderInfo">
                    <p>Verhuurder: Andries Regenhout</p>
                    <div>
                        <IoIosStar />
                        <IoIosStar />
                        <IoIosStar />
                        <IoIosStar />
                        <IoIosStar />
                    </div>
                </div>
            </div>
            <div className="ownerContainerStats">
                <div>
                    <BsClock />
                    <p>Gem. 2 uur response tijd</p>
                </div>
                <div>
                    <BsCheckCircle />
                    <p>73% acceptatie graad</p>
                </div>
            </div>
        </div>
    )
}