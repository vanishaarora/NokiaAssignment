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
@Table(name = "parts")
public class Part {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column()
  private String name;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<PartManufacturer> partManufacturers = new HashSet<>();

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

  public Set<PartManufacturer> getPartManufacturers() {
    return partManufacturers;
  }

  public void setPartManufacturers(Set<PartManufacturer> partManufacturers) {
    this.partManufacturers = partManufacturers;
  }

  @Override
  public String toString() {
    return "Part{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", partManufacturers=" + partManufacturers +
        '}';
  }
}
