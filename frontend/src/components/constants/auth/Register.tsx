import { Alert, Box, Grid, Stack, TextField, Typography } from '@mui/material'
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
    'Iets ging verkeerd bij het maken van uw account, probeer het alstublieft opnieuw'
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
      setError('')
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
      onClose()
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
      <Grid container spacing={{ xs: 2 }}>
        <Grid item xs={12}>
          <Typography variant="h6">Account</Typography>
        </Grid>
        <Grid item xs={12}>
          {/* Account */}
          <TextField
            label="E-mail"
            fullWidth
            required
            type="email"
            error={!!errors['email']}
            helperText={errors['email'] ? errors['email'].message : ''}
            {...register('email')}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            label="Wachtwoord"
            fullWidth
            required
            type="password"
            error={!!errors['password']}
            helperText={errors['password'] ? errors['password'].message : ''}
            {...register('password')}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
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
        </Grid>

        {/* Contactinformatie */}
        <Grid item xs={12}>
          <Typography variant="h6">Contact</Typography>
        </Grid>
        <Grid item xs={12}>
          <TextField
            label="Volledige naam"
            fullWidth
            required
            error={!!errors['name']}
            helperText={errors['name'] ? errors['name'].message : ''}
            {...register('name')}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            label="Telefoonnummer"
            fullWidth
            required
            error={!!errors['phoneNumber']}
            helperText={
              errors['phoneNumber'] ? errors['phoneNumber'].message : ''
            }
            {...register('phoneNumber')}
          />
        </Grid>
        {/* Address */}

        <Grid item xs={6}>
          <TextField
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
          <Grid item xs={12}>
            <Alert severity="error">{error}</Alert>
          </Grid>
        ) : null}

        <Grid item xs={12}>
          <LoadingButton
            variant="contained"
            fullWidth
            type="submit"
            loading={loading}
            size="large"
          >
            Aanmelden
          </LoadingButton>
        </Grid>
      </Grid>
    </Box>
  )
}
