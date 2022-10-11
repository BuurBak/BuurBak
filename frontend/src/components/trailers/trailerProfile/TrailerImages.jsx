import './TrailerImages.css'

export default function TrailerImages({ trailerDetails}) {
    return (
        <div className="trailerImagesContainer" onClick={() => console.log(trailerDetails)}>
            <div className="imagesGrid">
                <div className="mainImage" style={{ backgroundImage: `url(${trailerDetails?.[0]?.img})`}}></div>
                <div className="imageGridContainer">
                    <div className="firstImage" style={{ backgroundImage: `url(${trailerDetails?.[0]?.img})`}}></div>
                    <div className="secondImage" style={{ backgroundImage: `url(${trailerDetails?.[0]?.img})`}}></div>
                </div>
                <div className="imageGridContainer">
                    <div className="thirdImage" style={{ backgroundImage: `url(${trailerDetails?.[0]?.img})`}}></div>
                    <div className="fourthImage" style={{ backgroundImage: `url(${trailerDetails?.[0]?.img})`}}></div>
                </div>
            </div>
        </div>
    )
}