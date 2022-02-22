import React from 'react'
import styled from 'styled-components'
import { Row, Col } from 'antd'
import Header from './Header'
import Content from './Content'

const Wrapper = styled.div`
    height: 100vh;
`
export default function ChatWindow() {
    return (
        <Wrapper>
            <Row>
                <Col span={24}><Header /></Col>
                <Col span={24}><Content /></Col>
            </Row>
        </Wrapper>
    )
}