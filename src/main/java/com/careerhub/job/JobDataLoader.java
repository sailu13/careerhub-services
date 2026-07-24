package com.careerhub.job;

import com.careerhub.job.entity.EmploymentType;
import com.careerhub.job.entity.Job;
import com.careerhub.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JobDataLoader implements CommandLineRunner {

    private final JobRepository jobRepository;

    @Override
    public void run(String... args){
        if (jobRepository.count() > 0) {
            return;
        }

        jobRepository.save(Job.builder()
                .title("Java Backend Developer")
                .company("Google")
                .location("Hyderabad")
                .experience("3-5 Years")
                .salary("18 LPA")
                .description("Develop scalable backend services using Spring Boot.")
                .skills(List.of("Java", "Spring Boot", "MySQL", "REST"))
                .employmentType(EmploymentType.FULL_TIME)
                .postedAt(LocalDateTime.now())
                .build());

        jobRepository.save(Job.builder()
                .title("Software Engineer")
                .company("Microsoft")
                .location("Bangalore")
                .experience("2-4 Years")
                .salary("22 LPA")
                .description("Develop enterprise applications.")
                .skills(List.of("Java", "Kafka", "Azure"))
                .employmentType(EmploymentType.FULL_TIME)
                .postedAt(LocalDateTime.now())
                .build());

        jobRepository.save(Job.builder()
                .title("Backend Engineer")
                .company("Amazon")
                .location("Chennai")
                .experience("3-6 Years")
                .salary("25 LPA")
                .description("Work on cloud native microservices.")
                .skills(List.of("Java", "Spring Cloud", "AWS"))
                .employmentType(EmploymentType.FULL_TIME)
                .postedAt(LocalDateTime.now())
                .build());

        jobRepository.save(Job.builder()
                .title("Java Developer")
                .company("Infosys")
                .location("Pune")
                .experience("2-3 Years")
                .salary("10 LPA")
                .description("Build REST APIs.")
                .skills(List.of("Java", "Spring Boot"))
                .employmentType(EmploymentType.FULL_TIME)
                .postedAt(LocalDateTime.now())
                .build());

        jobRepository.save(Job.builder()
                .title("Spring Boot Developer")
                .company("TCS")
                .location("Hyderabad")
                .experience("3 Years")
                .salary("12 LPA")
                .description("Backend API Development.")
                .skills(List.of("Spring Boot", "PostgreSQL"))
                .employmentType(EmploymentType.FULL_TIME)
                .postedAt(LocalDateTime.now())
                .build());

    }
}
