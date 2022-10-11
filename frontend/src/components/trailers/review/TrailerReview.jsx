import { IoIosStar } from 'react-icons/io'
import './TrailerReview.css'

export default function TrailerReview() {
    return (
        <div className="reviewContainer" style={{ marginTop: 30, marginRight: 30 }}>
            <div className="ownerContainerHeader">
                <div className="avatar"></div>
                <div className="ownerContainerHeaderInfo">
                    <p style={{ color: '#3B8B87' }}>Andries Regenhout</p>
                    <div>
                        <IoIosStar />
                        <IoIosStar />
                        <IoIosStar />
                        <IoIosStar />
                        <IoIosStar />
                    </div>
                </div>
            </div>
            <p>Korte omschrijvingen van mensen die de
                aanhangwagen al geleend hebben, wat ze er
                van vonden en hoe de service gegaan is vanuit
                de eigenaar…</p>
        </div>
    )
}