package test;
import org.hibernate.Session;

import persistence.HibernateUtil;
import persistence.Squad;
import bo.Battle;

public class Main {

  public static void main(String[] args) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Squad squadAlex = (Squad) session.get(Squad.class, 1L);
    Squad squadSergey = (Squad) session.get(Squad.class, 2L);
    System.out.println(squadAlex.getName());
    System.out.println(squadSergey.getName());

    Battle battle = new Battle(squadAlex, squadSergey);
    battle.shuffleCards();
    battle.modelBattle();
  }
}