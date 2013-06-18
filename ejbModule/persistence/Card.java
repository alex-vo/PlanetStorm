package persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "card")
public class Card {

  public static final char TYPE_AIR = 'R';
  public static final char TYPE_ARMOR = 'A';
  public static final char TYPE_INFANTRY = 'I';
  public static final char TYPE_STRUCTURE = 'S';
  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "type")
  private char type;

  @Column(name = "card_set", nullable = false)
  private Integer cardSet;

  @Column(name = "attack")
  private Integer attack;

  @Column(name = "defense")
  private Integer defense;

  @Column(name = "time")
  private Integer time;

  @Column(name = "name")
  private String name;

  @Column(name = "is_hero", columnDefinition = "BIT")
  private Boolean isHero;

  @Column(name = "units")
  private Integer units;

  @Column(name = "equipment")
  private Integer equipment;

  @Column(name = "tactics")
  private Integer tactics;

  @Column(name = "barrage", columnDefinition = "BIT")
  private Boolean barrage;

  @Column(name = "protect_infantry")
  private Integer protectInfantry;

  @Column(name = "emp")
  private Integer emp;

  @Column(name = "orbital_strike")
  private Integer orbitalStrike;

  @Column(name = "bomb")
  private Integer bomb;

  @Column(name = "return_fire")
  private Integer returnFire;

  @Column(name = "armor")
  private Integer armor;

  @Column(name = "medic")
  private Integer medic;

  @Column(name = "repair")
  private Integer repair;

  @Column(name = "command_infantry")
  private Integer commantInfantry;

  @Column(name = "laser", columnDefinition = "BIT")
  private Boolean laser;

  @Column(name = "missile", columnDefinition = "BIT")
  private Boolean missile;

  @Column(name = "shell", columnDefinition = "BIT")
  private Boolean shell;

  public char getType() {
    return type;
  }

  public void setType(char type) {
    this.type = type;
  }

  public Integer getCardSet() {
    return cardSet;
  }

  public void setCardSet(Integer cardSet) {
    this.cardSet = cardSet;
  }

  public Integer getAttack() {
    return attack;
  }

  public void setAttack(Integer attack) {
    this.attack = attack;
  }

  public Integer getDefense() {
    return defense;
  }

  public void setDefense(Integer defense) {
    this.defense = defense;
  }

  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean isHero() {
    return isHero != null ? isHero : false;
  }

  public void setHero(Boolean isHero) {
    this.isHero = isHero;
  }

  public Integer getUnits() {
    return units != null ? units : 0;
  }

  public void setUnits(Integer units) {
    this.units = units;
  }

  public Integer getEquipment() {
    return equipment != null ? equipment : 0;
  }

  public void setEquipment(Integer equipment) {
    this.equipment = equipment;
  }

  public Integer getTactics() {
    return tactics != null ? tactics : 0;
  }

  public void setTactics(Integer tactics) {
    this.tactics = tactics;
  }

  public Boolean isBarrage() {
    return barrage != null ? barrage : false;
  }

  public void setBarrage(Boolean barrage) {
    this.barrage = barrage;
  }

  public Integer getProtectInfantry() {
    return protectInfantry != null ? protectInfantry : 0;
  }

  public void setProtectInfantry(Integer protectInfantry) {
    this.protectInfantry = protectInfantry;
  }

  public Integer getEmp() {
    return emp != null ? emp : 0;
  }

  public void setEmp(Integer emp) {
    this.emp = emp;
  }

  public Integer getOrbitalStrike() {
    return orbitalStrike != null ? orbitalStrike : 0;
  }

  public void setOrbitalStrike(Integer orbitalStrike) {
    this.orbitalStrike = orbitalStrike;
  }

  public Integer getBomb() {
    return bomb != null ? bomb : 0;
  }

  public void setBomb(Integer bomb) {
    this.bomb = bomb;
  }

  public Integer getReturnFire() {
    return returnFire != null ? returnFire : 0;
  }

  public void setReturnFire(Integer returnFire) {
    this.returnFire = returnFire;
  }

  public Integer getArmor() {
    return armor != null ? armor : 0;
  }

  public void setArmor(Integer armor) {
    this.armor = armor;
  }

  public Integer getMedic() {
    return medic != null ? medic : 0;
  }

  public void setMedic(Integer medic) {
    this.medic = medic;
  }

  public Integer getRepair() {
    return repair != null ? repair : 0;
  }

  public void setRepair(Integer repair) {
    this.repair = repair;
  }

  public Integer getCommantInfantry() {
    return commantInfantry != null ? commantInfantry : 0;
  }

  public void setCommantInfantry(Integer commantInfantry) {
    this.commantInfantry = commantInfantry;
  }

  public Boolean isLaser() {
    return laser != null ? laser : false;
  }

  public void setLaser(Boolean laser) {
    this.laser = laser;
  }

  public Boolean isMissile() {
    return missile != null ? missile : false;
  }

  public void setMissile(Boolean missile) {
    this.missile = missile;
  }

  public Boolean isShell() {
    return shell != null ? shell : false;
  }

  public void setShell(Boolean shell) {
    this.shell = shell;
  }

  public Long getId() {
    return id;
  }

}
