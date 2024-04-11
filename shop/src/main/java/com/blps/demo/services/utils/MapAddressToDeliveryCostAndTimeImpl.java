package com.blps.demo.services.utils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MapAddressToDeliveryCostAndTimeImpl implements MapAddressToDeliveryCostAndTime{
    @Override
    public Map<String, List<Integer>> getMapAddressToDeliveryCostAndTime() {
        return Map.ofEntries(
                Map.entry("Good street", List.of(20000, 2)),
                Map.entry("Victory street", List.of(30000, 3)),
                Map.entry("Lenina street", List.of(40000, 4))
        );
    }
}
