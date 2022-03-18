import { DeleteOutlined, UserAddOutlined, LogoutOutlined } from '@ant-design/icons';
import { isVisible } from '@testing-library/user-event/dist/utils';
import { Avatar, Button, Tooltip } from 'antd';
import React, { useContext, useState, useEffect } from 'react';
import styled from 'styled-components';
import { AppContext } from '../../../context/AppProvider';
import AuthProvider, { AuthContext } from '../../../context/AuthProvider';
import SubmitModal from '../modals/SubmitModal';
import { fetchData } from '../../../services/Service';
import { BE_ROOM_BASE_URL, BE_USER_LEAVE_ROOM } from '../../../constants/BackEndUrl';

const Wrapper = styled.div`
    display: flex;
    justify-content: space-between;
    height: 56px;
    padding: 0 16px;
    align-items: center;
    border-bottom: 1px solid rgba(230, 230, 230);

    .header {
        &__info {
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        &__name {
            margin: 0;
            font-weight: bold;
        }
        &__description {
            font-size: 12px;

        }
    }
`
const ButtonGroupStyled = styled.div`
    display: flex;
    align-items: center;
`
export default function Header() {
    const { selectedRoom, setIsAddMemberVisible, openMessage, setIsRefreshRoom, setSelectedRoom } = useContext(AppContext)
    const { user } = useContext(AuthContext)
    const [isOwner, setIsOwner] = useState(false)
    const [visibleSubmit, setVisibleSubmit] = useState(false)
    const [textSubmit, setTextSubmit] = useState('')
    const [afterSubmitFunction, setAfterSubmitFunction] = useState(null)

    /* Detect owner of chat group */
    useEffect(() => {
        const roomOwner = { ...selectedRoom['owner'] }
        if (roomOwner.email === user.email) {
            setIsOwner(true)
        } else {
            setIsOwner(false)
        }
    }, [selectedRoom])

    const handleRemoveGroup = () => {
        setVisibleSubmit(true)
        setTextSubmit("Bạn có muốn xóa nhóm này?")
        setAfterSubmitFunction(() => performRemoveRoom)
    }
    const handleOutGroup = () => {
        setVisibleSubmit(true)
        setTextSubmit("Bạn có muốn thoát nhóm này?")
        setAfterSubmitFunction(() => performOutRoom)
    }
    const closeModal = () => {
        setVisibleSubmit(false)
    }
    const performRemoveRoom = async (confirmPassword) => {
        /* Call API to perform remove the group */
        const res = await fetchData(`${BE_ROOM_BASE_URL}/${selectedRoom.id}`, 'DELETE', { confirmPassword })
        if (res !== null && res.status === 200) {
            openMessage("Đã xóa nhóm thành công", 'success')
            /* Reload page*/
            setIsRefreshRoom(true)
            setSelectedRoom({})
        } else if (res !== null && res.status !== 200) {
            openMessage(res.message, 'error')
            return
        } else {
            openMessage('Unexpected error', 'error')
            return
        }
    }
    const performOutRoom = async () => {
        const res = await fetchData(`${BE_USER_LEAVE_ROOM}/${selectedRoom.id}`, 'PUT')
        if (res !== null && res.status === 200) {
            openMessage("Đã thoát nhóm thành công", 'success')
            /* Reload page*/
            setIsRefreshRoom(true)
            setSelectedRoom({})
        } else if (res !== null && res.status !== 200) {
            openMessage(res.message, 'error')
            return
        } else {
            openMessage('Unexpected error', 'error')
            return
        }
    }

    return (
        <>
            <SubmitModal
                isVisible={visibleSubmit}
                text={textSubmit}
                closeModal={closeModal}
                performCallApi={afterSubmitFunction}
                needConfirm={false}
            />
            <Wrapper>
                {selectedRoom.id ?
                    <>
                        <div className='header__info'>
                            <p className='header__name'>{selectedRoom ? selectedRoom.name : ''}</p>
                            <span className='header__description'>{selectedRoom ? selectedRoom.description : ''}</span>
                        </div>
                        <ButtonGroupStyled>
                            <Button
                                icon={<UserAddOutlined />}
                                type='text'
                                onClick={() => setIsAddMemberVisible(true)}
                            >
                                Mời thêm
                            </Button>
                            {
                                isOwner ?
                                    <Button
                                        icon={<DeleteOutlined />}
                                        type='text'
                                        onClick={handleRemoveGroup}
                                    >
                                        Xóa nhóm
                                    </Button>
                                    :
                                    <Button
                                        icon={<LogoutOutlined />}
                                        type='text'
                                        onClick={handleOutGroup}
                                    >
                                        Thoát nhóm
                                    </Button>
                            }

                            <Avatar.Group size='small' maxCount={2}>
                                {/* {members.map(member => (
                        <Tooltip
                            key={member.id}
                            title={member.displayName ? member.displayName : ''}
                        >
                            <Avatar src={member.photoURL}>{member.photoURL ? '' : member.displayName?.charAt(0)?.toUpperCase()}</Avatar>
                        </Tooltip>
                    ))} */}
                            </Avatar.Group>
                        </ButtonGroupStyled>
                    </>
                    :
                    <>
                        <div style={{ fontWeight: 'bold' }}>Cần phải chọn phòng trước tiên</div>
                    </>
                }
            </Wrapper>
        </>
    )
}