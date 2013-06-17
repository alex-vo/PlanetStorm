package persistence;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "squad")
public class Squad {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "created", nullable = false)
  private Timestamp created;

  @OneToMany
  @JoinColumn(name = "squad_id")
  private List<SquadMember> squadMembers;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Timestamp getCreated() {
    return created;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public Long getId() {
    return id;
  }

  public List<SquadMember> getSquadMembers() {
    return squadMembers;
  }

  public void setSquadMembers(List<SquadMember> squadMembers) {
    this.squadMembers = squadMembers;
  }

}
