import { useEffect, useState } from 'react'
import { BsFilterRight } from 'react-icons/bs'
import { IoIosSearch } from 'react-icons/io'
import './OfferHeader.css'

export default function OfferHeader({ setShowFilters, trailers, setFilteredTrailers, filteredTrailers }) {
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
                    <select>
                        <option>Sorteren</option>
                        <option>Nieuwste eerst</option>
                    </select>
                </div>
            </div>
        </div>
    )
} 