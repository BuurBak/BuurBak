import { FaTrailer } from 'react-icons/fa'
import { IoIosArrowDown, IoIosArrowForward } from 'react-icons/io'
import './FAQ.css'
import Data from '../../data/dummy/FAQ.json'
import { useState } from 'react'
import { IconButton } from '@mui/material'

export default function FAQ() {
    const [activeAnswer, setActiveAnswer] = useState()
    const [typeOfUser, setTypeOfUser] = useState('renter')

    return (
        <div className="faqContainer">
            <div className="faqHeader">
                <div className="logo">FAQ</div>
                <div>
                    <p>Veelgestelde vragen voor huurders & verhuurders</p>
                    <p>Jouw vraag niet beantwoord? <span>Neem contact op</span></p>
                </div>
            </div>
            <div className="category">
                <p>Huurders</p>
                <p>Verhuurders</p>
            </div>
            <div className="faqQuestions">
                {Data.map((question) => (
                    <div key={question.id}>
                        <div className="questionContainer">
                            <FaTrailer size="24px" color="#EE7B46" />
                            <p>{question.question}</p>
                            {
                                activeAnswer === question.id ? (
                                    <IconButton className="iconButton" onClick={() => setActiveAnswer(null)}><IoIosArrowForward className="arrowDown" /></IconButton>
                                ) : (
                                    <IconButton className="iconButton" onClick={() => setActiveAnswer(question.id)}><IoIosArrowDown className="arrowDown" /></IconButton>
                                )}
                        </div>
                        {
                            activeAnswer === question.id ?
                                <div className="answerContainer">
                                    {question.answer}
                                </div>
                                : null
                        }
                    </div>
                ))}
            </div>
        </div>
    )
}