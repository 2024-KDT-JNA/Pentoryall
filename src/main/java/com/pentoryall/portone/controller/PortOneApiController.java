//package com.pentoryall.portone.controller;
//
//import com.pentoryall.common.dtos.CommonResponse;
//import com.pentoryall.portone.service.PortOneService;
//import com.pentoryall.settlement.dtos.UserSettlementDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.net.URISyntaxException;
//
//@RestController
//@RequestMapping("/api/portone")
//@RequiredArgsConstructor
//public class PortOneApiController {
//
//    private final PortOneService portOneService;
//
//    @RequestMapping("/vbank")
//    public ResponseEntity<CommonResponse> isAccountValid(@RequestBody UserSettlementDTO userInfo) throws URISyntaxException {
//        return ResponseEntity.ok(portOneService.getAccountHolder(userInfo.getBankCode(), userInfo.getAccountNumber()));
//    }
//}