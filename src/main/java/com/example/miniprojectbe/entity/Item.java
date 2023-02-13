package com.example.miniprojectbe.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Table(name = "ITEM")
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemId", nullable = false)
    private Long itemId;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "bank", nullable = false)
    private String bank;

    @Column(name = "itemName", nullable = false)
    private String itemName;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "join", nullable = false)
    private String join;

    @Column(name = "limit")
    private int limit;

    @Column(name = "preference")
    private String preference;

    @Column(name = "target")
    private String target;

    @OneToMany(mappedBy = "item")
    private List<Basket> basketList;
}
