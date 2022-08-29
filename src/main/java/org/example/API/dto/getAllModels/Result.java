package org.example.API.dto.getAllModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder
public class Result {

    public int total;
    public int filtered;
    public int count;
    public List<Entity> entities;
}
