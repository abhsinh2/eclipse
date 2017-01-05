package com.abhsinh2.server.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.wst.server.core.IServer;

/**
 * 
 * @author abhsinh2
 *
 */
public class LaunchTerminalCommandHandler extends AbstractHandler {

	protected IServer getSelectedServer(IStructuredSelection selection) {
		if (selection != null && !selection.isEmpty()) {
			Object obj = selection.getFirstElement();
			return obj instanceof IServer ? (IServer) obj : null;
		}
		return null;
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		IServer selectedServer = getSelectedServer((IStructuredSelection) selection);
		if (selectedServer == null) {
			MessageDialog.openError(Display.getDefault().getActiveShell(), "Invalid Selection",
					"This operation is not supported for the current selection.");
			return null;
		}
		return null;
	}

}
