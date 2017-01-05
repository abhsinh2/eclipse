package com.abhsinh2.server.ui;

import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.dialogs.PropertyPage;

/**
 * The property page used to show a project's deployment settings when deployed
 * on server
 * 
 * @author abhsinh2
 *
 */
public class CustomServerCustomDetailsPropertyPage extends PropertyPage implements IWorkbenchPropertyPage {

	public CustomServerCustomDetailsPropertyPage() {
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(false).margins(5, 5).applyTo(container);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Label label = new Label(container, SWT.LEFT);
		label.setText("Key:");
		Text text = new Text(container, SWT.NONE);		
		return container;
	}

}
