package com.example.kp_assignment.service;

import com.example.kp_assignment.common.Constant;
import com.example.kp_assignment.dto.SpreadDto;
import com.example.kp_assignment.entity.Receive;
import com.example.kp_assignment.entity.Spread;
import com.example.kp_assignment.repository.ReceiveRepository;
import com.example.kp_assignment.repository.SpreadRepository;
import com.example.kp_assignment.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScatterService {

    @Autowired
    SpreadRepository spreadRepository;

    @Autowired
    ReceiveRepository receiveRepository;

    @Transactional
    public String spreadMoney(Long user, String roomId, SpreadDto spreadDto) {
        LocalDateTime dateTime = LocalDateTime.now();
        String token = checkDuplicateToken();
        Spread spreadEntity = Spread.builder()
                .userId(user)
                .amount(spreadDto.getAmount())
                .peopleNumber(spreadDto.getPeopleNumber())
                .roomId(roomId)
                .token(token)
                .createdTime(dateTime)
                .build();

        spreadRepository.save(spreadEntity);
        divideMoney(user, roomId, spreadDto, token, dateTime);

        return token;
    }

    private String checkDuplicateToken() {
        String token = Util.createToken();
        String result = spreadRepository.findByToken(token);
        if (result != null) {
            int i = 0;
            while (Constant.TOKEN_MAKE_TRY_COUNT < i) {
                if (i > 10) {
                    new Throwable();
                }
                token = Util.createToken();
                result = spreadRepository.findByToken(token);
                if (result != null) {
                    i++;
                    continue;
                } else {
                    break;
                }
            }
        }
        return token;
    }

    private void divideMoney(Long user, String roomId, SpreadDto spreadDto, String token, LocalDateTime localDateTime) {
        List<Receive> receiveList = new ArrayList<>();

        long amount = spreadDto.getAmount();
        int peopleNumber = spreadDto.getPeopleNumber();

        long divideMoney = amount / peopleNumber;
        long leftMoney = amount - divideMoney * peopleNumber - 1;

        for (int i = 0; i < peopleNumber; i++) {
            if (peopleNumber == i) {
                divideMoney = leftMoney;
            }
            Receive receive = Receive.builder()
                    .spreadId(user).roomId(roomId).receivedAmount(divideMoney)
                    .token(token).createdTime(localDateTime).build();
            receiveList.add(receive);

            System.out.println(divideMoney);
        }
        receiveRepository.saveAll(receiveList);
    }
}
