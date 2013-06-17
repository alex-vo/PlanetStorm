import javax.ejb.Remote;

@Remote
public interface ExampleSessionBeanRemote {
  public int multiply(int a, int b);

  public int add(int a, int b);

  public int substract(int a, int b);
}
