import React, { useContext } from 'react'
import { Modal, Form, Input } from 'antd'
import { AppContext } from '../../../context/AppProvider'
import { AuthContext } from '../../../context/AuthProvider'
import { fetchData } from '../../../services/Service'
import { BE_ROOM_BASE_URL } from '../../../constants/BackEndUrl'

export default function AddRoom() {
    const { openIsAddRoom, setOpenIsAddRoom, openMessage, setIsRefreshRoom } = useContext(AppContext)
    const { user } = useContext(AuthContext)
    const [form] = Form.useForm()
    const handleOk = async () => {
        /* Add new room */
        const room = form.getFieldsValue()
        if (!(room.name && room.description)) {
            setOpenIsAddRoom(false)
            openMessage('Bạn nhập thiếu tên phòng hoặc mô tả phòng', 'error')
            return
        }

        if (room) {
            const res = await fetchData(BE_ROOM_BASE_URL, 'POST', { ...room, owner: user })
            if (res !== null && res.status === 200) {
                openMessage('Thành công', 'success')
                setIsRefreshRoom(true)
                setOpenIsAddRoom(false)
                return
            } else if (res !== null && res.status !== 200) {
                openMessage(res.message, 'error')
                return
            } else {
                openMessage('Unexpected error', 'error')
                return
            }
        }

        /* Reset modal */
        form.resetFields()
        setOpenIsAddRoom(false)
    }
    const handleCancel = () => {
        setOpenIsAddRoom(false)
    }
    return (
        <div>
            <Modal
                title='Tạo phòng'
                visible={openIsAddRoom}
                onOk={handleOk}
                onCancel={handleCancel}
            >
                <Form form={form} layout='vertical'>
                    <Form.Item
                        label='Tên phòng'
                        name='name'
                        rules={[{
                            required: true,
                            message: 'Trường bắt buộc!'
                        }]}
                    >
                        <Input placeholder='Nhập tên phòng' />
                    </Form.Item>
                    <Form.Item
                        label='Mô tả'
                        name='description'
                        rules={[{
                            required: true,
                            message: 'Trường bắt buộc!'
                        }]}
                    >
                        <Input placeholder='Nhập mô tả' />
                    </Form.Item>
                </Form>
            </Modal>
        </div>
    )
}