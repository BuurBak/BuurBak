import { Box } from '@mui/material'
import TrailerInfoIcon from './TrailerInfoIcon'
import { TbArrowsLeftRight } from 'react-icons/tb'
import React from 'react'
import { TrailerOffer } from '../../../types/TrailerOffer'

export default function TrailerInfoIcons({
  trailer,
}: {
  trailer: TrailerOffer
}) {
  const gridColumn = 'span 4'
  return (
    <Box display="grid" gridTemplateColumns="repeat(12, 1fr)" gap={2}>
      <Box gridColumn={gridColumn}>
        <TrailerInfoIcon
          icon={<TbArrowsLeftRight size={40} />}
          subHeader={`${trailer.capacity} kg`}
          title="Draagcapaciteit"
        />
      </Box>
    </Box>
  )
}
