package com.example.capstone.entity.interior;

import com.example.capstone.converter.StringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class InteriorStyle {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer styleId;

    @Column
    private String style;

    @Column(columnDefinition = "Text")
    @Convert(converter = StringListConverter.class)
    private List<String> about;

    @Column(columnDefinition = "Text")
    @Convert(converter = StringListConverter.class)
    private List<String> tips;

    @Column(columnDefinition = "Text")
    @Convert(converter = StringListConverter.class)
    private List<String> color;

    @Column(columnDefinition = "Text")
    @Convert(converter = StringListConverter.class)
    private List<String> interiorImage;
}
