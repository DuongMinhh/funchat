package com.duongminh.funchat.operation.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.duongminh.funchat.core.constant.MessageResponse;
import com.duongminh.funchat.core.dao.RoleDao;
import com.duongminh.funchat.core.dao.RoomDao;
import com.duongminh.funchat.core.dao.UserDao;
import com.duongminh.funchat.core.dto.RoomDto;
import com.duongminh.funchat.core.dto.UserDto;
import com.duongminh.funchat.core.enums.AuthProvider;
import com.duongminh.funchat.core.enums.UserRole;
import com.duongminh.funchat.core.exception.CustomException;
import com.duongminh.funchat.core.model.UserModel;
import com.duongminh.funchat.operation.service.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoomDao roomDao;

    @Override
    public UserDto create(UserModel model) throws CustomException {
        try {
            /* Validating */
            model.validate();
            if (userDao.existedBy(model.getEmail())) {
                throw new CustomException(MessageResponse.USER_EMAIL_EXISTED);
            }

            UserDto object = new UserDto();
            object.setName(model.getName().trim());
            object.setEmail(model.getEmail().trim());
            object.setPassword(passwordEncoder.encode(model.getPassword()));
            object.setProvider(AuthProvider.LOCAL.name());
            object.setRole(roleDao.getOne(UserRole.ROLE_USER));

            return userDao.create(object);
        } catch (Exception e) {
            if (e instanceof CustomException) {
                log.error(e.getMessage());
                throw e;
            }
            
            log.error(e.getMessage(), e);
            throw new CustomException(MessageResponse.SERVER_ERROR);
        }
    }

    @Override
    public UserDto getUser(Long id) throws CustomException {
        
        UserDto dto = userDao.getOne(id);
        if (Objects.nonNull(dto)) {
            return dto;
        }
        
        log.error(MessageResponse.USER_NOT_FOUND);
        throw new CustomException(MessageResponse.USER_NOT_FOUND);
    }

    @Override
    public Boolean leaveRoom(Long id, Long roomId) throws CustomException {
        try {
            /* Check owner of room */
            RoomDto room = roomDao.getOne(roomId, true);
            if (Objects.isNull(room)) {
                throw new CustomException(MessageResponse.ROOM_NOT_FOUND);
            }
            if (!Objects.isNull(room.getOwner()) && room.getOwner().getId().equals(id)) {
                throw new CustomException(MessageResponse.OWNER_CANNOT_LEAVE_ROOM);
            }
            
            return userDao.leaveRoom(id, roomId);
        } catch (Exception e) {
            if (e instanceof CustomException) {
                log.error(e.getMessage());
                throw e;
            }
            
            log.error(e.getMessage(), e);
            throw new CustomException(MessageResponse.SERVER_ERROR);
        }
    }

    @Override
    public List<UserDto> getUser(String searchText) throws CustomException {
        return userDao.getUser(searchText);
    }

}
