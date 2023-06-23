package com.fsse2305.e_commerce_project.service;

import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;
import com.fsse2305.e_commerce_project.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getUserByFirebaseUserDetail(FirebaseUserData firebaseUserData);
}
