import { GoogleMap, MarkerF } from "@react-google-maps/api";
import mapStyles from '../../data/mapStyles.js';
import Marker from '../../data/images/marker.svg'
import { useEffect, useState } from "react";
import Data from '../../data/dummy/trailers.json'
import '../../components/trailers/offer/Map.css'
import { IoIosArrowBack, IoIosArrowForward, IoIosMenu } from "react-icons/io";
import OfferHeader from '../../components/trailers/offer/OfferHeader'
import OfferRestults from "../../components/trailers/offer/OfferResults.jsx";
import TrailerCard from "../../components/trailers/trailerCard/TrailerCard.jsx";

export default function Offer() {
    const [trailers, setTrailers] = useState([])
    const [activeTrailer, setActiveTrailer] = useState()
    const [scaleMap, setScaleMap] = useState(false)
    const mapContainerStyle = { width: '100%', height: '100%', borderRadius: 10 }
    const center = ({ lat: 52.090736, lng: 5.121420 })
    const options = ({ styles: mapStyles, disableDefaultUI: true, clickableIcons: false })

    useEffect(() => {
        setTrailers(Data)
    }, [])

    return (
        <div style={{ display: 'flex' }}>
            <div style={{ width: '43%' }}>
                <OfferHeader />
                <OfferRestults />
            </div>
            <div className="map" style={scaleMap ? { width: '100%' } : null}>
                <GoogleMap zoom={13} center={center} options={options} mapContainerStyle={mapContainerStyle}>
                    <button className="zoomBtn" onClick={() => setScaleMap(current => !current)}>
                        {scaleMap
                            ? <IoIosArrowForward size="22px" color="white" />
                            : <IoIosArrowBack size="22px" color="white" />
                        }
                    </button>
                    {scaleMap
                        ? <button className="filterButton"><IoIosMenu size="26px" color="white" /></button>
                        : null
                    }
                    {trailers.map((trailer) => (
                        <MarkerF
                            key={trailer?.id}
                            onClick={() => setActiveTrailer(trailer)}
                            position={{ lat: trailer?.lat, lng: trailer?.lng }}
                            icon={{
                                url: Marker,
                                scaledSize: new window.google.maps.Size(50, 50)
                            }}
                        />
                    ))}
                    {activeTrailer ?
                        <div className="activeTrailerCard">
                            <TrailerCard trailer={activeTrailer} />
                        </div>
                        : null
                    }
                </GoogleMap>
            </div>
        </div>
    )
}