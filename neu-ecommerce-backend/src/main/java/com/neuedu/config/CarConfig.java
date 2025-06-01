package com.neuedu.config;

import com.neuedu.pojo.Car;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CarConfig {

    public static Map<Integer, Car> CAR_MAP = new HashMap<>();
}