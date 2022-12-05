// import TrailerCard from '../trailers/trailerCard/TrailerCard.jsx'
import useAxios from '../../hooks/use-axios'
import Loading from '../util/Loading'
import { Alert, Typography, Box, Stack } from '@mui/material'
import { PaginatedResponse } from '../../types/PaginatedResponse'
import { TrailerOfferInfo } from '../../types/TrailerOfferInfo'
import TrailerOfferInfoCard from './TrailerOfferInfoCard'
import TrailerCard from '../trailers/trailerCard/TrailerCard'

export default function HomeOffer() {
  const { response, loading, error } = useAxios<
    PaginatedResponse<TrailerOfferInfo>
  >({
    method: 'get',
    url: '/traileroffers',
  })

  if (loading) return <Loading />
  if (error) return <Alert severity="error">{error.message}</Alert>

  return (
    <Stack>
      <Typography color="primary" textAlign="center" variant="h2">
        Bekijk het aanbod
      </Typography>
      <Typography color="primary" textAlign="center">
        En wordt duurzaam door te delen
      </Typography>

      <Box
        display="flex"
        sx={{
          flexDirection: 'row',
          flexWrap: 'wrap',
          gap: '1rem',
          justifyContent: 'center',
          marginY: 4,
        }}
      >
        {response.content.map((trailer) => (
          <TrailerOfferInfoCard trailer={trailer} key={trailer.id} />
        ))}
      </Box>
    </Stack>
  )
}
