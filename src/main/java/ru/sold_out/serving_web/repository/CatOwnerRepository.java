package ru.sold_out.serving_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sold_out.serving_web.entity.CatOwner;

import java.util.Date;
import java.util.List;

@Repository
public interface CatOwnerRepository extends JpaRepository<CatOwner, Long> {
	List<CatOwner> findByFullName(String fullName);
	List<CatOwner> findByDayOfBirth(Date dayOfBirth);
}