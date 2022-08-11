package com.dummy.ewalletservice.controller;

import com.dummy.ewalletservice.model.request.PostMoneyRequest;
import com.dummy.ewalletservice.model.request.patchdisable.PostDisableRequest;
import com.dummy.ewalletservice.model.response.ResponseData;
import com.dummy.ewalletservice.model.response.postinitialize.InitializeResponse;
import com.dummy.ewalletservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class WalletController {

    @Autowired
    private PostInitializeService postInitializeService;

    @Autowired
    private PostEnableWalletService postEnableWalletService;

    @Autowired
    private GetWalletBalanceService getWalletBalanceService;

    @Autowired
    private PostAddMoneyService postAddMoneyService;

    @Autowired
    private PostUseMoneyWalletService postUseMoneyWalletService;

    @Autowired
    private PatchDisableMyWalletService patchDisableMyWalletService;


    @PostMapping("/api/v1/init")
    public ResponseEntity<InitializeResponse> initializeAccount(@ModelAttribute(name = "customer_xid") String customer_xid) {
        InitializeResponse response = postInitializeService.initialize(customer_xid);

        if (Objects.nonNull(response.getData().getError())) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/wallet")
    public ResponseEntity<ResponseData> enableWallet(@RequestHeader(name = "Authorization") String auth) {
        ResponseData resp = postEnableWalletService.postEnableWallet(auth);
        if (Objects.nonNull(resp.getError())) {
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }


    @GetMapping("/api/v1/wallet")
    public ResponseEntity<ResponseData> getWalletBalance(@RequestHeader(name = "Authorization") String auth) {
        ResponseData resp = getWalletBalanceService.getBalance(auth);
        if (Objects.nonNull(resp.getError())) {
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/wallet/deposits")
    public ResponseEntity<ResponseData> postSaveMoney(@RequestHeader(name = "Authorization") String auth,
                                                      @ModelAttribute("amount") String amount,
                                                      @ModelAttribute("reference_id") String referenceId) {
        PostMoneyRequest request = PostMoneyRequest.builder()
                .amount(amount)
                .authToken(auth)
                .referenceId(referenceId)
                .build();

        ResponseData resp = postAddMoneyService.postAddMoney(request);
        if (Objects.nonNull(resp.getError())) {
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PostMapping("/api/v1/wallet/withdrawals")
    public ResponseEntity<ResponseData> postWithdrawMoney(@RequestHeader(name = "Authorization") String auth,
                                                          @ModelAttribute("amount") String amount,
                                                          @ModelAttribute("reference_id") String referenceId) {
        PostMoneyRequest request = PostMoneyRequest.builder()
                .amount(amount)
                .authToken(auth)
                .referenceId(referenceId)
                .build();

        ResponseData resp = postUseMoneyWalletService.postUseMoney(request);
        if (Objects.nonNull(resp.getError())) {
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @PatchMapping("/api/v1/wallet")
    public ResponseEntity<ResponseData> patchDisableWallet(@RequestHeader(name = "Authorization") String auth,
                                                          @ModelAttribute("is_disabled") Boolean isDisabled) {

        ResponseData resp = patchDisableMyWalletService.disableWallet(PostDisableRequest.builder()
                        .isDisabled(isDisabled)
                        .tokenAuth(auth)
                .build());

        if (Objects.nonNull(resp.getError())) {
            return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }
}
