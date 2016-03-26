//<start id="instrumentalist_java" />
package openframework.spring.springinaction.idol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Instrumentalist implements Performer {
  public void perform() throws PerformanceException {
    getInstrument().play();
  }

  public Instrument getInstrument() {
    return instrument;
  }

  //<start id="autowire_property" />
  @Autowired
  @Qualifier("woodwind")
  private Instrument instrument;
  //<end id="autowire_property" />

}
//<end id="instrumentalist_java" />
