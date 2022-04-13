package ru.sold_out.serving_web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.sold_out.serving_web.entity.CatOwner;
import ru.sold_out.serving_web.service.CatOwnerService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/catOwners")
@RequiredArgsConstructor
public class CatOwnerController {
	private final CatOwnerService catOwnerService;

	@GetMapping()
	public String getAll(Map<String, Object> model) {
		List<CatOwner> catOwners = catOwnerService.findAll();
		model.put("catOwners", catOwners);
		return "catOwner/catOwners";
	}

	@PostMapping(value = "/add")
	public String add(Map<String, Object> model,
					  @RequestParam(required = false) String fullName,
					  @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth
	) {
		catOwnerService.save(fullName, dayOfBirth);
		List<CatOwner> catOwners = catOwnerService.findAll();
		model.put("catOwners", catOwners);
		return "catOwner/catOwners";
	}

	@PostMapping(value = "/delete")
	public String delete(Map<String, Object> model,
						 @RequestParam(required = false) Long id) {
		catOwnerService.deleteById(id);
		List<CatOwner> catOwners = catOwnerService.findAll();
		model.put("catOwners", catOwners);
		return "catOwner/catOwners";
	}

	@PostMapping(value = "/update")
	public String update(Map<String, Object> model,
						 @RequestParam(required = false) Long id,
						 @RequestParam(required = false) String fullName,
						 @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth
	) {
		catOwnerService.update(id, fullName, dayOfBirth);
		List<CatOwner> catOwners = catOwnerService.findAll();
		model.put("catOwners", catOwners);
		return "catOwner/catOwners";
	}

	@PostMapping(value = "/filterById")
	public String filterById(
			Map<String, Object> model,
			@RequestParam(required = false) Long id
	) {
		List<CatOwner> catOwners = (id == null) ?
				catOwnerService.findAll() :
				catOwnerService.findById(id).stream().toList();
		model.put("catOwners", catOwners);
		return "catOwner/catOwners";
	}

	@PostMapping(value = "/filterByName")
	public String filterByName(
			Map<String, Object> model,
			@RequestParam(required = false) String fullName
	) {
		List<CatOwner> catOwners = (fullName.isBlank()) ?
				catOwnerService.findAll() :
				catOwnerService.findByFullName(fullName).stream().toList();
		model.put("catOwners", catOwners);
		return "catOwner/catOwners";
	}

	@PostMapping(value = "/filterByDayOfBirth")
	public String filterByDayOfBirth(
			Map<String, Object> model,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dayOfBirth
	) {
		List<CatOwner> catOwners = (dayOfBirth == null) ?
				catOwnerService.findAll() :
				catOwnerService.findByDayOfBirth(dayOfBirth).stream().toList();
		model.put("catOwners", catOwners);
		return "catOwner/catOwners";
	}
}
