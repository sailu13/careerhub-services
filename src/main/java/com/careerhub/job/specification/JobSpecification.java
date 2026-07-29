package com.careerhub.job.specification;

import com.careerhub.job.entity.EmploymentType;
import com.careerhub.job.entity.Job;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {

    public static Specification<Job> hasSearch(String search) {
        return (root, query, cb) -> {
            if (search == null || search.isBlank()) {
                return cb.conjunction();
            }
            String keyword = "%" + search.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("title")), keyword),
                    cb.like(cb.lower(root.get("company")), keyword)
            );
        };
    }

    public static Specification<Job> hasLocation(String location) {
        return (root, query, cb) -> {
            if (location == null || location.isBlank()) {
                return cb.conjunction();
            }
            return cb.equal(root.get("location"), location);
        };
    }

    public static Specification<Job> hasEmploymentType(EmploymentType employmentType) {
        return (root, query, cb) -> {
            if (employmentType == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("employmentType"), employmentType);
        };
    }
}