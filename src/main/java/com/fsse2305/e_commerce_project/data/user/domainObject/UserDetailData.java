package com.fsse2305.e_commerce_project.data.user.domainObject;

import com.fsse2305.e_commerce_project.data.user.entity.UserEntity;

public class UserDetailData {
    private Integer uid;
    private String email;
    private String firebaseUid;

    public UserDetailData(UserEntity userEntity) {
        this.uid = userEntity.getUid();
        this.email = userEntity.getEmail();
        this.firebaseUid = userEntity.getFirebaseUid();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }
}
