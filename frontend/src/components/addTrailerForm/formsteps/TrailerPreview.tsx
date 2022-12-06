import { Avatar, IconButton } from '@mui/material'
import { TbX } from 'react-icons/tb'
import './TrailerPreview.css'

export default function TrailerPreview({ setShowPreview }) {
  return (
    <div className="">
      <div className="backgroundBlur" onClick={() => setShowPreview(false)} />
      <div className="previewContainer">
        <div className="previewHeader">
          <p>Advertentie voorbeeld</p>
          <IconButton>
            <TbX />
          </IconButton>
        </div>
        <div className="previewContent">
          <div className="previewImg" />
          <div className="previewTrailerInfo">
            <p>Bakwagen ongeremd</p>
            <div className="previewTrailerOwner">
              <div>
                <p>Aangeboden door John</p>
                <p>
                  Koningin Wilhelminastraat 28, Amersfoort, Utrecht, Nederland
                </p>
              </div>
              <Avatar className="avatar" />
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
