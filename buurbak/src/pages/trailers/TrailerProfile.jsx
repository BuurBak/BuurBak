import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Data from '../../data/dummy/trailers.json'
import '../../components/trailers/review/TrailerReview.css'
import TrailerReview from "../../components/trailers/review/TrailerReview";
import LicenseWarning from "../../components/trailers/trailerProfile/LicenseWarnign";
import DetailHeader from '../../components/trailers/trailerProfile/DetailHeader'
import Description from '../../components/trailers/trailerProfile/Description'
import DetailMap from '../../components/trailers/trailerProfile/DetailMap'
import Accessoires from "../../components/trailers/trailerProfile/Accessoires"
import Owner from '../../components/trailers/trailerProfile/Owner'
import Reservation from '../../components/trailers/trailerProfile/Reservation'
import TrailerImages from '../../components/trailers/trailerProfile/TrailerImages'

export default function TrailerProfile() {
    const { id } = useParams()
    const [trailers, setTrailers] = useState([])
    const trailerDetails = trailers.filter((trailer) => {
        return trailer.id === id
    })

    useEffect(() => {
        setTrailers(Data)
    }, [])

    return (
        <div style={{ paddingBottom: 80 }}>
            <TrailerImages trailerDetails={trailerDetails} />
            <div style={{ display: 'flex', width: '66%', marginLeft: 'auto', marginRight: 'auto', justifyContent: 'space-between' }}>
                <div style={{ width: '60%' }}>
                    <DetailHeader trailerDetails={trailerDetails} />
                    <LicenseWarning trailerDetails={trailerDetails} />
                    <Description />
                    <Accessoires />
                    <DetailMap trailerDetails={trailerDetails} />
                </div>
                <div style={{ width: '33.5%', marginTop: 74 }}>
                    <Owner />
                    <Reservation id={id} />
                </div>
            </div>
            <div style={{ display: 'flex', flexDirection: 'column' }}>
                <b className="subTitle">4.8 Rating op basis van 50 beoordelingen</b>
                <p className="moreBtn">Lees alle reviews</p>
                <div className="trailerReviews">
                    <TrailerReview />
                    <TrailerReview />
                    <TrailerReview />
                    <TrailerReview />
                    <TrailerReview />
                    <TrailerReview />
                    <TrailerReview />
                    <TrailerReview />
                    <TrailerReview />
                    <TrailerReview />
                    <TrailerReview />
                    <TrailerReview />
                </div>
            </div>
        </div>
    )
}