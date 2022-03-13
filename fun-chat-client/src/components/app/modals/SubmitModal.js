import { Form, Modal, Input } from 'antd'
import React, { useEffect } from 'react'

export default function SubmitModal({ isVisible, text='', performCallApi, closeModal }) {
    const [subitForm] = Form.useForm()

    useEffect(() => {
        subitForm.setFieldsValue({
            confirmPassword: ''
        })
    })
    const submitOk = async () => {
        const confirmPassword = subitForm.getFieldValue('confirmPassword')

        if (confirmPassword) {
            await performCallApi(confirmPassword)
            await closeModal()
        } else {
            await closeModal()
        }
    }
    const submitCancel = () => {
        closeModal()
    }

    return (
        <div>
            <Modal
                title={text !== '' ? text : 'Xác nhận thay đổi?'}
                visible={isVisible}
                onOk={submitOk}
                onCancel={submitCancel}
            >
                <Form form={subitForm} layout='vertical'>
                    <Form.Item label='Nhập mật khẩu' name='confirmPassword'>
                        <Input type='password' />
                    </Form.Item>
                </Form>
            </Modal>
        </div>
    )
}
