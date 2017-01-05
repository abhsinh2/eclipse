package com.abhsinh2.server.delegates;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaArgumentsTab;
import org.eclipse.wst.server.ui.ServerLaunchConfigurationTab;

/**
 * Defines the launch configuration tab group for the server.
 * 
 * @author abhsinh2
 *
 */
public class CustomServerLaunchConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {

	@Override
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
		ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
				new CustomServerLaunchConfigurationTab(new String[] { "com.abhsinh2.server.customServer" }),
				new JavaArgumentsTab(), new CommonTab() };

		for (ILaunchConfigurationTab tab : tabs) {
			tab.setLaunchConfigurationDialog(dialog);
		}

		setTabs(tabs);
	}

	private static class CustomServerLaunchConfigurationTab extends ServerLaunchConfigurationTab {
		public CustomServerLaunchConfigurationTab(String[] ids) {
			super(ids);
		}

		@Override
		public void initializeFrom(ILaunchConfiguration configuration) {
			super.initializeFrom(configuration);
			handleServerSelection();
		}
	}

}
