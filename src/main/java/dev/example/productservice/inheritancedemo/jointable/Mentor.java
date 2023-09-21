package dev.example.productservice.inheritancedemo.jointable;

import dev.example.productservice.inheritancedemo.tableperclass.User;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_mentor")
@PrimaryKeyJoinColumn(name = "User_id")
public class Mentor extends User {
    private double averageRating;
}
