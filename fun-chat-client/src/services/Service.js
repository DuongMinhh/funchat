import { ACCESS_TOKEN } from "../constants/Constant"

export const fetchData = async (url, method, data = {}) => {
    const token = localStorage.getItem(ACCESS_TOKEN)

    try {
        if (method.toLowerCase() === 'get') {
            const response = await fetch(url, {
                // mode: 'cors',
                method: method,
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
            })
            return await response.json()
        }

        const response = await fetch(url, {
            // mode: 'cors',
            method: method,
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(data)
        })
        return await response.json()
    } catch (error) {
        return { status: 400, message: 'BE not response' }
    }
}