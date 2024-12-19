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
@Table(name = "conditions")
public class Condition {
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
    @Column(name = "code")
    private String code;

    @Size(max = 255)
    @Column(name = "body_site")
    private String bodySite;

    @Size(max = 255)
    @Column(name = "severity")
    private String severity;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "onset_date")
    private LocalDate onsetDate;

    @Column(name = "abatement_date")
    private LocalDate abatementDate;

    @Column(name = "recorded_date")
    private LocalDate recordedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recorder")
    private Doctor recorder;

}