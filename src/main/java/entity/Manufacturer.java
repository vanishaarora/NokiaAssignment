package entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private String name;

  @OneToMany(mappedBy = "manufacturer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private Set<PartManufacturer> parts = new HashSet<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<PartManufacturer> getParts() {
    return parts;
  }

  public void setParts(Set<PartManufacturer> parts) {
    this.parts = parts;
  }

  @Override
  public String toString() {
    return "Manufacturer{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", parts=" + parts +
        '}';
  }
}
