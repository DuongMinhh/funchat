import React, { useContext, useState } from 'react'
import { Row, Col, Typography, Button, Form, Input } from 'antd'
import { BE_AUTH_LOGIN_URL, FACEBOOK_AUTH_URL, GITHUB_AUTH_URL, GOOGLE_AUTH_URL } from '../../constants/BackEndUrl'
import styled from 'styled-components'
import { BE_AUTH_SIGNUP_URL } from '../../constants/BackEndUrl'
import { fetchData } from '../../services/Service'
import { AuthContext } from '../../context/AuthProvider'
import { ACCESS_TOKEN } from '../../constants/Constant'
import { AppContext } from '../../context/AppProvider'

const { Title, Text } = Typography
const FormStyle = styled(Form)`
    border-radius: 5px;
    background-color: white;
    padding: 15px;

    h1 {
        text-align: center;
        color: #1890FF;
    }
    .ant-row .ant-form-item-label{
        text-align: left;
    }
    .ant-row .ant-form-item-control{
        text-align: right;
        .ant-form-item-control-input {
            text-align: left;
        }
    }
    .ant-row {
        margin-bottom: 10px;
    }
`
const Oauth2Wrapper = styled.div`
    border-radius: 5px;
    background-color: white;
    padding: 15px;
`
const Wrapper = styled.div`
    border: 1px solid rgba(230, 230, 230);
    margin: 30px;
    border-radius: 10px;
    height: 90vh;
`

export default function Login() {
    const [isLogin, setIsLogin] = useState(true)
    const { setAccessToken } = useContext(AuthContext)
    const { openMessage } = useContext(AppContext)

    const handleLogin = async value => {
        const res = await fetchData(BE_AUTH_LOGIN_URL, 'POST', value)
        if (res !== null && res.status === 200) {
            // openMessage('Login success', 'success')
            localStorage.setItem(ACCESS_TOKEN, res.data)
            setAccessToken(res.data)
            return
        } else if (res !== null && res.status !== 200) {
            openMessage(res.message, 'error')
            return
        } else {
            openMessage('Unexpected error', 'error')
            return
        }
    }
    const handleRegister = async value => {
        const res = await fetchData(BE_AUTH_SIGNUP_URL, 'POST', value)
        if (res !== null && res.status === 200) {
            openMessage('Register success', 'success')
            setIsLogin(true)
            return
        } else if (res !== null && res.status !== 200) {
            openMessage(res.message, 'error')
            setIsLogin(false)
            return
        } else {
            openMessage('Unexpected error', 'error')
            return
        }
    }

    return (
        <Wrapper>
            <Row justify='center'>
                <Col span={24}><Title style={{ textAlign: 'center', margin: 20 }} level={2}>FUN CHAT APPLICATION</Title></Col>
            </Row>
            <Row justify='center'>
                <Col span={10}>
                    <Oauth2Wrapper>
                        <Title style={{ textAlign: 'center' }} level={5}>ĐĂNG NHẬP BẰNG MẠNG XÃ HỘI</Title>
                        <Button style={{ width: '100%', marginBottom: 10, borderRadius: 5 }} >
                            <a href={GOOGLE_AUTH_URL}>Đăng nhập bằng google</a>
                        </Button>
                        <Button style={{ width: '100%', marginBottom: 10, borderRadius: 5 }} >
                            <a href={FACEBOOK_AUTH_URL}>Đăng nhập bằng facebook</a>
                        </Button>
                        <Button style={{ width: '100%', marginBottom: 10, borderRadius: 5 }} >
                            <a href={GITHUB_AUTH_URL}>Đăng nhập bằng github</a>
                        </Button>
                    </Oauth2Wrapper>
                </Col>
                <Col span={10}>
                    {
                        isLogin ?
                            <FormStyle
                                layout='horizontal'
                                labelCol={{ span: 6 }}
                                wrapperCol={{ span: 18 }}
                                onFinish={handleLogin}
                            >
                                <Title style={{ textAlign: 'center' }} level={5}>ĐĂNG NHẬP BẰNG TÀI KHOẢN ỨNG DỤNG</Title>
                                <Form.Item
                                    label='Email' name='email'
                                    rules={[{
                                        required: true,
                                        message: 'Trường bắt buộc!',
                                    }]}
                                >
                                    <Input style={{ borderRadius: 5 }} placeholder='Nhập email' />
                                </Form.Item>
                                <Form.Item
                                    label='Mật khẩu' name='password'
                                    rules={[{
                                        required: true,
                                        message: 'Trường bắt buộc!',
                                    }]}
                                >
                                    <Input style={{ borderRadius: 5 }} type='password' placeholder='Nhập mật khẩu' />
                                </Form.Item>
                                <Form.Item wrapperCol={{ offset: 6, span: 18 }}>
                                    <Button
                                        type="primary"
                                        htmlType="submit"
                                        style={{ borderRadius: 5 }}
                                        // onClick={handleLogin}
                                    >
                                        Đăng nhập
                                    </Button>
                                    <Text style={{ marginLeft: 10 }} onClick={() => setIsLogin(false)}><a>Đăng ký</a></Text>
                                </Form.Item>
                            </FormStyle>
                            :
                            <FormStyle
                                layout='horizontal'
                                labelCol={{ span: 6 }}
                                wrapperCol={{ span: 18 }}
                                // form={form}
                                onFinish={handleRegister}
                            >
                                <Title style={{ textAlign: 'center' }} level={5}>ĐĂNG KÝ TÀI KHOẢN ỨNG DỤNG</Title>
                                <Form.Item
                                    label='Tên người dùng' name='name'
                                    rules={[{
                                        required: true,
                                        message: 'Trường bắt buộc!',
                                    }]}
                                >
                                    <Input style={{ borderRadius: 5 }} placeholder='Nhập tên' />
                                </Form.Item>
                                <Form.Item
                                    label='Email' name='email'
                                    rules={[{
                                        required: true,
                                        message: 'Trường bắt buộc!',
                                    }]}
                                >
                                    <Input style={{ borderRadius: 5 }} placeholder='Nhập email' />
                                </Form.Item>
                                <Form.Item
                                    label='Mật khẩu' name='password'
                                    rules={[{
                                        required: true,
                                        message: 'Trường bắt buộc!',
                                    }]}
                                >
                                    <Input style={{ borderRadius: 5 }} type='password' placeholder='Nhập mật khẩu' />
                                </Form.Item>
                                <Form.Item
                                    label='Xác nhận mật khẩu' name='confirmPassword'
                                    rules={[
                                        {
                                            required: true,
                                            message: 'Trường bắt buộc!',
                                        },
                                        ({ getFieldValue }) => ({
                                            validator(_, value) {
                                                if (!value || getFieldValue('password') === value) {
                                                    return Promise.resolve();
                                                }

                                                return Promise.reject(new Error('Sai password!'));
                                            },
                                        }),
                                    ]}
                                >
                                    <Input type='password' placeholder='Xác nhận mật khẩu' />
                                </Form.Item>
                                <Form.Item wrapperCol={{ offset: 6, span: 18 }}>
                                    <Button
                                        type="primary"
                                        htmlType="submit"
                                        style={{ borderRadius: 5 }}
                                        // onClick={handleRegister}
                                        htmlType="submit"
                                    >
                                        Đăng ký
                                    </Button>
                                    <Text style={{ marginLeft: 10 }} onClick={() => setIsLogin(true)}><a>Đăng nhập</a></Text>
                                </Form.Item>
                            </FormStyle>
                    }

                </Col>
            </Row>
        </Wrapper>
    )
}
