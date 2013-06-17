import javax.ejb.Local;

@Local
public interface ExampleSessionBeanLocal {
  public int multiply(int a, int b);

  public int add(int a, int b);

  public int substract(int a, int b);
}