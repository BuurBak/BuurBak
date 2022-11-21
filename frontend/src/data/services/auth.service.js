import axios from 'axios';

const API_URL = "http://localhost/api/v1/auth"

const signUp = async(email, password, firstName, lastName, number, address) => {
    const name = firstName + " " + lastName
    return axios
        .post(API_URL + '/register', {
            email,
            password,
            name,
            number,
            address
        })
        .then((response) => {
            if (response.data.accesToken) {
                localStorage.setItem('user', JSON.stringify(response.data))
            }
            return response.data
        })
}

const login = async(email, password) => {
    return axios
        .post(API_URL + '/login', {
            email,
            password
        })
        .then((response) => {
            if (response.data.accesToken) {
                localStorage.setItem('user', JSON.stringify(response.data))
            }
            return response.data
        })
}

const logout = () => {
    localStorage.removeItem('user')
}

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem('user'))
}

const authService = {
    signUp,
    login,
    logout,
    getCurrentUser
}

export default authService