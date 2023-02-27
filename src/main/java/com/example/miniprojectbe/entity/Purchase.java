package com.example.miniprojectbe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@IdClass(PurchaseId.class)
@Table(name = "PURCHASE")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchaseId")
    private Long purchaseId;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    @Column(name="purchase_date")
    private LocalDateTime purchase_date;

    @Column(name="status")
    private String status;

    public void softDelete(){
        this.purchase_date = LocalDateTime.now();
        this.status="신청취소";
    }

    public void updateStatus(){
        this.purchase_date = LocalDateTime.now();
        this.status="신청완료";
    }
}
