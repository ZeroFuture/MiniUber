package springcloud.accountservice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "driver-generator")
  @SequenceGenerator(name = "driver-generator", sequenceName = "driver_seq", allocationSize = 1)
  @Column(name = "id", updatable = false, nullable = false)
  private long id;
  private String firstName;
  private String lastName;
  private String address;
  private String phone;
  private boolean isActive;
  private LocalDateTime createdAt;

  public Driver() {}

  public Driver(long id, String firstName, String lastName, String address, String phone,
                boolean isActive, LocalDateTime createdAt) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
    this.isActive = isActive;
    this.createdAt = createdAt;
  }

  public Driver(String firstName, String lastName, String address, String phone,
                boolean isActive) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
    this.isActive = isActive;
    this.createdAt = LocalDateTime.now();
  }

  @JsonProperty
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @JsonProperty
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @JsonProperty
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @JsonProperty
  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @JsonProperty("getActive")
  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  @JsonProperty
  public long getId() {
    return id;
  }

  @JsonProperty
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return String.format(
            "Driver[id=%d, firstName='%s', lastName='%s', "
                    + "address='%s', phone='%s', isActive='%s', createdAt='%s']",
            id, firstName, lastName, address, phone, isActive, createdAt);
  }
}
