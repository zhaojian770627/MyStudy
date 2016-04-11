package openframework.spring.springinaction.declarativetx;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.Before;
import org.junit.Test;

import openframework.spring.springinaction.jdbc.Spitter;
import openframework.spring.springinaction.jdbc.SpitterDao;

public class SpitterServiceImplTest {
	private SpitterServiceImpl spitterService;
	private SpitterDao spitterDao;
	private Spitter newSpitter;
	private Spitter oldSpitter;

	@Before
	public void setup() {
		newSpitter = new Spitter();
		newSpitter.setUsername("testuser");
		newSpitter.setPassword("password");
		newSpitter.setFullName("Michael McTest");

		oldSpitter = new Spitter();
		oldSpitter.setId(12345L);
		oldSpitter.setUsername("olduser");
		oldSpitter.setPassword("letmein");
		oldSpitter.setFullName("Bob O'Test");

		spitterDao = createNiceMock(SpitterDao.class);
		spitterDao.saveSpitter(oldSpitter);
		spitterDao.addSpitter(newSpitter);
		replay(spitterDao);

		spitterService = new SpitterServiceImpl();
		spitterService.spitterDao = spitterDao;
	}

	@Test
	public void shouldAddSpitter() {
		spitterService.saveSpitter(newSpitter);
		spitterService.saveSpitter(oldSpitter);
		verify(spitterDao);
	}
}
