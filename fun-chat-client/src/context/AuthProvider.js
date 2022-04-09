import { Spin } from 'antd'
import React, { createContext, useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import styled from 'styled-components'
import { BE_USER_ME_URL, BE_WEBSOCKET_CLIENT_URL } from '../constants/BackEndUrl'
import { ACCESS_TOKEN } from '../constants/Constant'
import SockJS from 'sockjs-client'
import { fetchData } from '../services/Service'

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

export const AuthContext = createContext()
export default function AuthProvider({ children }) {
    const [user, setUser] = useState({})
    const [accessToken, setAccessToken] = useState('')
    const navigation = useNavigate()
    const [isLoading, setIsLoading] = useState(false)
    const socket = new SockJS(BE_WEBSOCKET_CLIENT_URL)

    useEffect(async () => {
        setIsLoading(true)
        if (accessToken == '' || accessToken == null) {
            let token = localStorage.getItem(ACCESS_TOKEN)
            if (token) {
                setAccessToken(token)
            }
        }

        if (accessToken !== '' && accessToken !== null) {
            const res = await fetchData(BE_USER_ME_URL, 'GET', {});
            if (res !== null && res.status === 200) {
                setUser(convertUser(res.data))
                navigation('/')
            } else {
                navigation('/login')
            }
        } else {
            navigation('/login')
        }
        setIsLoading(false)
    }, [accessToken])
    return (
        <AuthContext.Provider value={{
            user, setUser,
            accessToken,
            setAccessToken, navigation,
            socket,
        }}>
            <Wrapper>
                {isLoading ? <StyledSpin /> : children}
            </Wrapper>
        </AuthContext.Provider>
    )
}
