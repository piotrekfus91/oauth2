package pl.iaeste.caseweek.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Entry {
    @JsonProperty(".tag")
    private String tag;
    private String name;
    @JsonProperty("path_lower")
    private String path;
}
