package dev.example.productservice.inheritancedemo.jointable;

import dev.example.productservice.inheritancedemo.tableperclass.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="jt_student")
public class Student extends User {
    private double psp;
    private double attendance;

}
