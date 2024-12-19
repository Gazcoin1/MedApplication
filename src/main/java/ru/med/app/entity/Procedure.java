package ru.med.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "procedures")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient", nullable = false)
    private User patient;

    @Size(max = 255)
    @Column(name = "category")
    private String category;

    @Size(max = 255)
    @Column(name = "code")
    private String code;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recorder")
    private Doctor recorder;

    @Column(name = "performed_date")
    private LocalDate performedDate;

    @Size(max = 255)
    @Column(name = "body_site")
    private String bodySite;

    @Column(name = "outcome", length = Integer.MAX_VALUE)
    private String outcome;

    @Column(name = "report", length = Integer.MAX_VALUE)
    private String report;

}