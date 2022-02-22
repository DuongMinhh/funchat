import React, { useContext, useEffect } from 'react'
import { Row, Col } from 'antd'
import ChatSideBar from './chat-side-bar/ChatSideBar'
import ChatWindow from './chat-window/ChatWindow'
import { AppContext } from '../../context/AppProvider'
import { AuthContext } from '../../context/AuthProvider'

export default function Main() {
    const { openMessage } = useContext(AppContext)
    const { accessToken } = useContext(AuthContext)

    useEffect(() => {
        if (accessToken) {
            openMessage('Login success', 'success')
        }
    }, [])

    return (
        <div>
            <Row>
                <Col span={6}><ChatSideBar/></Col>
                <Col span={18}><ChatWindow/></Col>
            </Row>
        </div>
    )
}
