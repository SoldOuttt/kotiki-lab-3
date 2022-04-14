package ru.sold_out.serving_web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sold_out.serving_web.entity.CatOwner;
import ru.sold_out.serving_web.repository.CatOwnerRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatOwnerService {
	private final CatOwnerRepository catOwnerRepository;

	public void save(String fullName, Date dayOfBirth) {
		if (fullName.isBlank() || dayOfBirth == null) {
			return;
		}
		CatOwner catOwner = new CatOwner(fullName, dayOfBirth);
		catOwnerRepository.save(catOwner);
	}

	public void deleteById(Long id) {
		if (id == null) {
			return;
		}
		try {
			catOwnerRepository.deleteById(id);
		} catch (Exception exception) {
			System.out.println(exception.getStackTrace());
			System.out.println(exception.getMessage());
		}
	}

	public void update(Long id, String fullName, Date dayOfBirth) {
		if (id == null
				|| fullName.isBlank()
				|| dayOfBirth == null
				|| findById(id).isEmpty()
		) {
			return;
		}
		CatOwner catOwner = new CatOwner(id, fullName, dayOfBirth);
		catOwnerRepository.save(catOwner);
	}

	public List<CatOwner> findAll() {
		return catOwnerRepository.findAll();
	}

	public Optional<CatOwner> findById(Long id) {
		return catOwnerRepository.findById(id);
	}

	public List<CatOwner> findByFullName(String fullName) {
		return catOwnerRepository.findByFullName(fullName);
	}

	public List<CatOwner> findByDayOfBirth(Date dayOfBirth) {
		return catOwnerRepository.findByDayOfBirth(dayOfBirth);
	}
}
