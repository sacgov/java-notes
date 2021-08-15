package guicenotes;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Here SquareRequest is a dependant on DrawSquare for its implementation
 * SquareRequest -> dependant
 * DrawSquare -> dependency
 * <p>
 * SquareRequest doesn't now anything about the child of DrawSquare
 * Let us say BigSquare is a child of DrawSquare and is needed as a business requirement
 * He will have to implement it as drawBigSquare by implementing it. we might need a different class
 * Or the class takes the square as a constructor parameter as to which square to use
 * <p>
 * This code is tightly coupled and not good for unit testing
 * Breaks single responsibility principle. DrawSquare is not a responsibility of this class
 * Its only responsibility should be draw
 * <p>
 * Imagine there are various other constructors that are needed inside this class.
 * It becomes hard to instantiate all these classes
 * Once we instantiate anything -> writing new makes it difficult to write unit tests
 */
class SquareRequestV1 {
  DrawSquare drawSquare = new DrawSquare();

  void makeRequest() {
    drawSquare.draw();
  }
}

class SquareRequestSubClass extends SquareRequest {

  @Inject
  SquareRequestSubClass(DrawShape drawShape) {
    super(drawShape);
  }

  @Override
  void makeRequest() {
    System.out.println("This is from a sub class");
    d.draw();
  }
}

class SquareRequestMethodInjection {
  DrawShape d;

  void makeRequest() {
    System.out.println("This is from a sub class");
    d.draw();
  }

  @Inject
  void setDrawShape(DrawShape d) {
    this.d = d;
  }

}

class SquareRequestFieldInjection {
  @Inject
  DrawShape d;

  void makeRequest() {
    System.out.println("This is from a sub class");
    d.draw();
  }
}

class SquareRequestNamedInjection{
  @Inject
  @Named("circle")
  DrawShape d;

  /**
   * The disadvantage is we need the strings to exactly match something like
   * annotatedWith(Names.named("circle))
   */

  void makeRequest() {
    System.out.println("This is from a sub class");
    d.draw();
  }
}

public class SquareRequest {
  /**
   * We have made an improvement here. We are passing the required square in the constructor
   * Through polymorphism will be delegated to any of the subtypes of drawShape
   * Now the BigSquare implementation will not need a change here
   * <p>
   * We have delegated the responsibility to the called of instantiating the request
   * We will outsource the dependency to guice to alleviate this in the UserRequest class
   * What is the square to be passed will depend on the configuration files rather than our implement it again
   */
  DrawShape d;

  @Inject
  SquareRequest(DrawShape d) {
    this.d = d;
  }

  void makeRequest() {
    d.draw();
  }
}