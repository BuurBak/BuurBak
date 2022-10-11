import './Reservation.css'
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';
import { useState } from 'react';
import dayjs from 'dayjs';
import TextField from '@mui/material/TextField';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { Link } from 'react-router-dom';

export default function Reservation({ id }) {
    const [value, setValue] = useState(dayjs('2022-09-29T21:11:54'));
    const current = new Date()

    const handleChange = (newValue) => {
        setValue(newValue);
    };
    return (
        <div className="actionContainer" style={{ marginTop: 20 }}>
            <p>€32,- per dag</p>
            <span>Van</span>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DesktopDatePicker
                    className="datePicker"
                    label="Van"
                    inputFormat="DD/MM/YYYY"
                    value={value}
                    onChange={handleChange}
                    renderInput={(params) => <TextField {...params} />}
                />
            </LocalizationProvider>
            <span>Tot</span>
            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DesktopDatePicker
                    className="datePicker"
                    label="Tot"
                    inputFormat="DD/MM/YYYY"
                    value={value}
                    onChange={handleChange}
                    renderInput={(params) => <TextField {...params} />}
                />
            </LocalizationProvider>
            <span>Dagdeel van - tot</span>
            <div className="inputFlexBox">
                <div className="actionInput" style={{ width: '45%' }}></div>
                <p>-</p>
                <div className="actionInput" style={{ width: '45%' }}></div>
            </div>
            <div className="divider"></div>
            <div className="actionContainerTotal">
                <p>Totaal:</p>
                <b>€35,-</b>
            </div>
            <Link to={`/reserveren/${id}`}><button className="actionCta">Reserveren</button></Link>
        </div>
    )
}