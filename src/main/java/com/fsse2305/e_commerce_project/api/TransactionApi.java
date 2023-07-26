package com.fsse2305.e_commerce_project.api;

import com.fsse2305.e_commerce_project.data.transaction.domainObject.TransactionDetailData;
import com.fsse2305.e_commerce_project.data.transaction.dto.TransactionDetailDto;
import com.fsse2305.e_commerce_project.data.transaction.dto.TransactionStatusDto;
import com.fsse2305.e_commerce_project.data.user.domainObject.FirebaseUserData;
import com.fsse2305.e_commerce_project.service.TransactionService;
import com.fsse2305.e_commerce_project.uility.JwtUtil;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:5173/")
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

    @GetMapping("/{tid}")
    public TransactionDetailDto getTransactionByTid(JwtAuthenticationToken jwtToken,
                                                    @PathVariable(value = "tid") Integer tid) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        return new TransactionDetailDto(transactionService.getTransactionByTid(firebaseUserData, tid));
    }

    @PatchMapping("/{tid}/pay")
    public TransactionStatusDto updateTransactionStatusByTid(JwtAuthenticationToken jwtToken,
                                                             @PathVariable(value = "tid") Integer tid) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        transactionService.updateTransactionStatusByTid(firebaseUserData, tid);
        return new TransactionStatusDto();
    }

    @PatchMapping("/{tid}/finish")
    public TransactionDetailDto completeTransactionByTid(JwtAuthenticationToken jwtToken,
                                                             @PathVariable(value = "tid") Integer tid) {
        FirebaseUserData firebaseUserData = JwtUtil.getFirebaseUserData(jwtToken);
        TransactionDetailData transactionDetailData = transactionService.completeTransactionByTid(firebaseUserData, tid);
        return new TransactionDetailDto(transactionDetailData);
    }
}
