import './DetailHeader.css'
import { AiOutlineColumnWidth } from 'react-icons/ai'
import { IoIosPin } from 'react-icons/io'

export default function DetailHeader({ trailerDetails }) {
  const trailer = trailerDetails?.[0]

  return (
    <div className="detailHeaderContainer">
      <p>{trailer?.title}</p>
      <div className="detailHeaderFlexBox">
        <div className="flexBoxItem">
          <p>€{trailer?.price}, -</p>
        </div>
        <div className="flexBoxItem">
          <IoIosPin size="20px" />
          <p>{trailer?.location}</p>
        </div>
        <div className="flexBoxItem">
          <p>{trailer?.price}</p>
          <AiOutlineColumnWidth size="24px" />
        </div>
        <div className="flexBoxItem">
          <p>{trailer?.price}</p>
          <AiOutlineColumnWidth
            size="24px"
            style={{ transform: 'rotate(90deg)' }}
          />
        </div>
        <div className="flexBoxItem">
          <p>{trailer?.price}</p>
          <p>M3</p>
        </div>
      </div>
    </div>
  )
}
