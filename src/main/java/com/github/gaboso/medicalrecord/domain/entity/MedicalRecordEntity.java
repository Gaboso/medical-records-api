package com.github.gaboso.medicalrecord.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_medical_record")
public class MedicalRecordEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "code_list_code", nullable = false)
    private String codeListCode;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "display_value", nullable = false)
    private String displayValue;

    @Column(name = "long_description")
    private String longDescription;

    @Column(name = "from_date", nullable = false)
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "sorting_priority")
    private Integer sortingPriority;

}
