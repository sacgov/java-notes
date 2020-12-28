package guicenotes;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Demo {
  public static final String SQUARE_REQ = "Square";

  private static void sendRequestV1(String squareReq) {

    /**
     * the responsibility of the sender to send the drawSquare object to squareRequest
     * We will change that to Guice
     */
    if (squareReq.equals(SQUARE_REQ)) {
      DrawShape drawSquare = new DrawSquare();
      SquareRequest squareRequest = new SquareRequest(drawSquare);
      squareRequest.makeRequest();
    }
  }
  private static void sendRequestV2(String squareReq) {

    /**
     * the responsibility of the sender to send the drawSquare object to squareRequest
     * We will change that to Guice
     */
    if (squareReq.equals(SQUARE_REQ)) {
      /**
       * We create Guice instance from AppModule to create the injector for us
       */
      Injector injector = Guice.createInjector(new AppModule());
      DrawShape drawSquare = injector.getInstance(DrawShape.class);
      SquareRequest squareRequest = new SquareRequest(drawSquare);
      squareRequest.makeRequest();
    }
  }

  private static void sendRequest(String squareReq) {
    if (squareReq.equals(SQUARE_REQ)) {
      /**
       * We get SquareRequest Directly from Guice
       *
       * Guice consults the Module to check what is the binding to instantiate the class
       * If TypeA is an interface we need something like
       * bind(TypeA.class).to(SomeConcreteImpl.class);
       *
       * If TypeA itself is concreteImpl
       * It can have or cannot have bindings
       * It can have a binding to its subclass
       *
       * Here we ask SquareRequest. Goes to bindings and sees there is no binding and is a concrete class
       * It attempts to call a constructor. It sees that we have inject annotation
       * It attempts to create DrawShape. DrawShape is bound to DrawSquare and it generates a DrawSquare which has
       * a no-arg constructor
       */
      Injector injector = Guice.createInjector(new AppModule());
      // We need to add an inject in the implementation of SquareRequest so it knows it can inject the DrawSquare
      SquareRequest squareRequest = injector.getInstance(SquareRequest.class);
      squareRequest.makeRequest();
    }
  }
  public static void main(String[] args) {
    sendRequest(SQUARE_REQ);
  }
}
