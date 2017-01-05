package com.abhsinh2.server.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.server.ui.DeleteServerDialogExtension;

public class CustomServerDeleteDialogExtension extends DeleteServerDialogExtension {

	@Override
	public void createControl(Composite parent) {
		
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void performPostDeleteAction(IProgressMonitor monitor) {
		
	}

	@Override
	public void performPreDeleteAction(IProgressMonitor monitor) {
		
	}

}
