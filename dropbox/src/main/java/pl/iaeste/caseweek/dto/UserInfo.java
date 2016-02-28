package pl.iaeste.caseweek.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfo {
    private String displayName;
    private String email;
}
