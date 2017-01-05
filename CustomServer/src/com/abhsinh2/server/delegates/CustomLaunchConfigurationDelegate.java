package com.abhsinh2.server.delegates;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.ServerUtil;

import com.abhsinh2.server.Activator;

/**
 * The launch configuration associated with the custom server. This is the entry
 * point when users start the server.
 * 
 * @author abhsinh2
 *
 */
public class CustomLaunchConfigurationDelegate implements ILaunchConfigurationDelegate {

	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {
		IServer server = ServerUtil.getServer(configuration);
		if (server == null) {
			Activator.logError("Could not map the launch configuration to a server", null);
			throw new CoreException(
					(IStatus) new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Could not find launch configuration"));
		}

		CustomServerBehaviourDelegate serverBehavior = (CustomServerBehaviourDelegate) server
				.loadAdapter(CustomServerBehaviourDelegate.class, null);

		try {
			serverBehavior.start(monitor);
		} catch (Exception e) {
			throw new CoreException(
					new Status(IStatus.ERROR, Activator.PLUGIN_ID, "An error occurred when starting the server", e));
		} finally {
			launch.terminate();
			ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
			launchManager.removeLaunch(launch);
		}
	}

}
