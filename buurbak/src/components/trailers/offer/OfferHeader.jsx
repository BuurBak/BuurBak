import { BsFilterRight } from 'react-icons/bs'
import { IoIosSearch } from 'react-icons/io'
import './OfferHeader.css'

export default function OfferHeader() {
    return (
        <div className="offerHeaderContainer">
            <div className="offerHeaderInput">
                <input placeholder="Zoeken..."></input>
                <IoIosSearch className="searchIcon" />
                <div className="offerHeaderIcon">
                    <BsFilterRight />
                </div>
            </div>
            <div className="offerHeaderTitleContainer">
                <div className="offerHeaderTitle">
                    <p>17 aanhangers gevonden</p>
                    <p>Bekijk het volledige aanhanger aanbod</p>
                </div>
                <div className="offerHeaderOrderBy">
                    <select>
                        <option>Sorteren</option>
                        <option>Nieuwste eerst</option>
                    </select>
                </div>
            </div>
        </div>
    )
}