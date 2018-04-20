package com.bpa.controll.routine;

import com.bpa.model.Browser;
import com.bpa.model.exception.RoutineException;

public interface Routine {
	public void execute(Browser _browser) throws RoutineException;
}
