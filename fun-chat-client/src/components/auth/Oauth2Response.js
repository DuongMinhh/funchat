import React, { useContext, useEffect } from 'react'
import { BE_USER_ME_URL } from '../../constants/BackEndUrl'
import { AuthContext } from '../../context/AuthProvider'

// const convertUser = data => {
//     return {
//         createdAt: data.createdAt,
//         updatedAt: data.updatedAt,
//         id: data.id,
//         name: data.name,
//         email: data.email,
//         provider: data.provider,
//         providerId: data.providerId,
//         photoUrl: data.photoUrl,
//         role: {
//             createdAt: data.role.createdAt,
//             updatedAt: data.role.updatedAt,
//             id: data.role.id,
//             name: data.role.name
//         }
//     }
// }
// const fetchDataAndNavigate = (token, navigation, setUser) => {
//     fetch(BE_USER_ME_URL, {
//         mode: 'cors',
//         method: 'GET',
//         headers: {
//             'Accept': 'application/json',
//             'Authorization': `Bearer ${token}`
//         }
//     })
//     .then(res => res.json())
//     .then(responseData => {
//         setUser(convertUser(responseData.data))
//         navigation('/')
//     })
//     .catch(error => {
//         console.log({ error })
//         navigation('/login')
//     })
// }

export default function Ouath2Response() {
    // const { setUser, setAccessToken, navigation } = useContext(AuthContext)

    // useEffect(() => {
    //     const url = new URL(window.location.href)
    //     const token = url.searchParams.get('token')
    //     const error = url.searchParams.get('error')
    //     if (token) {
    //         setAccessToken(token)
    //         fetchDataAndNavigate(token, navigation, setUser)
    //     }
    //     if (error) {
    //         navigation('/login')
    //     }
    // }, [])

    return (
        <></>
    )
}
