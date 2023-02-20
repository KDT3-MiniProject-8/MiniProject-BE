package com.example.miniprojectbe.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name="jwt_blacklist")
public class Blacklist {

    @Id
    @Column(name="token", nullable = false)
    private String token;

    @Column(name="create_date")
    private LocalDateTime createDate;
}
