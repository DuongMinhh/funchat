import { Form, Input, Modal } from 'antd'
import React, { useContext, useEffect, useState } from 'react'
import { AppContext } from '../../../context/AppProvider'
import { AuthContext } from '../../../context/AuthProvider'

export const UseInfoModal = () => {
    const [form] = Form.useForm()
    const [subitForm] = Form.useForm()
    const { isUseInfoModalVisible, setIsUseInfoModalVisible } = useContext(AppContext)
    const [submitVisibile, setSubmitVisible] = useState(false)
    const [infoChanging, setInfoChanging] = useState({})
    const { user } = useContext(AuthContext)

    useEffect(() => {
        form.setFieldsValue({
            name: user.name,
            email: user.email
        })
        subitForm.setFieldsValue({
            passwordConfirm: ''
        })
    })

    const submitOk = () => {
        /* Call BE API to change info of user */


        setSubmitVisible(false)
        setIsUseInfoModalVisible(false)
    }
    const submitCancel = () => {
        setSubmitVisible(false)
        setIsUseInfoModalVisible(false)
    }
    const handleOk = () => {
        const info = form.getFieldsValue()
        if (info.name !== user.name || info.password !== undefined) {
            setInfoChanging(info)
            setSubmitVisible(true)
        } else {
            setIsUseInfoModalVisible(false)
        }
    }
    const handleCancel = () => {
        setIsUseInfoModalVisible(false)
    }

    return (
        <div>
            {
                submitVisibile ?
                    <Modal
                        title='Xác nhận thay đổi?'
                        visible={isUseInfoModalVisible}
                        onOk={submitOk}
                        onCancel={submitCancel}
                    >
                        <Form form={subitForm} layout='vertical'>
                            <Form.Item label='Nhập mật khẩu hiện tại' name='passwordConfirm'>
                                <Input type='password' />
                            </Form.Item>
                        </Form>
                    </Modal>
                    :
                    <Modal
                        title='Thông tin cá nhân'
                        visible={isUseInfoModalVisible}
                        onOk={handleOk}
                        onCancel={handleCancel}
                    >
                        <Form form={form} layout='vertical'>
                            <Form.Item label='Tên hiển thị' name='name'>
                                <Input />
                            </Form.Item>
                            <Form.Item label='Địa chỉ email' name='email'>
                                <Input
                                    style={{ color: '#A9A9A9' }}
                                    readOnly
                                />
                            </Form.Item>
                            <Form.Item label='Mật khẩu' name='password'>
                                <Input
                                    placeholder='Thiết lập mật khẩu mới'
                                    type='password'
                                />
                            </Form.Item>
                        </Form>
                    </Modal>
            }
        </div>
    )
}
