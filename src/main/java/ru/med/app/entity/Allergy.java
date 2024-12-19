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
@Table(name = "allergies")
public class Allergy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient", nullable = false)
    private User patient;

    @Size(max = 255)
    @Column(name = "clinical_status")
    private String clinicalStatus;

    @Size(max = 255)
    @Column(name = "type")
    private String type; // типа аллергия или

    @Size(max = 255)
    @Column(name = "category")
    private String category;

    @Size(max = 255)
    @Column(name = "code")
    private String code;

    @Size(max = 50)
    @Column(name = "criticality", length = 50)
    private String criticality;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "onset_date")
    private LocalDate onsetDate;

    @Column(name = "recorded_date")
    private LocalDate recordedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recorder")
    private Doctor recorder;

    @Column(name = "last_occurrence_date")
    private LocalDate lastOccurrenceDate;

    @Size(max = 255)
    @Column(name = "reaction_severity")
    private String reactionSeverity;

    @Column(name = "reaction_description", length = Integer.MAX_VALUE)
    private String reactionDescription;

}