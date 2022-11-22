import { useState } from 'react'
import { Link } from 'react-router-dom'
import './Footer.css'
import FooterData from './FooterData.json'

export default function Footer() {
  const [footerData, setFooterData] = useState(FooterData)

  return (
    <div className="footerContainer" onClick={() => console.log(footerData)}>
      <div className="footerLogo"></div>
      <div className="footerLinks">
        {footerData?.map(({ id, title, links }) => {
          return (
            <div className="footerGrid" key={id}>
              <p>{title}</p>
              {links?.map((link) => (
                <p className="footerLinkContent" key={link.title}>
                  <Link
                    to={link.to}
                    style={{
                      fontSize: 14,
                      textDecoration: 'none',
                      color: '#747474',
                    }}
                  >
                    {link.title}
                  </Link>
                </p>
              ))}
            </div>
          )
        })}
      </div>
    </div>
  )
}
