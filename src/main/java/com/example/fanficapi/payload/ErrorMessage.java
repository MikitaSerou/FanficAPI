package com.example.fanficapi.payload;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Data
public class ErrorMessage {

    @NonNull
    Integer value;
    @NonNull
    Date date;
    @NonNull
    String message;
    @NonNull
    String description;
}
