package pl.iaeste.caseweek.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntryRequest {
    private String path;
}
