package com.docom.pingenerator.service;

import com.docom.pingenerator.dao.PinHistoryRepo;
import com.docom.pingenerator.entity.PinHistory;
import com.docom.pingenerator.model.PinRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PinGenerateService {
    @Autowired
    private PinHistoryRepo pinRepo;
    @Value("${pin.max.allowed}")
    private Integer maxAllowed;
    @Value("${pin.max.retries}")
    private Integer maxReries;
    @Value("${pin.expiry.time}")
    private Integer expiryTimeInMinutes;

    public Integer generatePin(String msisdn){
        Date currentDate = getCurrentDate();
        Date expiredDate = getExpiredDate();
        List<PinHistory> msisdnList = getActivePinListByMsisdn(msisdn,0,expiredDate,currentDate);
        System.out.println(msisdnList);
        int status = validateActivePin(msisdn, msisdnList);
        if (status != 0)
            return status;
        return Integer.parseInt(savePin(msisdn,currentDate).getPin());
    }

    private PinHistory savePin(String msisdn, Date currentDate) {
        String pin = generateRandomPin();
        PinHistory pinHistory = new PinHistory(pin,msisdn,currentDate,0,0);
        return pinRepo.save(pinHistory);
    }


    private int validateActivePin(String msisdn, List<PinHistory> msisdnList) {
        if(maxAllowed-1< msisdnList.size()){
            if(maxReries-1< msisdnList.get(0).getRetries()){
                return 1;
//                return Constants.MSISDN+" "+ msisdn+" "+ Constants.RETRIES_MESSAGE;
            }
            return 2;
//            return Constants.MSISDN+" "+ msisdn +" "+ Constants.ALREADY_HAS_3_NON_VALIDATED_PINS;
        }
        return 0;
    }

    private Date getCurrentDate() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date getExpiredDate(){
        LocalDateTime expireTime = LocalDateTime.now().minus(Duration.of(expiryTimeInMinutes, ChronoUnit.MINUTES));
        return Date.from(expireTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    private List<PinHistory> getActivePinListByMsisdn(String msisdn, int validated,
                                                      Date expiredDate, Date currentDate) {
        System.out.println("Discarded: "+pinRepo.discardPin(1,msisdn,expiredDate));
        return pinRepo.getPinDetailsByMSISDN(msisdn,validated,expiredDate,currentDate);
    }

    private String generateRandomPin() {
        Random r = new Random(System.currentTimeMillis());
        return Integer.valueOf((1 + r.nextInt(9)) * 1000 + r.nextInt(1000)).toString();
    }

    public Integer validate(PinRequest request) {
        Date currentDate = getCurrentDate();
        Date expiredDate = getExpiredDate();
        List<PinHistory> msisdnList = getActivePinListByMsisdn(request.getMsisdn(),0,expiredDate,currentDate);
        System.out.println(msisdnList);
        if(0<msisdnList.size()){
            return getResponse(request, currentDate, expiredDate, msisdnList);
        }
        return 5;
//        return Constants.PIN_NOT_FOUND;
    }

    private Integer getResponse(PinRequest request, Date currentDate, Date expiredDate, List<PinHistory> msisdnList) {
        if(maxReries-1< msisdnList.get(0).getRetries()){
            return 1;
//            return Constants.MSISDN+" "+ request.getMsisdn()+" "+ Constants.RETRIES_MESSAGE;
        }
        return validatePin(request, msisdnList, expiredDate, currentDate);

    }

    private Integer validatePin(PinRequest request, List<PinHistory> msisdnList, Date expiredDate, Date currentDate) {
        Optional<PinHistory> pin = msisdnList.stream()
                .filter(ele -> ele.getPin().equals(request.getPin()))
                .findAny();
        if(pin.isPresent()) {
            pinRepo.updatePinStatus(1,request.getMsisdn(),request.getPin());
            return 3;
//            return Constants.PIN+" "+ Constants.VALIDATED;
        }
        System.out.println("Retry: ");
        pinRepo.attempTry(request.getMsisdn(),expiredDate,currentDate);
        return 4;
//        return Constants.INVALID+" "+Constants.PIN;
    }
}
