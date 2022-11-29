import {
  Card,
  CardActionArea,
  CardContent,
  CardMedia,
  Typography,
} from '@mui/material'
import { Box } from '@mui/system'
import { TrailerOfferInfo } from '../../types/TrailerOfferInfo'
import RvHookupIcon from '@mui/icons-material/RvHookup'
interface TrailerOfferInfoCardProps {
  trailer: TrailerOfferInfo
}

export default function TrailerOfferInfoCard({
  trailer,
}: TrailerOfferInfoCardProps) {
  return (
    <Card
      sx={{
        borderRadius: 4,
        maxWidth: 350,
        boxShadow: '0px 3px 15px rgba(0, 0, 0, 0.2)',
      }}
    >
      <CardActionArea href={`/aanbod/${trailer.id}`}>
        {trailer.images ? (
          <CardMedia
            component="img"
            height="140"
            image={trailer.images ? trailer.images[0] : ''}
            alt="trailer"
          />
        ) : (
          <Box
            bgcolor="secondary.main"
            height={140}
            sx={{
              display: 'flex',
              justifyContent: 'center',
              alignItems: 'center',
            }}
          >
            <RvHookupIcon fontSize="large" />
          </Box>
        )}

        <CardContent>
          <Typography gutterBottom variant="h5" color="primary" component="div">
            {trailer.trailerType.name}
          </Typography>
          <Typography variant="body2" color="secondary">
            Lizards are a widespread group of squamate reptiles, with over 6,000
            species, ranging across all continents except Antarctica
          </Typography>
        </CardContent>
      </CardActionArea>
    </Card>
  )
}
