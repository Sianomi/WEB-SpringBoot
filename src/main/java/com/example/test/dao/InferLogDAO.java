package com.example.test.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity                                                                     // DB임을 선언. JPA를 쓰기위한 Annotation
@NoArgsConstructor
@Getter
@Table(name="inferlog")
public class InferLogDAO {                                                  // InferLogDAO Class. JPA를 통해 DB 1:1 연결

    @Id                                                                     // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY)                     // 자동 생성. IDENTITY Type.
    private Long code;                                                      // Primary Key 변수

    private String eID;                                                     // Email ID
    private String bucketname;                                              // S3 Bucket Name
    private String inferimagepath;                                          // 추론 이미지 S3 Key Path
    private String originalimagepath;                                       // 원본 이미지 S3 Key Path
    private String result;                                                  // JSON Result
    private String usedservice;                                             // 추론 Service Name

    @Column(nullable = false, updatable = false)                            // NULL 불가, 갱신 불가
    @CreationTimestamp                                                      // TimeStamp임을 선언하는 Annotation. Create Auto Timestamp.
    private LocalDateTime timestamp;                                        // TimeStamp 변수

    @Builder                                                                // InferLogDAO 변수를 생성하기 위한 Builder
    public InferLogDAO(String eID, String bucketname, String inferimagepath, String originalimagepath, String result, String usedservice) {
        this.eID = eID;
        this.bucketname = bucketname;
        this.inferimagepath = inferimagepath;
        this.originalimagepath = originalimagepath;
        this.result = result;
        this.usedservice = usedservice;
    }
}
