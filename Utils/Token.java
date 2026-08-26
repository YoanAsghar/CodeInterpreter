package Utils;

import java.util.ArrayList;
import java.util.List;

public class Token {

  Types tokenType;
  String value;
  int position;

  public Token(Types type, String value, int position) {
    this.tokenType = type;
    this.value = value;
    this.position = position;
  }

  public static List<Token> Tokenize(String stringInput) {
    List<Token> tokens = new ArrayList<>();
    int i = 0;

    while (i < stringInput.length()) {
      char c = stringInput.charAt(i);

      // Skip whitespace
      if (Character.isWhitespace(c)) {
        i++;
        continue;
      }

      // Numbers
      if (Character.isDigit(c)) {
        int start = i;
        while (i < stringInput.length() && Character.isDigit(stringInput.charAt(i))) {
          i++;
        }
        tokens.add(new Token(Types.INT, stringInput.substring(start, i), start));
        continue;
      }

      // Operators
      if (c == '+' || c == '-' || c == '*' || c == '/' || c == '=' || c == '<' || c == '>') {
        tokens.add(new Token(Types.OPERATOR, String.valueOf(c), i));
        i++;
        continue;
      }

      // Strings (quoted)
      if (c == '"') {
        i++; // skip opening quote
        int start = i;
        while (i < stringInput.length() && stringInput.charAt(i) != '"') {
          i++;
        }
        tokens.add(new Token(Types.STRING, stringInput.substring(start, i), start));
        i++; // skip closing quote
        continue;
      }

      // Identifiers (words)
      if (Character.isLetter(c) || c == '_') {
        int start = i;
        while (i < stringInput.length()
            && (Character.isLetterOrDigit(stringInput.charAt(i)) || stringInput.charAt(i) == '_')) {
          i++;
        }
        String word = stringInput.substring(start, i);
        // Check if it's a keyword
        tokens.add(new Token(Types.IDENTIFIER, word, start));
        continue;
      }

      // Unknown character - skip
      i++;
    }

    return tokens;
  }

  public Types getTokenType() {
    return tokenType;
  }

  public String getValue() {
    return value;
  }

  public int getPosition() {
    return position;
  }

  @Override
  public String toString() {
    return tokenType + "(" + value + ")";
  }

  public enum Types {
    INT,
    OPERATOR,
    STRING,
    IDENTIFIER,
  }
}
