package com.thanks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by micky on 2016. 8. 17..
 */
@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private Double lat;
    private Double lon;
}
