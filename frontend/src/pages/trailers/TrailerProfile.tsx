import { useParams } from 'react-router-dom'
import useAxios from '../../hooks/use-axios'
import { TrailerOffer } from '../../types/TrailerOffer'
import Loading from '../../components/util/Loading'
import { Alert, Container, Divider, Grid, Stack } from '@mui/material'
import React from 'react'
import TrailerHeader from '../../components/trailers/trailerProfile/TrailerHeader'
import TrailerOwner from '../../components/trailers/trailerProfile/TrailerOwner'
import ReservationForm from '../../components/trailers/reservation/ReservationForm'
import { TrailerDescription } from '../../components/trailers/trailerProfile/TrailerDescription'
import useMediumBreakpoint from '../../hooks/use-medium-breakpoint'
import MobileBottomReservationBar from '../../components/trailers/trailerProfile/MobileBottomReservationBar'
import TrailerInfoIcons from '../../components/trailers/trailerProfile/TrailerInfoIcons'
import TrailerLocationMap from '../../components/trailers/trailerProfile/TrailerLocationMap'

export default function TrailerProfile() {
  const matches = useMediumBreakpoint()

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
    <Container maxWidth="lg" sx={{ marginBottom: matches ? 0 : 8 }}>
      <Stack gap={5} paddingTop={5}>
        <TrailerHeader trailerOffer={trailerOffer} />
        <Grid container rowGap={4}>
          <Grid item xs={12} md={7}>
            <Stack gap={2}>
              <TrailerDescription trailer={trailerOffer} />
              <Divider />
              <TrailerInfoIcons trailer={trailerOffer} />
              <Divider />
              <TrailerLocationMap
                fakeLatitude={trailerOffer.fakeLatitude}
                fakeLongitude={trailerOffer.fakeLongitude}
              />
            </Stack>
          </Grid>
          <Grid item xs={0} md={1}></Grid>

          {/*Sidebar for making reservations and owner*/}
          {matches ? (
            <Grid item xs={12} md={4}>
              <Grid container spacing={2}>
                <Grid item xs={12} order={{ xs: 2, md: 1 }}>
                  <TrailerOwner trailerOffer={trailerOffer} />
                </Grid>
                <Grid item xs={12} order={{ xs: 1, md: 2 }}>
                  <ReservationForm />
                </Grid>
              </Grid>
            </Grid>
          ) : (
            <MobileBottomReservationBar price={trailerOffer.price} />
          )}

          <Grid item xs={12} md={7}></Grid>
        </Grid>
      </Stack>
    </Container>
  )
}
