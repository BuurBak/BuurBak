import { Alert, Box, TextField, Typography } from '@mui/material'
import { useForm, SubmitHandler } from 'react-hook-form'
import { object, string, TypeOf } from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import { useEffect, useState } from 'react'
import { LoadingButton } from '@mui/lab'
import { useAuth } from '../../../hooks/use-auth'
import { AxiosError } from 'axios'

const loginSchema = object({
  email: string()
    .min(1, 'E-mail is verplicht')
    .email('E-mail is niet een email'),
  password: string()
    .min(1, 'Wachtwoord is verplicht')
    .max(32, 'Wachtwoord moet korter dan 32 characters zijn'),
})

type LoginInput = TypeOf<typeof loginSchema>

interface LoginProps {
  onClose: () => void
}

export default function Login({ onClose }: LoginProps) {
  const { login } = useAuth()
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  const {
    register,
    formState: { errors, isSubmitSuccessful },
    reset,
    handleSubmit,
  } = useForm<LoginInput>({
    resolver: zodResolver(loginSchema),
  })

  useEffect(() => {
    if (isSubmitSuccessful) {
      reset()
    }
  }, [isSubmitSuccessful, reset])

  const onSubmitHandler: SubmitHandler<LoginInput> = async (values) => {
    try {
      setLoading(true)
      await login({
        username: values.email,
        password: values.password,
      })
      onClose()
    } catch (error) {
      if (error instanceof AxiosError) {
        if (error.response.status === 401) {
          setError('E-mail of wachtwoord incorrect')
        }
      } else {
        setError('Er is iets foutgegaan, probeer het later opnieuw')
      }
      throw error
    } finally {
      setLoading(false)
    }
  }

  return (
    <Box
      component="form"
      noValidate
      autoComplete="off"
      onSubmit={handleSubmit(onSubmitHandler)}
    >
      <Typography align="center" variant="h4" gutterBottom>
        Log in bij BuurBak
      </Typography>

      <TextField
        sx={{ mb: 2 }}
        label="E-mail"
        fullWidth
        required
        type="email"
        error={!!errors['email']}
        helperText={errors['email'] ? errors['email'].message : ''}
        {...register('email')}
      />

      <TextField
        sx={{ mb: 2 }}
        label="Wachtwoord"
        fullWidth
        required
        type="password"
        error={!!errors['password']}
        helperText={errors['password'] ? errors['password'].message : ''}
        {...register('password')}
      />

      {!!error ? <Alert severity="error">{error}</Alert> : null}

      <LoadingButton
        variant="contained"
        fullWidth
        type="submit"
        loading={loading}
        sx={{ py: '0.8rem', mt: '1rem' }}
      >
        Inloggen
      </LoadingButton>
    </Box>
  )
}
