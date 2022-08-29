package org.example.API.dto.getAllModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor
@Builder
public class Entity {

    public int id;
    public String title;
    public String description;
    public int cases_count;
    public String created;
    public String updated;
    public String created_at;
    public String updated_at;
}
