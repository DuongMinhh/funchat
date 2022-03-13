import { notification } from 'antd';
import React, { createContext, useState } from 'react';

export const AppContext = createContext()
export default function AppProvider({ children }) {
  const [api, contextHolder] = notification.useNotification();
  notification.config({
    placement: 'bottomLeft',
  })

  const openMessage = (message, type = 'info') => {
    const object = {
      message: `${type.toUpperCase()}`,
      description: (
        <AppContext.Consumer>{() => `${message}`}</AppContext.Consumer>
      ),
    }

    if (type.toLowerCase() === 'error') {
      api.error(object)
      return
    }
    if (type.toLowerCase() === 'warning') {
      api.warning(object)
      return
    }
    if (type.toLowerCase() === 'info') {
      api.info(object);
      return
    }
    if (type.toLowerCase() === 'success') {
      api.success(object);
      return
    }
  };

  const [selectedRoom, setSelectedRoom] = useState({})
  const [openIsAddRoom, setOpenIsAddRoom] = useState(false)
  const [isRefreshRoom, setIsRefreshRoom] = useState(false)
  const [isAddMemberVisible, setIsAddMemberVisible] = useState(false)
  const [stompClient, setStompClient] = useState({})
  const [isUseInfoModalVisible, setIsUseInfoModalVisible] = useState(false)

  return (
    <AppContext.Provider
      value={{
        openMessage,
        selectedRoom, setSelectedRoom,
        openIsAddRoom, setOpenIsAddRoom,
        isRefreshRoom, setIsRefreshRoom,
        isAddMemberVisible, setIsAddMemberVisible,
        stompClient, setStompClient,
        isUseInfoModalVisible, setIsUseInfoModalVisible,
      }}>
      {contextHolder}
      {children}
    </AppContext.Provider>
  )
}
