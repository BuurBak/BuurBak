import { ImageList, ImageListItem } from '@mui/material'
import { Image } from '../../../types/Image'

interface TrailerImagesProps {
  images: Image[]
}

export default function TrailerImages({ images }: TrailerImagesProps) {
  return (
    <ImageList variant="quilted" gap={12} cols={4} sx={{ borderRadius: 4 }}>
      {images.map((image, index) => (
        <ImageListItem
          key={image.src}
          cols={index === 0 ? 2 : 1}
          rows={index === 0 ? 2 : 1}
        >
          <img src={image.src} alt={image.title} loading="lazy" />
        </ImageListItem>
      ))}
    </ImageList>
  )
}
