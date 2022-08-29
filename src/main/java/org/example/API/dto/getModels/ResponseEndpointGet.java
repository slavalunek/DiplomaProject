package org.example.API.dto.getModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder
public class ResponseEndpointGet {

    public boolean status;
    public Result result;
}