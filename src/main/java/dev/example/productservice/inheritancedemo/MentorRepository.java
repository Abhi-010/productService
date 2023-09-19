package dev.example.productservice.inheritancedemo;

import dev.example.productservice.inheritancedemo.singletable.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor,Long> {
    @Override
    <S extends Mentor> S save(S entity);
}
