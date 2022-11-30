import { useParams } from 'react-router-dom'
import useAxios from '../../hooks/use-axios'
import { TrailerOffer } from '../../types/TrailerOffer'
import Loading from '../../components/util/Loading'
import { Alert, Container, Divider, Grid, Stack } from '@mui/material'
import { Image } from '../../types/Image'
import TrailerImages from '../../components/trailers/trailerProfile/TrailerImages'
import React from 'react'
import TrailerHeader from '../../components/trailers/trailerProfile/TrailerHeader'
import TrailerOwner from '../../components/trailers/trailerProfile/TrailerOwner'

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

  const images: Image[] = [
    {
      src: 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Mallard2.jpg/640px-Mallard2.jpg',
      title: 'duck',
    },
    {
      src: 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Mallard2.jpg/640px-Mallard2.jpg',
      title: 'duck',
    },
    {
      src: 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Mallard2.jpg/640px-Mallard2.jpg',
      title: 'duck',
    },
    {
      src: 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Mallard2.jpg/640px-Mallard2.jpg',
      title: 'duck',
    },
    {
      src: 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Mallard2.jpg/640px-Mallard2.jpg',
      title: 'duck',
    },
  ]

  if (loading) return <Loading />
  if (error) return <Alert severity="error">{error.message}</Alert>

  return (
    <Container maxWidth="lg">
      <Stack gap={5}>
        <TrailerImages images={images} />
        <TrailerHeader trailerOffer={trailerOffer} />
        <Grid container>
          <Grid item xs={7}>
            <Divider />
          </Grid>
          <Grid xs={1} />
          <Grid item xs={4}>
            <TrailerOwner trailerOffer={trailerOffer} />
          </Grid>
        </Grid>
      </Stack>
    </Container>
  )
}
