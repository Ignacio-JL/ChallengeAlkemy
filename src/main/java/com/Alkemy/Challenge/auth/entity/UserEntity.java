package com.Alkemy.Challenge.auth.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "user", 
	uniqueConstraints = { @UniqueConstraint(columnNames = {"email"})}
)
@Setter
@Getter
public class UserEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	@NonNull
    @NotEmpty(message = "the name can't be null")
    @NotBlank(message = "the name can't  be blank")
    @Column(name = "first_name", nullable = false, updatable = false)
    private String firstName;
    @NonNull
    @NotEmpty(message = "the lastName can't be null")
    @NotBlank(message = "the lastName can't  be blank")
    @Column(name = "last_name", nullable = false, updatable = false)
    private String lastName;
    @NonNull
    @Email(message = "enter a correct email")
    @Column(nullable = false, updatable = false, unique = true)
    private String email;
	@Column
	@Size(min = 8, message = "La contraseña debe contener al menos 8 caracteres")
	private String password;
	
	@Column(nullable = false)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "User_id", referencedColumnName = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "Role_id", referencedColumnName = "role_id")
			)
	private Set<RoleEntity> roles = new HashSet<>();
	
	private boolean accountNonExpired;
	
	private boolean accountNonLocked;
	
	private boolean credentialsNonExpired;
	
	private boolean enabled;
	
	public UserEntity() {
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
	}
}
