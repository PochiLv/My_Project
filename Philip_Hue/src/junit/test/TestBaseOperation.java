package junit.test;

import org.junit.Test;

import com.lml.baseOp.BaseOperation;
import com.lml.exception.ChagngeStateException;
import com.lml.model.Light;
import com.lml.model.Linght_State;

public class TestBaseOperation {
	@Test
	public void testChageState(){
		BaseOperation baseOperation = new BaseOperation();
		Light light = new Light();
		light.setId("1");
		Linght_State state = light.getState();
		state.setBri(150);
		state.setHue(13537);
		//state.setCt(370);
		try {
			baseOperation.change_state(light);
		} catch (Exception e) {
			throw new ChagngeStateException(e);
		}
	}
}
