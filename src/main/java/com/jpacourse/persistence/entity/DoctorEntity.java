package com.jpacourse.persistence.entity;
import com.jpacourse.persistence.enums.Specialization;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DOCTOR")
public class DoctorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 255)
	private String firstName;

	@Column(nullable = false, length = 255)
	private String lastName;

	@Column(nullable = false, length = 255)
	private String telephoneNumber;

	@Column(nullable = false, unique = true, length = 255)
	private String email;

	@Column(nullable = false, unique = true, length = 255)
	private String doctorNumber;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Specialization specialization;

	@JoinColumn(name = "DOCTOR_ID")
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	private List<VisitEntity> visits;

	@ManyToMany
	@JoinTable(
			name = "DOCTOR_TO_ADDRESS",
			joinColumns = @JoinColumn(name = "DOCTOR_ID"),
			inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID")
	)
	private List<AddressEntity> addresses;

	@Version
	private int version;


	// Setters and Getters:

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(String doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public List<VisitEntity> getVisits() {
		return visits;
	}

	public void setVisits(List<VisitEntity> visits) {
		this.visits = visits;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


}
