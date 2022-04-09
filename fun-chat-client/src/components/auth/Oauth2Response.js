import React, { useContext, useEffect } from 'react'
import { ACCESS_TOKEN } from '../../constants/Constant'
import { AppContext } from '../../context/AppProvider'
import { AuthContext } from '../../context/AuthProvider'

export default function Ouath2Response() {
    const {  setAccessToken } = useContext(AuthContext)
    const { openMessage } = useContext(AppContext)

    useEffect(() => {
        const url = new URL(window.location.href)
        const token = url.searchParams.get('token')
        const error = url.searchParams.get('error')
        if (token) {
            setAccessToken(token)
            localStorage.setItem(ACCESS_TOKEN, token)
        }
        if (error) {
            openMessage(error, 'error')
        }
    }, [])

    return (
        <></>
    )
}
