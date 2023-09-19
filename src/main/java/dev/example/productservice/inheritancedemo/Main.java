package dev.example.productservice.inheritancedemo;

import dev.example.productservice.inheritancedemo.singletable.Mentor;
import dev.example.productservice.inheritancedemo.singletable.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private MentorRepository mentorRepository;
    public Main(MentorRepository mentorRepository){
        this.mentorRepository = mentorRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        Mentor mentor = new Mentor();
        mentor.setName("Naman");
        mentor.setEmail("abc@gmail.com");
        mentor.setAverageRating(4.65);
        mentorRepository.save(mentor);

    }
}
