package ru.sold_out.serving_web.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;
import ru.sold_out.serving_web.entity.additional_info.CatColor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@ToString(of = {"id", "name", "dayOfBirth", "breed", "color"})
@DynamicUpdate
public class Cat implements Serializable {
	@Serial
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dayOfBirth;

	@Column(nullable = false)
	private String breed;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CatColor color;

	@ManyToMany(
			fetch = FetchType.LAZY,
			cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
	)
	@JoinTable(name = "cat_friends",
			joinColumns = @JoinColumn(name = "cat_id"),
			inverseJoinColumns = @JoinColumn(name = "friend_id")
	)
	private Set<Cat> friends;

	@ManyToMany(
			fetch = FetchType.LAZY,
			cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
	)
	@JoinTable(name = "cat_friends",
			joinColumns = @JoinColumn(name = "friend_id"),
			inverseJoinColumns = @JoinColumn(name = "cat_id")
	)
	private Set<Cat> friendOf;

	@ManyToOne(fetch = FetchType.LAZY) // спросить почему не работает
	private CatOwner catOwner;

	public Cat(String name, Date dayOfBirth, String breed, CatColor color, CatOwner catOwner) {
		this.name = name;
		this.dayOfBirth = dayOfBirth;
		this.breed = breed;
		this.color = color;
		this.catOwner = catOwner;
	}

	public Cat(Long id, String name, Date dayOfBirth, String breed, CatColor color, CatOwner catOwner) {
		this.id = id;
		this.name = name;
		this.dayOfBirth = dayOfBirth;
		this.breed = breed;
		this.color = color;
		this.catOwner = catOwner;
	}
}
