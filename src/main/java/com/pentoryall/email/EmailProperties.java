package com.pentoryall.email;

/* @ConfigurationProperties 사용하기 위해 의존성 추가해주자.

   annotationProcessor "org.springframework.boot:spring-boot-configuration-processor"

*/

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "email") // 설정 파일에서 email: 로 시작하는 properties
@Configuration
public class EmailProperties {
    private String name;
    private String link;
    private Long validTime;
}
