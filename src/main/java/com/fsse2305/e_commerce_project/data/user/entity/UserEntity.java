package com.fsse2305.e_commerce_project.data.user.entity;

import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;
import jakarta.persistence.*;

@Entity
@Table(name = "user", indexes = @Index(name = "firebase_uid", columnList = "firebase_uid"))
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(nullable = false)
    private String email;

    @Column(name = "firebase_uid", nullable = false, unique = true)
    private String firebaseUid;

    public UserEntity() {
    }

    public UserEntity(FirebaseUserData firebaseUserData) {
        this.email = firebaseUserData.getEmail();
        this.firebaseUid = firebaseUserData.getFirebaseUid();
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
