import { UserAddOutlined } from '@ant-design/icons';
import { Avatar, Button, Tooltip } from 'antd';
import React, { useContext } from 'react';
import styled from 'styled-components';
import { AppContext } from '../../../context/AppProvider';

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
    const { selectedRoom, setIsAddMemberVisible } = useContext(AppContext)

    return (
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
                            Mời
                        </Button>
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
    )
}