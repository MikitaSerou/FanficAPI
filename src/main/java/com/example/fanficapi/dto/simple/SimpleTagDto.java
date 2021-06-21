package com.example.fanficapi.dto.simple;

import com.example.fanficapi.model.Publication;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleTagDto {

    private Long id;

    private  String name;
}
