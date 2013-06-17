import java.util.List;

import javax.naming.InitialContext;

import org.hibernate.Query;
import org.hibernate.Session;

import persistence.Card;
import persistence.HibernateUtil;

public class Main {
  public void runTest() throws Exception {
    InitialContext ctx = new InitialContext();
    ExampleSessionBeanRemote bean = (ExampleSessionBeanRemote) ctx.lookup("ejb/ExampleSessionBean");
    int result = bean.multiply(3, 4);
    System.out.println(result);
  }

  public static void main(String[] args) {
    /*
     * Main cli = new Main(); try { cli.runTest(); } catch (Exception e) { e.printStackTrace(); }
     */
    Session session = HibernateUtil.getSessionFactory().openSession();
    Query query = session.createQuery("from Card");
    List<Card> allCards = query.list();
    for (int i = 0; i < allCards.size(); i++) {
      Card card = (Card) allCards.get(i);
      System.out.print(card.getName());
      System.out.println();
    }
  }
}