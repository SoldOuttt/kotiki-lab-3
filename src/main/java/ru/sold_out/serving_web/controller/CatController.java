package ru.sold_out.serving_web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sold_out.serving_web.entity.Cat;
import ru.sold_out.serving_web.service.CatService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/cats")
@RequiredArgsConstructor
public class CatController {
	private final CatService catService;

	@GetMapping()
	public String getAll(Map<String, Object> model) {
		List<Cat> cats = catService.findAll();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/add")
	public String add(Map<String, Object> model,
					  @RequestParam(required = false) String name,
					  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth,
					  @RequestParam(required = false) String breed,
					  @RequestParam(required = false) String color,
					  @RequestParam(required = false) Long ownerId
	) {
		catService.save(name, dayOfBirth, breed, color, ownerId);
		List<Cat> cats = catService.findAll();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/filterById")
	public String filterById(
			Map<String, Object> model,
			@RequestParam(required = false) Long id
	) {
		List<Cat> cats = (id == null) ?
				catService.findAll() :
				catService.findById(id).stream().toList();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/filterByName")
	public String filterByName(
			Map<String, Object> model,
			@RequestParam(required = false) String name
	) {
		List<Cat> cats = (name.isBlank()) ?
				catService.findAll() :
				catService.findByName(name).stream().toList();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/filterByDayOfBirth")
	public String filterByDayOfBirth(
			Map<String, Object> model,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth
	) {
		List<Cat> cats = (dayOfBirth == null) ?
				catService.findAll() :
				catService.findByDayOfBirth(dayOfBirth).stream().toList();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/filterByBreed")
	public String filterByBreed(
			Map<String, Object> model,
			@RequestParam(required = false) String breed
	) {
		List<Cat> cats = (breed.isBlank()) ?
				catService.findAll() :
				catService.findByBreed(breed).stream().toList();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/filterByColor")
	public String filterByColor(
			Map<String, Object> model,
			@RequestParam(required = false) String color
	) {
		List<Cat> cats = (color.isBlank()) ?
				catService.findAll() :
				catService.findByColor(color).stream().toList();
		model.put("cats", cats);
		return "cat/cats";
	}

	@PostMapping(value = "/filterByOwnerId")
	public String filterByOwnerId(
			Map<String, Object> model,
			@RequestParam(required = false) Long ownerId
	) {
		List<Cat> cats = (ownerId == null) ?
				catService.findAll() :
				catService.findByOwnerId(ownerId).stream().toList();
		model.put("cats", cats);
		return "cat/cats";
	}
}
