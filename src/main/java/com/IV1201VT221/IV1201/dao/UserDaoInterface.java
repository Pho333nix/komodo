package com.IV1201VT221.IV1201.dao;

import com.IV1201VT221.IV1201.model.User;

import java.util.UUID;

public interface UserDaoInterface {
    int insertUser(User user);
    int updateUser(UUID id, User user);
    int deleteUser(UUID id, User user);
}
