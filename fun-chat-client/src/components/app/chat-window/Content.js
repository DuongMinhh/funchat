import { Button, Form, Input } from 'antd';
import React, { useContext, useEffect, useRef, useState } from 'react';
import styled from 'styled-components';
import { AppContext } from '../../../context/AppProvider';
import { AuthContext } from '../../../context/AuthProvider';
import { fetchData } from '../../../services/Service';
import Message from './Message';
import { BE_MESSAGE_ALL_URL, BE_WEBSOCKET_CLIENT_URL } from '../../../constants/BackEndUrl';
import SockJS from 'sockjs-client'
import Stomp from 'stompjs'

const Wrapper = styled.div`
    height: calc(100vh - 56px);
    display: flex;
    flex-direction: column;
    padding: 11px;
    justify-content: flex-end;
`
const MessageList = styled.div`
    overflow: none;
    position: relative;
    width: 100%;
    height: 100%;
`
const MessageListScrollStyled = styled.div`
    overflow-y: auto;
    position: absolute;
    bottom: 0;
    max-height: 100%;
    width: 100%;
    will-change: transform;
`
const FormStyled = styled(Form)`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 2px 2px 2px 0;
    border: 1px solid rgba(230, 230, 230);
    border-radius: 2px;
    margin-right: 10px;
    .ant-form-item {
        flex: 1;
        margin-bottom: 0;
    }
`
export default function Content() {
    const { user } = useContext(AuthContext)
    const { selectedRoom, openMessage, stompClient, setStompClient } = useContext(AppContext)
    const [messages, setMessages] = useState([])
    const scrollRef = useRef(null)
    const pointerRef = useRef(null)
    const [form] = Form.useForm()
    let socket = null
    let sc = null

    const wsConnectSuccess = (stompClient, subcribeRoomId) => {
        if (stompClient) {
            stompClient.subscribe(`/res/room/${subcribeRoomId}`, message => {
                setMessages(messages => [...messages, JSON.parse(message.body)])
            })
        }
    }

    const wsConnectError = error => {
        openMessage('Websocket error: ' + error, 'error')
    }

    useEffect(() => {
        /* Fetch messages */
        const doFetch = async () => {
            const res = await fetchData(`${BE_MESSAGE_ALL_URL}/${selectedRoom.id}`, 'get')
            if (res !== null && res.status === 200) {
                setMessages(res.data)
            }
        }
        doFetch()

        /* Connect to the socket of selected room */
        socket = new SockJS(BE_WEBSOCKET_CLIENT_URL);
        if (selectedRoom.id) {
            sc = Stomp.over(socket)
            sc.connect({}, () => wsConnectSuccess(sc, selectedRoom.id), error => wsConnectError(error))

            setStompClient(sc)
        }

        return () => {
            if (selectedRoom.id !== null && sc !== null) {
                sc.disconnect()
                setStompClient(sc)
            }
        }
    }, [selectedRoom])

    /* Auto scroll to bottom when the message is changed */
    useEffect(() => {
        if (scrollRef.current) {
            scrollRef.current.scrollIntoView({ behavior: "auto" })
        }
    }, [messages])

    /* Auto foucus on input text whenever the component is rendered */
    useEffect(() => {
        if (pointerRef.current) {
            pointerRef.current.focus()
        }
    })

    /* Handle enter input event */
    const handleEnter = async value => {
        form.resetFields()
        stompClient.send(`/wsroom/${selectedRoom.id}`, {}, JSON.stringify({
            'authorId': user.id,
            'roomId': selectedRoom.id,
            'content': value.message
        }))
    }
    return (
        <Wrapper>
            {selectedRoom.id ?
                <>
                    <MessageList>
                        <MessageListScrollStyled>
                            {messages.map((m, key) => (
                                <Message
                                    key={key}
                                    createAt={m.createdAt}
                                    name={m.author.name}
                                    photoURL={m.author.photoUrl}
                                    text={m.content}
                                />
                            ))}
                            <div ref={scrollRef} />
                        </MessageListScrollStyled>

                    </MessageList>
                    <FormStyled
                        form={form}
                        onFinish={handleEnter}
                    >
                        <Form.Item name='message'>
                            <Input
                                ref={pointerRef}
                                placeholder='Nhập tin nhắn...'
                                bordered={false}
                                autoComplete='off'
                            />
                        </Form.Item>
                        <Button
                            type='primary'
                            htmlType='submit'
                        >
                            Gửi
                        </Button>
                    </FormStyled>
                </>
                :
                <></>
            }
        </Wrapper>
    )
}
