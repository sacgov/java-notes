package guicenotes.providers;

import com.google.inject.Provider;
import guicenotes.DrawSquare;
import guicenotes.EnhancedDrawSquare;

public class DrawSquareProvider implements Provider<EnhancedDrawSquare> {
  String color;
  Integer edges;

  // we can inject here as well
  public DrawSquareProvider(String color,Integer edges) {
    this.color = color;
    this.edges = edges

  }
  @Override
  public EnhancedDrawSquare get() {
    return new EnhancedDrawSquare("ref", 40);
  }
}
