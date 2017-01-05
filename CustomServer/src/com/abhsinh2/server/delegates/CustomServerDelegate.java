package com.abhsinh2.server.delegates;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.ServerDelegate;

/**
 * The server delegate of the custom server
 * 
 * @author abhsinh2
 *
 */
public class CustomServerDelegate extends ServerDelegate {

	@Override
	public IStatus canModifyModules(IModule[] addedModules, IModule[] removedModules) {
		return null;
	}

	@Override
	public IModule[] getChildModules(IModule[] module) {
		return null;
	}

	@Override
	public IModule[] getRootModules(IModule module) throws CoreException {
		return null;
	}

	@Override
	public void modifyModules(IModule[] addedModules, IModule[] removedModules, IProgressMonitor monitor) throws CoreException {

	}

}
