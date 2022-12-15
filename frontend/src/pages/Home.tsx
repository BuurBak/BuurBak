import Footer from '../components/constants/footer/Footer'
import Hero from '../components/home/Hero'
import HeroInfo from '../components/home/HeroInfo'
import HomeOffer from '../components/home/HomeOffer'
import { Box } from '@mui/material'

export default function Home() {
  return (
    <Box>
      <Hero />
      <HeroInfo />
      <HomeOffer />
      <Footer />
    </Box>
  )
}
