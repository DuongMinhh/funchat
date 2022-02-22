import React, { useContext, useEffect, useState } from 'react'
import { BE_ROOM_ALL_URL } from '../../../constants/BackEndUrl'
import { AppContext } from '../../../context/AppProvider'
import { fetchData } from '../../../services/Service'
import { Button, Collapse, Typography } from 'antd'
import styled from 'styled-components'
import { PlusSquareFilled } from '@ant-design/icons'

const { Panel } = Collapse
const PanelStyled = styled(Panel)`
    &&& {
        .ant-collapse-header, p {
            color: white;
        }
        .ant-collapse-content-box {
            padding: 0 40px;
        }
        .add-room {
            color: white;
            padding: 0;
        }
        a.ant-typography {
            color: white;
        }
    }
`
const LinkStyled = styled(Typography.Link)`
    display: block;
    margin-bottom: 5px;
    border: 1px solid whitesmoke;
    padding: 3px;
    border-radius: 5px;
`
export default function RoomList() {
    const [rooms, setRooms] = useState([])
    const { openMessage, setSelectedRoom, setOpenIsAddRoom, isRefreshRoom, setIsRefreshRoom } = useContext(AppContext)

    useEffect(() => {
        async function doFetch() {
            const res = await fetchData(BE_ROOM_ALL_URL, 'GET')
            setIsRefreshRoom(false)
            if (res !== null && res.status === 200) {
                setRooms(res.data)
                return
            } else if (res !== null && res.status !== 200) {
                openMessage(res.message, 'error')
                return
            } else {
                openMessage('Unexpected error', 'error')
                return
            }
        }

        doFetch()
    }, [isRefreshRoom])

    const handleSelectRoom = id => {
        setSelectedRoom(rooms.filter(e => e.id === id)[0])
    }
    const handleAddRoom = () => {
        setOpenIsAddRoom(true)
    }
    return (
        <Collapse ghost defaultActiveKey={['1']}>
            <PanelStyled header='Danh sách phòng của bạn' key='1'>
                {rooms.map((room) => (
                    <LinkStyled
                        key={room.id}
                        onClick={() => handleSelectRoom(room.id)}
                    >
                        {room.name}
                    </LinkStyled>
                ))}
                <Button
                    type='text'
                    icon={<PlusSquareFilled />}
                    className='add-room'
                    onClick={handleAddRoom}
                >
                    Thêm phòng
                </Button>
            </PanelStyled>
        </Collapse>
    )
}
