package com.koev.jsonprocessingcardealer.configuration;

import com.google.gson.*;
import com.koev.jsonprocessingcardealer.util.*;
import com.koev.jsonprocessingcardealer.util.tobedeleted.XmlParser;
import com.koev.jsonprocessingcardealer.util.tobedeleted.XmlParserImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Random;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .setPrettyPrinting()
                .create();

    }

    @Bean
    public FileUtil fileUtil(){
        return new FileUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Validator validator(){
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidatorUtilImpl(validator());
    }

    @Bean
    public Random random(){
        return new Random();
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }


}
