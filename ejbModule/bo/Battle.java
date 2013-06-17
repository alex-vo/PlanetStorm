package bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import persistence.Card;
import persistence.Squad;
import persistence.SquadMember;

public class Battle {

  private List<Card> firstDeck;
  private List<Card> secondDeck;

  public Battle(Squad firstSquad, Squad secondSquad) {
    firstDeck = new ArrayList<Card>();
    secondDeck = new ArrayList<Card>();
    for (SquadMember squadMember : firstSquad.getSquadMembers()) {
      firstDeck.add(squadMember.getCard());
    }
    for (SquadMember squadMember : secondSquad.getSquadMembers()) {
      secondDeck.add(squadMember.getCard());
    }
    // this.firstDeck = firstSquad.getSquadMembers();
    // this.secondDeck = secondSquad;
  }

  public void shuffleCards() {
    Collections.shuffle(firstDeck);
    Collections.shuffle(secondDeck);
  }

  public void fight() {
    List<CardFighter> firstWaitingZone = new ArrayList<CardFighter>();
    List<CardFighter> secondWaitingZone = new ArrayList<CardFighter>();
    List<CardFighter> firstBattleField = new ArrayList<CardFighter>();
    List<CardFighter> secondBattleField = new ArrayList<CardFighter>();
    Integer firstPlayerHealth = 50;
    Integer secondPlayerHealth = 50;
    int firstTurn = 0;
    int secondTurn = 0;
    for (int i = 0; i < 50; i++) {
      for (CardFighter cardFighter : firstWaitingZone) {
        if (i == cardFighter.getCard().getTime()) {
          cardFighter.enterBattleField(firstBattleField, firstWaitingZone);
        }
      }
      if (firstDeck != null && firstDeck.size() > 0) {
        CardFighter cardFighter = new CardFighter(firstDeck.get(0));
        firstDeck.remove(cardFighter);
        firstWaitingZone.add(cardFighter);
      }
      if (firstBattleField != null && firstBattleField.size() > 0) {
        // firstBattleField.get(firstTurn) - FIGHT

        if (firstBattleField.size() > firstTurn + 1) {
          firstTurn++;
        }
      }

      Collection<CardFighter> cardsToRemove = new ArrayList<CardFighter>();
      for (CardFighter cardFighter : firstBattleField) {
        if (cardFighter.defense <= 0) {
          cardFighter.beforeDie(firstBattleField);
          cardsToRemove.add(cardFighter);
        }
      }
      firstBattleField.removeAll(cardsToRemove);
      cardsToRemove = new ArrayList<CardFighter>();
      for (CardFighter cardFighter : secondBattleField) {
        if (cardFighter.defense <= 0) {
          cardFighter.beforeDie(secondBattleField);
          cardsToRemove.add(cardFighter);
        }
      }
      secondBattleField.removeAll(cardsToRemove);
    }
    if (firstPlayerHealth <= 0) {
      System.out.println("Second player wins");
    } else if (secondPlayerHealth <= 0) {
      System.out.println("First player wins");
    } else {
      System.out.println("Draw");
    }
  }

  class CardFighter {
    public Integer attack;
    public Integer defense;
    public Boolean enabled = true;
    private Card card;

    public CardFighter(Card card) {
      this.card = card;
      this.attack = card.getAttack();
      this.defense = card.getDefense();
    }

    public Card getCard() {
      return card;
    }

    public void fight(List<CardFighter> myBattleField, List<CardFighter> opponentBattleField,
        Integer opponentHealth) {
      List<CardFighter> cardsToAttack = new ArrayList<CardFighter>();
      if (this.enabled) {
        // MEDIC
        if (this.getCard().getMedic() > 0) {
          CardFighter target = null;
          for (CardFighter cardFighter : myBattleField) {
            if (!this.equals(cardFighter)
                && cardFighter.getCard().getType() == Card.TYPE_INFANTRY
                && (target == null || target.getCard().getDefense() - target.defense < cardFighter
                    .getCard().getDefense() - cardFighter.defense)) {
              target = cardFighter;
            }
          }
          if (target != null) {
            target.defense += this.getCard().getMedic();
          }
        }
        // END MEDIC
        // REPAIR
        if (this.getCard().getRepair() > 0) {
          CardFighter target = null;
          for (CardFighter cardFighter : myBattleField) {
            if (!this.equals(cardFighter)
                && (cardFighter.getCard().getType() == Card.TYPE_ARMOR || cardFighter.getCard()
                    .getType() == Card.TYPE_AIR)
                && (target == null || target.getCard().getDefense() - target.defense < cardFighter
                    .getCard().getDefense() - cardFighter.defense)) {
              target = cardFighter;
            }
          }
          if (target != null) {
            target.defense += this.getCard().getRepair();
          }
        }
        // END REPAIR
        if (myBattleField.indexOf(this) >= 0
            && opponentBattleField.size() > myBattleField.indexOf(this) && this.attack > 0) {
          cardsToAttack.add(opponentBattleField.get(myBattleField.indexOf(this)));
          Integer damage = this.attack;
          // BARRAGE
          if (this.getCard().isBarrage()) {
            if (opponentBattleField.size() > myBattleField.indexOf(this) + 1
                && opponentBattleField.get(myBattleField.indexOf(this) + 1) != null) {
              cardsToAttack.add(opponentBattleField.get(myBattleField.indexOf(this) + 1));
            }
            if (opponentBattleField.size() > myBattleField.indexOf(this) - 1
                && myBattleField.indexOf(this) - 1 >= 0
                && opponentBattleField.get(myBattleField.indexOf(this) - 1) != null) {
              cardsToAttack.add(opponentBattleField.get(myBattleField.indexOf(this) - 1));
            }
          }
          // END BARRAGE
          // EMP
          if (this.getCard().getEmp() > 0) {
            List<CardFighter> possibleTargets = new ArrayList<CardFighter>();
            for (CardFighter cardFighter : opponentBattleField) {
              if (cardFighter.getCard().getType() == Card.TYPE_AIR
                  || cardFighter.getCard().getType() == Card.TYPE_ARMOR
                  || cardFighter.getCard().getType() == Card.TYPE_STRUCTURE) {
                possibleTargets.add(cardFighter);
              }
              if (possibleTargets.size() > 0) {
                CardFighter target = possibleTargets.get(new Random().nextInt(possibleTargets
                    .size()));
                if (new Random().nextInt(100) < this.getCard().getEmp()) {
                  target.enabled = false;
                }
              }
            }
          }
          // END EMP
          for (CardFighter cardToAttack : cardsToAttack) {
            // LASER
            if (this.getCard().isLaser() && cardToAttack.getCard().getType() == Card.TYPE_INFANTRY) {
              damage = 2 * this.attack;
            } else {
              damage = this.attack;
            }
            // END LASER
            // MISSILE
            if (this.getCard().isMissile() && cardToAttack.getCard().getType() == Card.TYPE_AIR) {
              damage = 2 * this.attack;
            } else {
              damage = this.attack;
            }
            // END MISSILE
            // SHELL
            if (this.getCard().isShell() && cardToAttack.getCard().getType() == Card.TYPE_ARMOR) {
              damage = 2 * this.attack;
            } else {
              damage = this.attack;
            }
            // END SHELL
            cardToAttack.defense -= damage;
            cardToAttack.attacked(this, myBattleField);
          }
        } else {
          // ORBITAL STRIKE
          if (this.getCard().getOrbitalStrike() > 0 && opponentBattleField.size() > 0) {
            CardFighter target = opponentBattleField.get(new Random().nextInt(opponentBattleField
                .size()));
            target.defense -= this.getCard().getOrbitalStrike();
          }
          // END ORBITAL STRIKE
          // BOMB
          if (this.getCard().getBomb() > 0) {
            for (CardFighter cardFighter : opponentBattleField) {
              cardFighter.defense -= this.getCard().getBomb();
            }
          }
          // END BOMB
        }
      } else {
        this.enabled = true;
      }
    }

    public void attacked(CardFighter attackingCard, List<CardFighter> attackingBattleField) {
      // RETURN FIRE
      if (this.getCard().getReturnFire() > 0) {
        attackingCard.defense -= this.getCard().getReturnFire();
      }
      // END RETURN FIRE
      // ARMOR
      if (this.getCard().getArmor() > 0) {
        this.defense += this.getCard().getArmor();
      }
      // END ARMOR
    }

    public void beforeDie(List<CardFighter> battleField) {
      // PROTECT_INFANTRY
      if (this.getCard().getProtectInfantry() > 0) {
        for (CardFighter cardFighter : battleField) {
          if (!cardFighter.equals(this) && cardFighter.getCard().getType() == Card.TYPE_INFANTRY) {
            cardFighter.defense -= this.getCard().getProtectInfantry();
          }
        }
      }
      // END PROTECT_INFANTRY
      // COMMAND INFANTRY
      if (this.getCard().getCommantInfantry() > 0) {
        for (CardFighter cardFighter : battleField) {
          if (!cardFighter.equals(this) && cardFighter.getCard().getType() == Card.TYPE_INFANTRY) {
            cardFighter.attack -= this.getCard().getCommantInfantry();
          }
        }
      }
      // END COMMAND INFANTRY
    }

    public void enterBattleField(List<CardFighter> battleField, List<CardFighter> waitingZone) {
      // PROTECT_INFANTRY
      if (this.getCard().getProtectInfantry() > 0 && battleField.size() > 0) {
        for (CardFighter cardFighter : battleField) {
          if (cardFighter.getCard().getType() == Card.TYPE_INFANTRY) {
            cardFighter.defense += this.getCard().getProtectInfantry();
          }
        }
      }
      if (battleField.size() > 0 && this.getCard().getType() == Card.TYPE_INFANTRY) {
        for (CardFighter cardFighter : battleField) {
          if (cardFighter.getCard().getProtectInfantry() > 0) {
            this.defense += cardFighter.getCard().getProtectInfantry();
          }
        }
      }
      // END PROTECT_INFANTRY
      // COMMAND INFANTRY
      if (this.getCard().getCommantInfantry() > 0) {
        for (CardFighter cardFighter : battleField) {
          if (cardFighter.getCard().getType() == Card.TYPE_INFANTRY) {
            cardFighter.attack += this.getCard().getCommantInfantry();
          }
        }
      }
      if (battleField.size() > 0 && this.getCard().getType() == Card.TYPE_INFANTRY) {
        for (CardFighter cardFighter : battleField) {
          if (cardFighter.getCard().getCommantInfantry() > 0) {
            this.attack += cardFighter.getCard().getCommantInfantry();
          }
        }
      }
      // END COMMAND INFANTRY
      waitingZone.remove(this);
      battleField.add(this);
    }

  }

}
