package com.fsse2305.e_commerce_project.repository;

import com.fsse2305.e_commerce_project.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findUserByFirebaseUid(String firebaseUid);
}
