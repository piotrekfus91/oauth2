package pl.iaeste.caseweek.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EntryResponse {
    private List<Entry> entries;
}
