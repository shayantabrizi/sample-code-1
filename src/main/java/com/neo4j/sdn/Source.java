package com.neo4j.sdn;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Source {
    String userId;
    String userName;
    Long size;
}
