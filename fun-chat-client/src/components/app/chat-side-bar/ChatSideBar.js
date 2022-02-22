import React from 'react'
import styled from 'styled-components'
import { Row, Col } from 'antd'
import RoomList from './RoomList'
import UserInfo from './UserInfo'

const Wrapper = styled.div`
    background: #696969;
    color: white;
    height: 100vh;
`
export default function ChatSideBar() {
    return (
        <Wrapper>
            <Row>
                <Col span={24}><UserInfo/></Col>
                <Col span={24}><RoomList/></Col>
            </Row>
        </Wrapper>
    )
}