import { Form, Modal, Input } from 'antd'
import React, { useEffect } from 'react'

export default function SubmitModal({ isVisible, header='', text = '', performCallApi, closeModal, needConfirm }) {
    const [subitForm] = Form.useForm()

    useEffect(() => {
        subitForm.setFieldsValue({
            confirmPassword: ''
        })
    })
    const submitOk = async () => {
        if (needConfirm) {
            const confirmPassword = subitForm.getFieldValue('confirmPassword')

            if (confirmPassword) {
                await performCallApi(confirmPassword)
                await closeModal()
            } else {
                await closeModal()
            }
        } else {
            await performCallApi("")
            await closeModal()
        }
    }
    const submitCancel = () => {
        closeModal()
    }

    return (
        <div>
            <Modal
                title={header !== '' ? header : 'XÁC NHẬN'}
                visible={isVisible}
                onOk={submitOk}
                onCancel={submitCancel}
            >
                {text !== '' ? text : 'Xác nhận thay đổi?'}
                {
                    needConfirm ?
                        <Form form={subitForm} layout='vertical'>
                            <Form.Item label='Nhập mật khẩu' name='confirmPassword'>
                                <Input type='password' />
                            </Form.Item>
                        </Form>
                        :
                        ""
                }
            </Modal>
        </div>
    )
}
