// <start id="piano_java" />
package openframework.spring.springinaction.idol;

import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("woodwind")
public class Saxophone implements Instrument {
  public Saxophone() {
  }

  public void play() {
    System.out.println("PLINK PLINK PLINK");
  }
}
// <end id="piano_java" />
