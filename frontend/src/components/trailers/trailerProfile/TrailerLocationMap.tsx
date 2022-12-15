import { GoogleMap, MarkerF } from '@react-google-maps/api'
import { Box } from '@mui/material'
import React from 'react'
import { TrailerOffer } from '../../../types/TrailerOffer'

export default function TrailerLocationMap({
  fakeLatitude,
  fakeLongitude,
}: Pick<TrailerOffer, 'fakeLatitude' | 'fakeLongitude'>) {
  return (
    <Box width="100%" height={300}>
      <GoogleMap
        zoom={13}
        center={{
          lat: fakeLatitude,
          lng: fakeLongitude,
        }}
        options={{
          disableDefaultUI: true,
          clickableIcons: false,
        }}
        mapContainerStyle={{
          width: '100%',
          height: '100%',
          borderRadius: 10,
        }}
      >
        <MarkerF
          position={{
            lat: fakeLatitude,
            lng: fakeLongitude,
          }}
          icon={{
            url: '/marker.svg',
          }}
        ></MarkerF>
      </GoogleMap>
    </Box>
  )
}
