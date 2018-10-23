package com.tl.apollo.demo;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestDataSource {

    private String url;
    private String name;
    private String password;
}
