package com.abhsinh2.server.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * 
 * @author abhsinh2
 *
 */
public class CustomServerActionPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	public CustomServerActionPreferencePage() {
	}

	public CustomServerActionPreferencePage(String title) {
		super(title);
	}

	public CustomServerActionPreferencePage(String title, ImageDescriptor image) {
		super(title, image);
	}

	@Override
	public void init(IWorkbench workbench) {

	}

	@Override
	protected Control createContents(Composite parent) {
		return null;
	}

}
