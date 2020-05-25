package model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wind {

    private double speed;
    private double deg;

    @Override
    public String toString() {
        return "speed=" + speed +
                ", deg=" + deg ;
    }
}
