import { Form, Input, Modal } from 'antd';
import React, { useContext, useEffect, useState } from 'react';
import { AppContext } from '../../../context/AppProvider';
import { AuthContext } from '../../../context/AuthProvider';
import SubmitModal from './SubmitModal';

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
    const handleOk = () => {
        const info = form.getFieldsValue()
        if (info.name !== user.name || (info.password !== undefined && info.password !== '')) {
            setInfoChanging(info)
            setSubmitVisible(true)
        } else {
            setIsUseInfoModalVisible(false)
            setSubmitVisible(false)
        }
    }
    const handleCancel = () => {
        setIsUseInfoModalVisible(false)
    }
    const closeModal = () => {
        setSubmitVisible(false)
    }
    const performCallApi = async (passwordConfirm) => {
        /* Call API to perform */
        /* Show toast */
        /* Reload page*/
        console.log(infoChanging)
        console.log({passwordConfirm})
    }

    return (
        <div>
            <SubmitModal
                isVisible={submitVisibile}
                closeModal={closeModal}
                performCallApi={performCallApi}
            />
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
                    <Form.Item label='Thiết lập mật khẩu mới' name='password'>
                        <Input
                            placeholder='Nhập mật khẩu'
                            type='password'
                        />
                    </Form.Item>
                </Form>
            </Modal>
        </div>
    )
}
