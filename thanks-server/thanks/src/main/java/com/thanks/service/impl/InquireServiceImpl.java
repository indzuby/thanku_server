package com.thanks.service.impl;

import com.thanks.model.Inquire;
import com.thanks.repository.InquireRepository;
import com.thanks.service.InquireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zuby on 2016. 7. 23..
 */
@Service
public class InquireServiceImpl implements   InquireService{

    @Autowired
    InquireRepository inquireRepository;

    @Override
    public List<Inquire> findAll() {
        return inquireRepository.findAll();
    }

    @Override
    public Inquire find(Long key) {
        return inquireRepository.findOne(key);
    }

    @Override
    public Inquire add(Inquire data) {
        return inquireRepository.saveAndFlush(data);
    }

    @Override
    public Inquire update(Long key, Inquire data) {
        return null;
    }

    @Override
    public void remove(Long key) {
    }
}
