import { useParams } from 'react-router-dom'
import useAxios from '../../hooks/use-axios'
import { TrailerOffer } from '../../types/TrailerOffer'
import Loading from '../../components/util/Loading'
import { Alert, Container, Grid, Stack } from '@mui/material'
import React from 'react'
import TrailerHeader from '../../components/trailers/trailerProfile/TrailerHeader'
import TrailerOwner from '../../components/trailers/trailerProfile/TrailerOwner'
import ReservationForm from '../../components/trailers/reservation/ReservationForm'
import { TrailerDescription } from '../../components/trailers/trailerProfile/TrailerDescription'

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
        <TrailerHeader trailerOffer={trailerOffer} />
        <Grid container rowGap={4}>
          <Grid item xs={12} md={7}>
            <TrailerDescription trailer={trailerOffer} />
          </Grid>
          <Grid item xs={0} md={1}></Grid>
          <Grid item xs={12} md={4}>
            <Grid container spacing={2}>
              <Grid item xs={12} order={{ xs: 2, md: 1 }}>
                <TrailerOwner trailerOffer={trailerOffer} />
              </Grid>
              <Grid item xs={12} order={{ xs: 1, md: 2 }}>
                <ReservationForm trailerOffer={trailerOffer} />
              </Grid>
            </Grid>
          </Grid>
          <Grid item xs={12} md={7}></Grid>
        </Grid>
      </Stack>
    </Container>
  )
}
