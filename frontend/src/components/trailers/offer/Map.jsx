import './Map.css'
import mapStyles from '../../../data/mapStyles.js'
import { GoogleMap, MarkerF } from '@react-google-maps/api';
import AreaMarker from '../../../data/images/areamarker.svg'

export default function Map({ trailerDetails }) {
    const mapContainerStyle = { width: '100%', height: '100%', borderRadius: 10 }
    const center = ({ lat: trailerDetails?.[0]?.lat, lng: trailerDetails?.[0]?.lng })
    const options = ({ styles: mapStyles, disableDefaultUI: true, clickableIcons: false })

    return (
        <GoogleMap zoom={14} center={center} options={options} mapContainerStyle={mapContainerStyle} onClick={() => console.log(trailerDetails)}>
            <MarkerF
                key={trailerDetails?.[0]?.id}
                position={{ lat: trailerDetails?.[0]?.lat, lng: trailerDetails?.[0]?.lng }}
                icon={{
                    url: AreaMarker,
                    scaledSize: new window.google.maps.Size(80, 80)
                }}
            />
        </GoogleMap>
    )
}