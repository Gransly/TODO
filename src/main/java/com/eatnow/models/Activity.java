package com.eatnow.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "activity")
public class Activity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String activity;

    private String accessibility;

    @NotBlank
    private String type;

    private String participants;

    private String price;

    @Enumerated(EnumType.STRING)
    private ActivityStatus activityStatus;

}
