package com.example.ekwateurtest.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnergyResource {
    Integer gas;
    Integer electricity;
}
