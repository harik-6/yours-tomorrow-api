package com.example.demo.services;

import com.example.demo.models.Option;
import com.example.demo.repositories.OptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService {
    @Autowired
    private OptionsRepository optionRepository;

    public Option getOption(String optionId) {
        return optionRepository.findById(optionId).orElse(null);
    }

}
