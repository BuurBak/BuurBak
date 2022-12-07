import { Grid } from '@mui/material'
import { Image } from '../../../types/Image'

interface TrailerImagesProps {
  images: Image[]
}

export default function TrailerImages({ images }: TrailerImagesProps) {
  return (
    <Grid container spacing={2} bgcolor="primary.main" borderRadius={4}>
      {images.map((item, index) => (
        <Grid item xs={4} className="foo">
          <img src={item.src} alt={item.title} style={{ maxWidth: '100%' }} />
        </Grid>
      ))}
    </Grid>
  )
}
