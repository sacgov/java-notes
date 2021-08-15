package guicenotes;

import com.google.inject.Inject;
import guicenotes.annotations.ColorValue;
import guicenotes.annotations.EdgesValue;

public class EnhancedDrawSquare implements DrawShape {

  private String color;
  private Integer edges;

  /**
   * check how guice injects these String and Integers
   */
  @Inject
  public EnhancedDrawSquare(@ColorValue  String color, @EdgesValue Integer edges) {
    super();
    this.color = color;
    this.edges = edges;
  }

  @Override
  public void draw() {
    System.out.println("drawing  square with " + color + " " + edges);
  }
}
