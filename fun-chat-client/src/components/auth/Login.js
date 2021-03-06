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
    const { setAccessToken, navigation } = useContext(AuthContext)
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
                        <Title style={{ textAlign: 'center' }} level={5}>????NG NH???P B???NG M???NG X?? H???I</Title>
                        <Button style={{ width: '100%', marginBottom: 10, borderRadius: 5 }} >
                            <a href={GOOGLE_AUTH_URL}>????ng nh???p b???ng google</a>
                        </Button>
                        <Button style={{ width: '100%', marginBottom: 10, borderRadius: 5 }} >
                            <a href={FACEBOOK_AUTH_URL}>????ng nh???p b???ng facebook</a>
                        </Button>
                        <Button style={{ width: '100%', marginBottom: 10, borderRadius: 5 }} >
                            <a href={GITHUB_AUTH_URL}>????ng nh???p b???ng github</a>
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
                                <Title style={{ textAlign: 'center' }} level={5}>????NG NH???P B???NG T??I KHO???N ???NG D???NG</Title>
                                <Form.Item
                                    label='Email' name='email'
                                    rules={[{
                                        required: true,
                                        message: 'Tr?????ng b???t bu???c!',
                                    }]}
                                >
                                    <Input style={{ borderRadius: 5 }} placeholder='Nh???p email' />
                                </Form.Item>
                                <Form.Item
                                    label='M???t kh???u' name='password'
                                    rules={[{
                                        required: true,
                                        message: 'Tr?????ng b???t bu???c!',
                                    }]}
                                >
                                    <Input style={{ borderRadius: 5 }} type='password' placeholder='Nh???p m???t kh???u' />
                                </Form.Item>
                                <Form.Item wrapperCol={{ offset: 6, span: 18 }}>
                                    <Button
                                        type="primary"
                                        htmlType="submit"
                                        style={{ borderRadius: 5 }}
                                        // onClick={handleLogin}
                                    >
                                        ????ng nh???p
                                    </Button>
                                    <Text style={{ marginLeft: 10 }} onClick={() => setIsLogin(false)}><a>????ng k??</a></Text>
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
                                <Title style={{ textAlign: 'center' }} level={5}>????NG K?? T??I KHO???N ???NG D???NG</Title>
                                <Form.Item
                                    label='T??n ng?????i d??ng' name='name'
                                    rules={[{
                                        required: true,
                                        message: 'Tr?????ng b???t bu???c!',
                                    }]}
                                >
                                    <Input style={{ borderRadius: 5 }} placeholder='Nh???p t??n' />
                                </Form.Item>
                                <Form.Item
                                    label='Email' name='email'
                                    rules={[{
                                        required: true,
                                        message: 'Tr?????ng b???t bu???c!',
                                    }]}
                                >
                                    <Input style={{ borderRadius: 5 }} placeholder='Nh???p email' />
                                </Form.Item>
                                <Form.Item
                                    label='M???t kh???u' name='password'
                                    rules={[{
                                        required: true,
                                        message: 'Tr?????ng b???t bu???c!',
                                    }]}
                                >
                                    <Input style={{ borderRadius: 5 }} type='password' placeholder='Nh???p m???t kh???u' />
                                </Form.Item>
                                <Form.Item
                                    label='X??c nh???n m???t kh???u' name='confirmPassword'
                                    rules={[
                                        {
                                            required: true,
                                            message: 'Tr?????ng b???t bu???c!',
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
                                    <Input type='password' placeholder='X??c nh???n m???t kh???u' />
                                </Form.Item>
                                <Form.Item wrapperCol={{ offset: 6, span: 18 }}>
                                    <Button
                                        type="primary"
                                        htmlType="submit"
                                        style={{ borderRadius: 5 }}
                                        // onClick={handleRegister}
                                        // htmlType="submit"
                                    >
                                        ????ng k??
                                    </Button>
                                    <Text style={{ marginLeft: 10 }} onClick={() => setIsLogin(true)}><a>????ng nh???p</a></Text>
                                </Form.Item>
                            </FormStyle>
                    }

                </Col>
            </Row>
        </Wrapper>
    )
}
