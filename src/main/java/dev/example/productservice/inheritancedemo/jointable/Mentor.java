package dev.example.productservice.inheritancedemo.jointable;

import dev.example.productservice.inheritancedemo.tableperclass.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_mentor")
public class Mentor extends User {
    private double averageRating;
}
