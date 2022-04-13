package ru.sold_out.serving_web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sold_out.serving_web.entity.Cat;
import ru.sold_out.serving_web.entity.CatOwner;
import ru.sold_out.serving_web.entity.additional_info.CatColor;
import ru.sold_out.serving_web.repository.CatOwnerRepository;
import ru.sold_out.serving_web.repository.CatRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatService {
	private final CatRepository catRepository;
	private final CatOwnerRepository catOwnerRepository;

	public void save(String name, Date dayOfBirth, String breed, String color, Long ownerId) {
		CatColor catColor;
		try {
			catColor = CatColor.valueOf(color.toUpperCase());
		} catch (IllegalArgumentException illegalArgumentException) {
			return;
		}
		Optional<CatOwner> catOwner = catOwnerRepository.findById(ownerId);
		if (name.isBlank()
				|| dayOfBirth == null
				|| breed.isBlank()
				|| catOwner.isEmpty()) {
			return;
		}
		Cat cat = new Cat(name, dayOfBirth, breed, catColor, catOwner.get());
		catRepository.save(cat);
	}

	public List<Cat> findAll() {
		return catRepository.findAll();
	}

	public Optional<Cat> findById(Long id) {
		return catRepository.findById(id);
	}

	public List<Cat> findByName(String name) {
		return catRepository.findByName(name);
	}

	public List<Cat> findByDayOfBirth(Date dayOfBirth) {
		return catRepository.findByDayOfBirth(dayOfBirth);
	}

	public List<Cat> findByBreed(String breed) {
		return catRepository.findByBreed(breed);
	}

	public List<Cat> findByColor(String color) {
		CatColor catColor;
		try {
			catColor = CatColor.valueOf(color.toUpperCase());
		} catch (IllegalArgumentException illegalArgumentException) {
			return Collections.emptyList();
		}
		return catRepository.findByColor(catColor);
	}

	public List<Cat> findByOwnerId(Long ownerId) {
		return catRepository.findByCatOwnerId(ownerId);
	}
}
