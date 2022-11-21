import './Filters.css'
import { Checkbox, IconButton } from '@mui/material'
import { IoIosCalendar, IoIosClose } from 'react-icons/io'
import { FaRegIdCard, FaTrailer } from 'react-icons/fa'

export default function Filters({ setShowFilters }) {
    return (
        <div className="pageContainer">
            <div className="backgroundBlur" onClick={() => setShowFilters(false)} />
            <div className="popUpContainer">
                <div className="popUpHeader">
                    <p>Filters</p>
                </div>
                <IconButton className="closeIcon" onClick={() => setShowFilters(false)}>
                    <IoIosClose size="30px" color="black" />
                </IconButton>
                <div className="popUpContainerFilters">
                    <div style={{ display: 'flex', width: '100%' }}>
                        <div className="dateFilterContainer">
                            <span>Algemeen</span>
                            <p>Controleer de beschikbare aanhangers op de gewenste datum, prijs en locatie</p>
                            <span>Datum van - tot</span>
                            <div className="dateFilter">
                                <input placeholder="Van"></input>
                                <div className="dateFilterIcon"><IoIosCalendar /></div>
                                <input placeholder="Tot" style={{ borderLeft: '1px solid #DDDDDD', borderRadius: '0px 5px 5px 0px' }}></input>
                                <div className="dateFilterIcon"><IoIosCalendar /></div>
                            </div>
                        </div>
                    </div>
                    <div className="divider" />
                    <span className="filterCategoryTitle">Type aanhanger</span>
                    <p className="filterCategoryText">Selecteer het gewenste aanhanger type waarnaar u op zoek bent.</p>
                    <div className="categoryGrid">
                        <div className="categoryItem">
                            <p>Bakwagen ongeremd</p>
                            <FaTrailer size="20px" color="#EE7B46" />
                        </div>
                        <div className="categoryItem">
                            <p>Bakwagen ongeremd</p>
                            <FaTrailer size="20px" color="#EE7B46" />
                        </div>
                        <div className="categoryItem">
                            <p>Bakwagen ongeremd</p>
                            <FaTrailer size="20px" color="#EE7B46" />
                        </div>
                        <div className="categoryItem">
                            <p>Bakwagen ongeremd</p>
                            <FaTrailer size="20px" color="#EE7B46" />
                        </div>
                        <div className="categoryItem">
                            <p>Bakwagen ongeremd</p>
                            <FaTrailer size="20px" color="#EE7B46" />
                        </div>
                        <div className="categoryItem">
                            <p>Bakwagen ongeremd</p>
                            <FaTrailer size="20px" color="#EE7B46" />
                        </div>
                        <p>Meer zien</p>
                    </div>
                    <span className="filterCategoryTitle">Afmetingen</span>
                    <p className="filterCategoryText">Geef de gewenste afmetingen van uw aanhanger op zoals in het diagram weergeven hieronder.</p>
                    <div className="trailerSize">
                        <div className="trailerSizeGrid">
                            <div className="trailerSizeItem">
                                <span>Lengte</span>
                                <input className="trailerSizeItemInput" placeholder="Cm" />
                            </div>
                            <div className="trailerSizeItem">
                                <span>Breedte</span>
                                <input className="trailerSizeItemInput" placeholder="Cm" />
                            </div>
                            <div className="trailerSizeItem">
                                <span>Hoogte</span>
                                <input className="trailerSizeItemInput" placeholder="M3" />
                            </div>
                        </div>
                        <div className="trailerIllustration" />
                    </div>
                    <span className="filterCategoryTitle">Rijbewijs</span>
                    <p className="filterCategoryText">
                        Voor bepaalde aanhangers is een rijbewijs B+ of een rijbewijs BE verplicht. Niet zeker wat dit inhoudt? <span>Bekijk de uitleg</span>
                    </p>
                    <div className="categoryGrid" style={{ borderBottom: 'none' }}>
                        <div className="categoryItem">
                            <p>Rijbewijs B+ verplicht</p>
                            <FaRegIdCard size="20px" color="#EE7B46" />
                        </div>
                        <div className="categoryItem">
                            <p>Rijbewijs BE verplicht</p>
                            <FaRegIdCard size="20px" color="#EE7B46" />
                        </div>
                    </div>
                </div>
                <div className="popupContainerFooter">
                    <button className="filterContentAction">
                        Toon resultaten
                    </button>
                </div>
            </div>
        </div>
    )
}