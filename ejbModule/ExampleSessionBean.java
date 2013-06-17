import javax.ejb.Stateless;

@Stateless(name = "ExampleSessionBean", mappedName = "ejb/ExampleSessionBean")
public class ExampleSessionBean implements ExampleSessionBeanRemote, ExampleSessionBeanLocal {

  public ExampleSessionBean() {
    ;
  }

  public int multiply(int a, int b) {
    return a * b;
  }

  public int add(int a, int b) {
    return a + b;
  }

  public int substract(int a, int b) {
    return a - b;
  }
}