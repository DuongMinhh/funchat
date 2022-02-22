import { Spin } from 'antd'
import React, { createContext, useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'
import { BE_USER_ME_URL, BE_WEBSOCKET_CLIENT_URL } from '../constants/BackEndUrl'
import { ACCESS_TOKEN } from '../constants/Constant'
import SockJS from 'sockjs-client'

const Wrapper = styled.div`
    height: 100vh;
    width: 100vw;
`
const StyledSpin = styled(Spin)`
    position: relative;
    top: 40%;
    left: 50%;
    transform: translate(-50%, -50%);
`
const convertUser = data => {
    return {
        createdAt: data.createdAt,
        updatedAt: data.updatedAt,
        id: data.id,
        name: data.name,
        email: data.email,
        provider: data.provider,
        providerId: data.providerId,
        photoUrl: data.photoUrl,
        role: {
            createdAt: data.role.createdAt,
            updatedAt: data.role.updatedAt,
            id: data.role.id,
            name: data.role.name
        }
    }
}
const fetchDataAndNavigate = (token, navigation, setUser) => {
    fetch(BE_USER_ME_URL, {
        mode: 'cors',
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    })
    .then(res => res.json())
    .then(responseData => {
        setUser(convertUser(responseData.data))
        navigation('/')
        return
    })
    .catch(error => {
        console.log({ error })
        navigation('/login')
        return
    })
}

export const AuthContext = createContext()
export default function AuthProvider({ children }) {
    const [user, setUser] = useState({})
    const [accessToken, setAccessToken] = useState('')
    const navigation = useNavigate()
    const [isLoading, setIsLoading] = useState(false)
    const socket = new SockJS(BE_WEBSOCKET_CLIENT_URL);

    /* Check login status when start up */
    useEffect(() => {
        const url = new URL(window.location.href)
        let token = url.searchParams.get('token')
        let error = url.searchParams.get('error')

        if (token) {
            localStorage.setItem(ACCESS_TOKEN, token)
            setAccessToken(token)
        } else {
            token = localStorage.getItem(ACCESS_TOKEN)
            setAccessToken(token)
        }

        if (accessToken) {
            fetchDataAndNavigate(accessToken, navigation, setUser)
            return
        } else if (error) {
            navigation('/login')
            return 
        } else {
            navigation('/login')
            return
        }
    }, [accessToken])
    return (
        <AuthContext.Provider value={{ user, setUser, accessToken, setAccessToken, navigation, socket }}>
            <Wrapper>
                {isLoading ? <StyledSpin /> : children}
            </Wrapper>
        </AuthContext.Provider>
    )
}
