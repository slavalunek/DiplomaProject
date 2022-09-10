package org.example.API.dto.getModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.API.dto.getAllModels.Entity;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder
public class ResponseEndpointGet {

    public boolean status;
    public Result result;

    public List<Entity> convertResultToEntity() {
        return List.of(Entity.builder()
                     .id(result.getId())
                     .title(result.getTitle())
                     .description(result.getDescription())
                     .casesCount(result.getCasesCount())
                     .created(result.getCreated())
                     .updated(result.getUpdated())
                     .createdAt(result.getCreatedAt())
                     .updatedAt(result.getUpdatedAt())
                     .build());
    }
}