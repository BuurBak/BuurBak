import './Filters.css'
import { IconButton } from '@mui/material'
import { IoIosCalendar, IoIosClose } from 'react-icons/io'

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
                            <span>Datum</span>
                            <div className="dateFilter">
                                <input placeholder="Van"></input>
                                <div className="dateFilterIcon"><IoIosCalendar /></div>
                                <input placeholder="Tot" style={{ borderLeft: '1px solid #DDDDDD', borderRadius: '0px 5px 5px 0px' }}></input>
                                <div className="dateFilterIcon"><IoIosCalendar /></div>
                            </div>
                        </div>
                    </div>
                    <div className="divider" />
                    <div className="divider" />
                </div>
            </div>
        </div>
    )
}