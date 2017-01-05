package com.abhsinh2.server.delegates;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.wst.server.core.model.ServerBehaviourDelegate;

/**
 * Defines all custom server operations like starting or stopping the server.
 * 
 * @author abhsinh2
 *
 */
public class CustomServerBehaviourDelegate extends ServerBehaviourDelegate {

	@Override
	public void stop(boolean force) {

	}

	public void start(IProgressMonitor monitor) {
		System.out.println("Starting code goes here");
		StatusManager.getManager().handle(Status.OK_STATUS, StatusManager.LOG | StatusManager.SHOW);
	}

}
