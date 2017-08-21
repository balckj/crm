package com.yidatec.service;

import com.yidatec.mapper.SequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by qsw on 17-8-16.
 */
@Service
public class SequenceService {
    @Autowired
    SequenceMapper sequenceMapper;

    public String generateProjectSequence(){
        LocalDate currentMonth = LocalDate.now();
        String ym = currentMonth.format(DateTimeFormatter.ofPattern("yyyyMM"));
        int a = sequenceMapper.generateSequence(ym);
        String sequence = "";
        if(a<10){
            sequence = "00"+a;
        }else if(a < 100){
            sequence = "0"+a;
        }else if(a < 1000){
            sequence = ""+a;
        }
        else{
            sequence = ""+a;
        }
        return ym+"-"+sequence;
    }

    public String generateContractSequence(String type,String projectCode){
        int a = sequenceMapper.generateSequence(projectCode);
        String sequence = "";
        if(a<10){
            sequence = "0"+a;
        }else if(a < 100){
            sequence = ""+a;
        }
        else{
            sequence = ""+a;
        }
        return type+"-"+projectCode +"-"+sequence;
    }
}
