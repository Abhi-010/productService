package dev.example.productservice.inheritancedemo.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor,Long> {

    <S extends Mentor> S save(Mentor mentor);
}
