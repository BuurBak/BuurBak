import { useParams } from 'react-router-dom'
import useAxios from '../../hooks/use-axios'
import { TrailerOffer } from '../../types/TrailerOffer'
import Loading from '../../components/util/Loading'
import { Alert, Container, Divider, Grid, Stack } from '@mui/material'
import React from 'react'
import TrailerHeader from '../../components/trailers/trailerProfile/TrailerHeader'
import TrailerOwner from '../../components/trailers/trailerProfile/TrailerOwner'
import ReservationForm from '../../components/trailers/reservation/ReservationForm'

export default function TrailerProfile() {
  const { id } = useParams()
  const {
    response: trailerOffer,
    error,
    loading,
  } = useAxios<TrailerOffer>({
    url: `/traileroffers/${id}`,
    method: 'get',
  })

  if (loading) return <Loading />
  if (error) return <Alert severity="error">{error.message}</Alert>

  return (
    <Container maxWidth="lg">
      <Stack gap={5} paddingTop={5}>
        {/*<TrailerImages images={images} />*/}
        <TrailerHeader trailerOffer={trailerOffer} />
        <Grid container>
          <Grid item xs={0} md={7}>
            <Divider />
          </Grid>
          <Grid xs={0} md={1} />

          <Grid item xs={12} md={4}>
            <TrailerOwner trailerOffer={trailerOffer} />
            <ReservationForm trailerOffer={trailerOffer} />
          </Grid>
        </Grid>
      </Stack>
    </Container>
  )
}
