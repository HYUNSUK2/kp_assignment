package com.example.kp_assignment.service;

import com.example.kp_assignment.common.GlobalException;
import com.example.kp_assignment.dto.ReceiveDto;
import com.example.kp_assignment.dto.SearchDto;
import com.example.kp_assignment.entity.Receive;
import com.example.kp_assignment.entity.Spread;
import com.example.kp_assignment.repository.ReceiveRepository;
import com.example.kp_assignment.repository.SpreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class ReceiveService {

    @Autowired
    ReceiveRepository receiveRepository;

    @Autowired
    SpreadRepository spreadRepository;

    public long receiveMoney(Long userId, String roomId, String token) {
        List<Receive> receives = receiveRepository.findByToken(token);
        checkReceiveMoney(receives, userId);

        Receive receive = receives.get(0);
        receive.setReceivedTime(LocalDateTime.now());
        receive.setRoomId(roomId);
        receive.setReceiveId(userId);
        receiveRepository.save(receive);

        return receive.getReceivedAmount();
    }

    public SearchDto searchHistory(Long userId, String token){
        Long amount;
        Long calAmount;
        LocalDateTime localDateTime;

        Spread spread = spreadRepository.findByToken(token);
        checkSearchReceive(spread, userId);
        localDateTime = spread.getCreatedTime();
        amount = spread.getAmount();
        calAmount = amount;

        List<Receive> receives = receiveRepository.findByTokenAndSpreadId(token, userId);
        List<ReceiveDto> receiveDtos = new ArrayList<>();

        for( Receive receive : receives){
            ReceiveDto receiveDto = ReceiveDto.builder()
                                        .receivedAmount(receive.getReceivedAmount())
                                        .receivedId(receive.getReceiveId()).build();
            receiveDtos.add(receiveDto);
            calAmount -= receive.getReceivedAmount();
        }

        SearchDto searchDto = SearchDto.builder()
                                .amount(amount)
                                .createdTime(localDateTime)
                                .totalReceivedAmount(calAmount)
                                .receiveId(receiveDtos).build();

        return searchDto;
    }

    private void checkReceiveMoney(List<Receive> receives, Long userId){
        if (receives.stream().anyMatch(item -> item.getCreatedTime().isBefore(LocalDateTime.now().minusMinutes(10)))) {
            throw new GlobalException("timeout seed money :: 400");
        } else if (receives.stream().anyMatch(item -> item.getReceiveId() == userId)) {
            throw new GlobalException("can't get money again :: 400");
        } else if (receives.stream().filter(item -> Objects.isNull(item.getReceiveId()))
                .count() == 0) {
            throw new GlobalException("money is gone :: 400");
        } else if (receives.stream().anyMatch(item -> item.getSpreadId() == userId))
            throw new GlobalException("offer can't get money :: 400");
    }

    private void checkSearchReceive(Spread spread, Long userId){
        if (Objects.isNull(spread)) {
            throw new GlobalException("wrong token :: 400");
        }

        if (spread.getUserId() != userId) {
            throw new GlobalException("can't search others :: 400");
        }

        if (spread.getCreatedTime().isBefore(LocalDateTime.now().minusDays(7))) {
            throw new GlobalException("expire date to search :: 400");
        }

    }
}
