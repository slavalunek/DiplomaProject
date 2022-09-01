package org.example.API.dto.getAllModels;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    public Date created_at;
    public Date updated_at;
}
