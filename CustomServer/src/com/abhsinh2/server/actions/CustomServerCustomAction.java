package com.abhsinh2.server.actions;

import org.eclipse.jface.action.Action;

public class CustomServerCustomAction extends Action {
	public CustomServerCustomAction() {
		setId("Custom Action");
		setText("Custom Action");
		setToolTipText("Custom Action");
	}
	
	@Override
	public void run() {
		super.run();
	}
}
