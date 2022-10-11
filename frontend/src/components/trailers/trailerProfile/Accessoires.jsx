import { BsFillLockFill, BsGrid3X3 } from 'react-icons/bs'
import './Accessoires.css'

export default function Accessoires() {
    return (
        <div className="detailInfoContainer" style={{marginTop: 30, paddingBottom: 20}}>
            <b>Accessoires</b>
            <div className="accessoiresGrid">
                <div className="accessoireItem">
                    <BsGrid3X3 size="20px" />
                    <p>Veiligheidsnet inbegrepen</p>
                </div>
                <div className="accessoireItem">
                    <BsFillLockFill size="20px" />
                    <p>Disselsot inbegrepen</p>
                </div>
            </div>
        </div>
    )
}