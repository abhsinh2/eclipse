package com.abhsinh2.server.ui;


import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;

public class CustomServerParentPropertyPage extends PropertyPage implements IWorkbenchPropertyPage {

	public CustomServerParentPropertyPage() {
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(false).margins(5, 5).applyTo(container);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Label label = new Label(container, SWT.LEFT);
		label.setText("Custom Server Properties");
		return container;
	}

}
