import { useState } from 'react'
import { BsFilterRight } from 'react-icons/bs'
import { IoIosSearch } from 'react-icons/io'
import './OfferHeader.css'

export default function OfferHeader({ setShowFilters, trailers, setFilteredTrailers, filteredTrailers, setSortType }) {
  const [searchText, setSearchText] = useState("");

  const handleChange = value => {
    setSearchText(value);
    filterData(value);
  };

  const excludeColumns = ["id", "lat", "lng", "img"];

  const filterData = (value) => {
    const lowercasedValue = value.toLowerCase().trim();
    if (lowercasedValue === "") setFilteredTrailers(trailers);
    else {
      const filteredData = trailers.filter(item => {
        return Object.keys(item).some(key =>
          excludeColumns.includes(key) ? false : item[key].toString().toLowerCase().includes(lowercasedValue)
        );
      });
      setFilteredTrailers(filteredData);
    }
  }

  return (
    <div className="offerHeaderContainer">
      <div className="offerHeaderInput">
        <input placeholder="Zoeken op type, locatie, prijs..." value={searchText} onChange={e => handleChange(e.target.value)} />
        <IoIosSearch className="searchIcon" />
        <div className="offerHeaderIcon" onClick={() => setShowFilters(true)}>
          <BsFilterRight />
        </div>
      </div>
      <div className="offerHeaderTitleContainer">
        <div className="offerHeaderTitle">
          <p>{filteredTrailers.length} aanhangers gevonden</p>
          <p>Bekijk het volledige aanhanger aanbod</p>
        </div>
        <div className="offerHeaderOrderBy">
          <select onChange={(e) => setSortType(e.target.value)}>
            <option value="standard">Sorteren</option>
            <option value="newFirst">Datum (nieuw-oud)</option>
            <option value="oldFirst">Datum (oud-nieuw)</option>
            <option value="priceLowToHigh">Prijs (laag-hoog)</option>
            <option value="priceHighToLow">Prijs (Hoog-laag)</option>
          </select>
        </div>
      </div>
    </div>
  )
} 