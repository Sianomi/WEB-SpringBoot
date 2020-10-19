package com.example.test.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name="inferlog")
public class InferLogDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String eID;
    private String bucketname;
    private String inferimagepath;
    private String originalimagepath;
    private String result;
    private String usedservice;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime timestamp;

    @Builder
    public InferLogDAO(String eID, String bucketname, String inferimagepath, String originalimagepath, String result, String usedservice) {
        this.eID = eID;
        this.bucketname = bucketname;
        this.inferimagepath = inferimagepath;
        this.originalimagepath = originalimagepath;
        this.result = result;
        this.usedservice = usedservice;
    }
}
