package com.abhsinh2.server.editors;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.wst.server.ui.editor.ServerEditorSection;

/**
 * Configuration for custom server
 * 
 * @author abhsinh2
 *
 */
public class CustomServerConfigurationSection extends ServerEditorSection {
	private Text userName;
	private Text password;

	@Override
	public void createSection(Composite parent) {
		super.createSection(parent);

		FormToolkit toolkit = getFormToolkit(parent.getDisplay());

		GridData gdSpan2 = GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create();

		Section connectionSection = toolkit.createSection(parent,
				Section.DESCRIPTION | Section.TITLE_BAR | Section.TWISTIE | Section.EXPANDED);
		connectionSection.setText("SSH Credential");
		connectionSection.setDescription("SSH Credential Details");
		GridLayoutFactory.fillDefaults().applyTo(connectionSection);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(connectionSection);

		Composite connectionComp = toolkit.createComposite(connectionSection);
		GridLayoutFactory.fillDefaults().numColumns(3).applyTo(connectionComp);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(connectionComp);

		toolkit.createLabel(connectionComp, "Username:");
		userName = toolkit.createText(connectionComp, "", SWT.SINGLE | SWT.BORDER);
		userName.setLayoutData(gdSpan2);

		toolkit.createLabel(connectionComp, "Password:");
		password = toolkit.createText(connectionComp, "", SWT.PASSWORD | SWT.SINGLE | SWT.BORDER);
		password.setLayoutData(gdSpan2);

		toolkit.createButton(connectionComp, "Restore", SWT.PUSH).addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				widgetDefaultSelected(e);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		connectionSection.setClient(connectionComp);
	}

}
