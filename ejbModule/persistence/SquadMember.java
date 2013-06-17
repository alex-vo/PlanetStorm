package persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "squad_member")
public class SquadMember {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "card_id")
  private Card card;

  @ManyToOne
  @JoinColumn(name = "squad_id")
  private Squad squad;

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }

  public Squad getSquad() {
    return squad;
  }

  public void setSquad(Squad squad) {
    this.squad = squad;
  }

  public Long getId() {
    return id;
  }

}
