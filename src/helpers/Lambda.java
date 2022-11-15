package helpers;

public interface Lambda {
  public void exec() throws Exception;
}

interface LambdaOne {
  public void exec(Option op) throws Exception;
}

interface LambdaStr {
  public boolean exec(String obj);
}
