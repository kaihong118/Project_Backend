package com.fsse2305.e_commerce_project.service.impl;

import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;
import com.fsse2305.e_commerce_project.data.user.entity.UserEntity;
import com.fsse2305.e_commerce_project.repository.UserRepository;
import com.fsse2305.e_commerce_project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity getUserByFirebaseUserDetail(FirebaseUserData firebaseUserData) {
        UserEntity userEntity = userRepository.findUserByFirebaseUid(firebaseUserData.getFirebaseUid());
        if(userEntity == null) {
            logger.info("Get User By Firebase Uid: Fail > Created New User");
            UserEntity newUserEntity = new UserEntity(firebaseUserData);
            userRepository.save(newUserEntity);
            return newUserEntity;
        }
        return userEntity;
    }
}
