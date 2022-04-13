package ru.sold_out.serving_web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sold_out.serving_web.entity.Cat;
import ru.sold_out.serving_web.entity.additional_info.CatColor;

import java.util.Date;
import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
	List<Cat> findByName(String name);
	List<Cat> findByDayOfBirth(Date dayOfBirth);
	List<Cat> findByBreed(String breed);
	List<Cat> findByColor(CatColor catColor);
	List<Cat> findByCatOwnerId(Long id);
}
