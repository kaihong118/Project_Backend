package com.fsse2305.e_commerce_project.api;

import com.fsse2305.e_commerce_project.data.transaction.domainObject.TransactionDetailData;
import com.fsse2305.e_commerce_project.data.transaction.dto.TransactionDetailDto;
import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;
import com.fsse2305.e_commerce_project.service.TransactionService;
import com.fsse2305.e_commerce_project.uility.JwtUtil;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionApi {
    private final TransactionService transactionService;

    public TransactionApi(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionDetailDto createTransaction(JwtAuthenticationToken jwtToken) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        TransactionDetailData transactionDetailData = transactionService.createTransaction(firebaseUserData);
        return new TransactionDetailDto(transactionDetailData);
    }
}
