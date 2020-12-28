package guicenotes;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import guicenotes.annotations.ColorValue;
import guicenotes.annotations.EdgesValue;

public class AppModule extends AbstractModule {

  @Override
  protected void configure() {
    /**
     * What we are saying is that when we request DrawShape it should give DrawSquare
     */
//    bind(DrawShape.class).to(DrawSquare.class);
    bind(SquareRequest.class).to(SquareRequestSubClass.class);

    /**
     * In the below enhanced case it is always the same string and inteeger
     * We might using string in other places which will be affected by such a definition
     * We have to define an annotation to bind the value according ot the annotation
     */
    // use .in(Scopes.Singleton)
    bind(DrawShape.class).to(EnhancedDrawSquare.class);
    bind(String.class).annotatedWith(ColorValue.class).toInstance("Square");
    bind(Integer.class).annotatedWith(EdgesValue.class).toInstance(40);

  }

  /**
   * Sometimes we have an object in a jar that is from external source
   * Then we need a provider since we can no longer inject ColorValue/ EdgesValue
   * See the example before
   */
//  @Provides
//  @Singleton
//  DrawShape providesDrawSquare() {
//    return new EnhancedDrawSquare("ref", 2);
//  }
  /**
   * Add parameters and inject in the above provider as well
   * Also since this logic keeps getting bigger - we can write a provider class
   * bind.toProvider
   */
}
