import {
  Card,
  CardActionArea,
  CardContent,
  CardMedia,
  Stack,
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
        width: 350,
        boxShadow: '0px 3px 15px rgba(0, 0, 0, 0.2)',
      }}
    >
      <CardActionArea href={`/aanbod/${trailer.id}`}>
        {trailer.images ? (
          <CardMedia
            component="img"
            height={200}
            image={trailer.images ? trailer.images[0] : ''}
            alt="trailer"
          />
        ) : (
          <Box
            bgcolor="secondary.main"
            height={200}
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
          <Stack direction="row" justifyContent="space-between">
            <Typography variant="body2" color="secondary">
              {trailer.price ? trailer.price : '$30'}
            </Typography>
            <Typography variant="body2" color="secondary">
              {trailer.location ? trailer.location : 'Onbekend'}
            </Typography>
          </Stack>
        </CardContent>
      </CardActionArea>
    </Card>
  )
}
