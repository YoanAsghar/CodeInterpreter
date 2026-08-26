import java.util.Scanner;
import java.util.List;
import Utils.Token;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
      System.out.print(">>> ");
      String input = scanner.nextLine();

      if (input.equals("exit"))
        running = false;

      List<Token> tokens = Token.Tokenize(input);
      for (Token token : tokens) {
        System.out.println(token);
      }
    }

    scanner.close();
  }
}
