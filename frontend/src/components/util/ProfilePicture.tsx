import { Avatar } from '@mui/material'
import PersonIcon from '@mui/icons-material/Person'
import { User } from '../../types/User'

interface ProfilePictureProps {
  user: User
  dimension?: number
}

export default function ProfilePicture({
  user,
  dimension = 32,
}: ProfilePictureProps) {
  if (user.profile_picture_url)
    return (
      <Avatar
        sx={{ width: dimension, height: dimension }}
        alt={user.name}
        src={user.profile_picture_url}
      />
    )
  else
    return (
      <Avatar sx={{ width: dimension, height: dimension }}>
        <PersonIcon />
      </Avatar>
    )
}
