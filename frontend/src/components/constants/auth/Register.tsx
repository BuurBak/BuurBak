import { Alert, Box, Grid, TextField, Typography } from '@mui/material'
import { useForm, SubmitHandler } from 'react-hook-form'
import { object, string, TypeOf } from 'zod'
import { zodResolver } from '@hookform/resolvers/zod'
import { useEffect, useState } from 'react'
import { LoadingButton } from '@mui/lab'
import { useAuth } from '../../../hooks/use-auth'
import { AxiosError } from 'axios'

const registerSchema = object({
  name: string()
    .min(1, 'Naam is verplicht')
    .max(100, 'Naam moet minder dan 100 characters lang zijn'),
  email: string()
    .min(1, 'E-mail is verplicht')
    .email('E-mail is niet een email'),
  postalCode: string()
    .min(6, 'Postcode is verplicht')
    .max(6, 'Postcode mag niet langer dan zes characters zijn'),
  houseNumber: string().min(1, 'Huisnummer is verplicht').max(100),
  streetName: string().min(1, 'Straat is verplicht').max(100),
  city: string()
    .min(1, 'Plaatsnaam is verplicht')
    .max(100, 'Plaatsnaam mag niet langer dan 100 characters zijn'),
  phoneNumber: string()
    .min(1, 'Telefoonnummer is verplicht')
    .max(10, 'Telefoonnummer is te lang'),
  password: string()
    .min(8, 'Wachtwoord moet langer dan 8 characters zijn')
    .max(32, 'Wachtwoord moet korter dan 32 characters zijn'),
  passwordConfirm: string().min(1, 'Herhaal uw wachtwoord'),
  // Algemene voorwaarden
  // terms: literal(true, {
  //   invalid_type_error: 'Accept Terms is required',
  // }),
}).refine((data) => data.password === data.passwordConfirm, {
  path: ['passwordConfirm'],
  message: 'Wachtwoorden komen niet overeen',
})

type RegisterInput = TypeOf<typeof registerSchema>

interface RegisterProps {
  onClose: () => void
}

export default function Register({ onClose }: RegisterProps) {
  const { register: registerAuth } = useAuth()
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')
  const ACCOUNT_CREATION_ERROR_MESSAGE =
    'Iets ging verkeerd bij het maken van uw account'
  const ACCOUNT_CREATION_EMAIL_TAKEN_MESSAGE = 'E-mail is al in gebruik'

  const {
    register,
    formState: { errors, isSubmitSuccessful },
    reset,
    handleSubmit,
  } = useForm<RegisterInput>({
    resolver: zodResolver(registerSchema),
  })

  useEffect(() => {
    if (isSubmitSuccessful) {
      reset()
    }
  }, [isSubmitSuccessful, reset])

  const onSubmitHandler: SubmitHandler<RegisterInput> = async (values) => {
    try {
      setLoading(true)
      await registerAuth({
        email: values.email,
        password: values.password,
        name: values.name,
        number: values.phoneNumber,
        address: {
          city: values.city,
          number: values.houseNumber,
          postal_code: values.postalCode,
          street_name: values.streetName,
        },
      })
    } catch (error) {
      if (error instanceof AxiosError) {
        if (error.response.status === 409) {
          setError(ACCOUNT_CREATION_EMAIL_TAKEN_MESSAGE)
        } else {
          setError(ACCOUNT_CREATION_ERROR_MESSAGE)
        }
      } else {
        setError(ACCOUNT_CREATION_ERROR_MESSAGE)
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
        Meld je aan bij BuurBak
      </Typography>

      <Typography variant="h6">Account</Typography>
      {/* Account */}
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
      <TextField
        sx={{ mb: 2 }}
        label="Herhaal wachtwoord"
        fullWidth
        required
        type="password"
        error={!!errors['passwordConfirm']}
        helperText={
          errors['passwordConfirm'] ? errors['passwordConfirm'].message : ''
        }
        {...register('passwordConfirm')}
      />

      {/* Contactinformatie */}
      <Typography variant="h6">Contact</Typography>
      <TextField
        sx={{ mb: 2 }}
        label="Volledige naam"
        fullWidth
        required
        error={!!errors['name']}
        helperText={errors['name'] ? errors['name'].message : ''}
        {...register('name')}
      />
      <TextField
        sx={{ mb: 2 }}
        label="Telefoonnummer"
        fullWidth
        required
        error={!!errors['phoneNumber']}
        helperText={errors['phoneNumber'] ? errors['phoneNumber'].message : ''}
        {...register('phoneNumber')}
      />

      {/* Address */}
      <Grid container columnSpacing={{ xs: 2 }}>
        <Grid item xs={6}>
          <TextField
            sx={{ mb: 2 }}
            label="Postcode"
            fullWidth
            required
            error={!!errors['postalCode']}
            helperText={
              errors['postalCode'] ? errors['postalCode'].message : ''
            }
            {...register('postalCode')}
          />
        </Grid>

        <Grid item xs={6}>
          <TextField
            sx={{ mb: 2 }}
            label="Huisnummer & toevoegingen"
            fullWidth
            required
            error={!!errors['houseNumber']}
            helperText={
              errors['houseNumber'] ? errors['houseNumber'].message : ''
            }
            {...register('houseNumber')}
          />
        </Grid>

        <Grid item xs={12}>
          <TextField
            sx={{ mb: 2 }}
            label="Woonplaats"
            fullWidth
            required
            error={!!errors['city']}
            helperText={errors['city'] ? errors['city'].message : ''}
            {...register('city')}
          />
        </Grid>

        <Grid item xs={12}>
          <TextField
            sx={{ mb: 2 }}
            label="Straatnaam"
            fullWidth
            required
            error={!!errors['streetName']}
            helperText={
              errors['streetName'] ? errors['streetName'].message : ''
            }
            {...register('streetName')}
          />
        </Grid>
      </Grid>

      {/* Algemene voorwaarden */}
      {/* <FormGroup>
              <FormControlLabel
                control={<Checkbox required />}
                {...register('terms')}
                label={
                  <Typography color={errors['terms'] ? 'error' : 'inherit'}>
                    Accept Terms and Conditions
                  </Typography>
                }
              />
              <FormHelperText error={!!errors['terms']}>
                {errors['terms'] ? errors['terms'].message : ''}
              </FormHelperText>
            </FormGroup> */}

      {!!error ? (
        <Alert severity="error">{error} - Probeer het opnieuw</Alert>
      ) : null}

      <LoadingButton
        variant="contained"
        fullWidth
        type="submit"
        loading={loading}
        sx={{ py: '0.8rem', mt: '1rem' }}
      >
        Aanmelden
      </LoadingButton>
    </Box>
  )
}
