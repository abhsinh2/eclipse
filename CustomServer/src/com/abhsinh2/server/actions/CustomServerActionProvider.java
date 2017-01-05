package com.abhsinh2.server.actions;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonViewerSite;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.wst.server.core.IServer;

/**
 * Action provider used to add a content menu to the server view
 * 
 * @author abhsinh2
 *
 */
public class CustomServerActionProvider extends CommonActionProvider {
	public static final String MY_SERVER_CONTEXT_MENU_ID = "com.abhsinh2.server.customContextMenu";

	@Override
	public void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);
		// Add More Menu

		ICommonViewerSite site = getActionSite().getViewSite();
		IStructuredSelection selection = null;
		if (site instanceof ICommonViewerWorkbenchSite) {
			ICommonViewerWorkbenchSite wsSite = (ICommonViewerWorkbenchSite) site;
			selection = (IStructuredSelection) wsSite.getSelectionProvider().getSelection();
		}

		IServer customServer = null;
		if (selection != null && !selection.isEmpty()) {
			Object obj = selection.getFirstElement();
			customServer = obj instanceof IServer ? (IServer) obj : null;
		}

		if (customServer == null) {
			return;
		}

		String serverId = customServer.getServerType().getId();

		MenuManager importLogMenu = new MenuManager("Custom Menu", null, MY_SERVER_CONTEXT_MENU_ID);
		importLogMenu.add(new CustomServerCustomAction());

		menu.insertBefore(IWorkbenchActionConstants.MB_ADDITIONS, importLogMenu);
	}
}
