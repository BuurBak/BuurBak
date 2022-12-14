import { TrailerOffer } from '../../../types/TrailerOffer'
import {
  Card,
  CardContent,
  CardHeader,
  Rating,
  Stack,
  Typography,
} from '@mui/material'
import ProfilePicture from '../../util/ProfilePicture'
import React from 'react'
import { TbCheck, TbClock } from 'react-icons/tb'

export default function TrailerOwner(props: { trailerOffer: TrailerOffer }) {
  return (
    <Card variant="outlined">
      <CardHeader
        avatar={
          <ProfilePicture dimension={60} user={props.trailerOffer.owner} />
        }
        title={
          <Typography variant="subtitle1" color="primary">
            {props.trailerOffer.owner.name}
          </Typography>
        }
        subheader={<Rating value={2} readOnly />}
      />
      <CardContent>
        <Stack direction="row" justifyContent="space-between">
          <Typography variant="body2" display="flex" alignItems="center" noWrap>
            {/* TODO add response time */}
            <TbClock /> 2 uur response tijd
          </Typography>
          <Typography variant="body2" display="flex" alignItems="center" noWrap>
            {/* TODO add response time */}
            <TbCheck /> 73% acceptatiegraad
          </Typography>
        </Stack>
      </CardContent>
    </Card>
  )
}
