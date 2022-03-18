import { Form, Modal, Select } from 'antd';
import React, { useContext, useState, useEffect } from 'react';
import { AppContext } from '../../../context/AppProvider';
import { BE_USER_SEARCH_URL, BE_ROOM_BASE_URL } from '../../../constants/BackEndUrl'
import { fetchData } from '../../../services/Service';

const { Option } = Select;

export default function AddMember() {
    const { isAddMemberVisible, setIsAddMemberVisible, openMessage, selectedRoom } = useContext(AppContext)
    const [searchText, setSearchText] = useState('')
    const [userList, setUserList] = useState([])
    const [selectedUserId, setSelectedUserId] = useState(null)

    useEffect(() => {
        const timeOutId = setTimeout(() => searchUser(searchText), 1500);
        return () => clearTimeout(timeOutId);
    }, [searchText]);

    const handleOk = async () => {
        if(selectedUserId !== null) {
            const res = await fetchData(`${BE_ROOM_BASE_URL}/${selectedRoom.id}/add-user?userId=${selectedUserId}`, 'PUT')
            if (res !== null && res.status === 200) {
                openMessage('Thêm người dùng thành công', 'success')
            } else if (res !== null && res.status !== 200) {
                openMessage(res.message, 'error')
                return
            } else {
                openMessage('Unexpected error', 'error')
                return
            }
        }
        /* Reset modal */
        setIsAddMemberVisible(false)
    }
    const handleCancel = () => {
        setIsAddMemberVisible(false)
    }
    const handleSearch = value => {
        setSearchText(value)
    }
    const searchUser = async searchText => {
        if (searchText !== '') {
            const res = await fetchData(`${BE_USER_SEARCH_URL}?key=${searchText}`, 'GET')
            if (res !== null && res.status === 200) {
                setUserList(res.data)
            } else if (res !== null && res.status !== 200) {
                openMessage(res.message, 'error')
                return
            } else {
                openMessage('Unexpected error', 'error')
                return
            }
        } else {
            setUserList([])
        }
    }
    const handleSelectUser = id => {
        setSelectedUserId(id)
    }
    return (
        <div>
            <Modal
                title='Thêm member'
                visible={isAddMemberVisible}
                onOk={handleOk}
                onCancel={handleCancel}
            >
                <Form layout='vertical'>
                    <Form.Item label='Nhập tên hoặc email' name='searchText'>
                        <Select
                            showSearch
                            value={searchText}
                            placeholder='Nhập tên hoặc email...'
                            defaultActiveFirstOption={false}
                            showArrow={false}
                            filterOption={false}
                            onChange={handleSelectUser}
                            onSearch={handleSearch}
                            notFoundContent={null}
                        >
                            {
                                userList.map(user => <Option key={user.id} value={user.id}>{user.name}</Option>)
                            }
                        </Select>
                    </Form.Item>
                </Form>
            </Modal>
        </div>
    )
}
