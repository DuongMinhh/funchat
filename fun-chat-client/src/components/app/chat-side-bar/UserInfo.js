import React, { useContext } from 'react'
import styled from 'styled-components'
import { Avatar, Button, Col, Row, Typography } from 'antd'
import { AuthContext } from '../../../context/AuthProvider'
import { ACCESS_TOKEN } from '../../../constants/Constant'
import { AppContext } from '../../../context/AppProvider'


const Wrapper = styled.div`
    margin: 10px;

    .username {
        color: white;
        margin-left: 10px;
    }
`
export default function UserInfo() {
    const { user, setUser, setAccessToken } = useContext(AuthContext)
    const { openMessage, setIsUseInfoModalVisible } = useContext(AppContext)
    const handleClick = () => {
        localStorage.setItem(ACCESS_TOKEN, null)
        setUser({})
        setAccessToken('')
        openMessage('Logout success', 'success')
    }

    return (
        <Wrapper>
            <Row>
                <Col span={24}>
                    {user.photoUrl ? <Avatar src={user.photoUrl}></Avatar> : <Avatar>{user.name?.charAt(0)?.toUpperCase()}</Avatar>}
                    <Typography.Text className='username' >{user.name}</Typography.Text>
                </Col>
                <Col span={24}>
                    <Button
                        style={{ width: '100%', marginTop: 10, borderRadius: '5px' }}
                        ghost
                        onClick={() => setIsUseInfoModalVisible(true)}
                    >
                        Thông tin cá nhân
                    </Button>
                    <Button
                        style={{ width: '100%', marginTop: 10, borderRadius: '5px' }}
                        ghost
                        onClick={handleClick}
                    >
                        Đăng xuất
                    </Button>
                </Col>
            </Row>
        </Wrapper>
    )
}
