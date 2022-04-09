import { Avatar, Typography } from 'antd'
import React, { useContext, useEffect, useState } from 'react'
import styled from 'styled-components'
import { AuthContext } from '../../../context/AuthProvider'

const Wrapper = styled.div`
    display: flex;
    margin-bottom: 10px;

    .message {
        text-align: start;
        border: 1px solid rgba(230, 230, 230);
        padding: 5px;
        border-radius: 5px;
    }
    .message .author {
        margin-left: 5px;
        font-weight: bold;
    }
    .message .date {
        margin-left: 10px;
        font-size: 11px;
        color: #a7a7a7;
    }
    .message .content {
        margin-left: 30px;
    }
`
const WrapperAuthor = styled.div`
    display: flex;
    flex-flow: column nowrap;
    align-items: flex-end;
    margin-bottom: 10px;
    margin-right: 10px;

    .message {
        text-align: end;
        border: 1px solid #e5efff;;
        padding: 5px;
        border-radius: 5px;
        background-color: #e5efff;
    }
    .message .author {
        margin-right: 5px;
        font-weight: bold;
    }
    .message .date {
        margin-right: 10px;
        font-size: 11px;
        color: #a7a7a7;
    }
    .message .content {
        margin-right: 30px;
    }
`
export default function Message({ text, name, userId, createAt, photoURL }) {
    const { user } = useContext(AuthContext)
    const [isAuthor, setIsAuthor] = useState(false)

    useEffect(() => {
        if (user.id === userId) {
            setIsAuthor(true)
        } else {
            setIsAuthor(false)
        }
    }, [])

    return (
        !isAuthor ?
            <Wrapper>
                <div className='message'>
                    <div>
                        <Avatar size='small' src={photoURL}>A</Avatar>
                        <Typography.Text className='author'>{name}</Typography.Text>
                        <Typography.Text className='date'>{createAt}</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text className='content'>{text}</Typography.Text>
                    </div>
                </div>
            </Wrapper>
            :
            <WrapperAuthor>
                <div className='message'>
                    <div>
                        <Typography.Text className='date'>{createAt}</Typography.Text>
                        <Typography.Text className='author'>Tôi</Typography.Text>
                        <Avatar size='small' src={user.photoUrl}>B</Avatar>
                    </div>
                    <div>
                        <Typography.Text className='content'>{text}</Typography.Text>
                    </div>
                </div>
            </WrapperAuthor>
    )
}
