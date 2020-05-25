package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Weather {

    private long id;
    private String main;
    private String description;
    private String icon;


}
