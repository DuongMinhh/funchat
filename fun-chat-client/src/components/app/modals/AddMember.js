import { Form, Input, Modal } from 'antd';
import React, { useContext } from 'react';
import { AppContext } from '../../../context/AppProvider';

export default function AddMember() {
    const { isAddMemberVisible, setIsAddMemberVisible } = useContext(AppContext)

    const [form] = Form.useForm()
    const handleOk = () => {
        /* Reset modal */
        form.resetFields()
        setIsAddMemberVisible(false)
    }
    const handleCancel = () => {
        setIsAddMemberVisible(false)
    }
    return (
        <div>
            <Modal
                title='Thêm member'
                visible={isAddMemberVisible}
                onOk={handleOk}
                onCancel={handleCancel}
            >
                <Form form={form} layout='vertical'>
                    <Form.Item label='Nhập user ID' name='uid'>
                        <Input placeholder='Nhập uid để mời thêm người dùng...' />
                    </Form.Item>
                </Form>
            </Modal>
        </div>
    )
}
