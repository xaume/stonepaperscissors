package com.example.stonepaperscissors.dataaccess.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.stonepaperscissors.common.Result;
import com.example.stonepaperscissors.common.Shape;

import lombok.Data;

@Data
@Entity
@Table(name = "gameresult")
public class GameResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Shape playerShape;

    @Column
    @Enumerated(EnumType.STRING)
    private Shape computerShape;

    @Column
    @Enumerated(EnumType.STRING)
    private Result result;

}
