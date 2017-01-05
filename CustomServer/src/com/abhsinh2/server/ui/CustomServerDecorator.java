package com.abhsinh2.server.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.ui.ISharedImages;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.ui.IServerModule;

import com.abhsinh2.server.Activator;

/**
 * 
 * @author abhsinh2
 *
 */
public class CustomServerDecorator implements ILightweightLabelDecorator {

	@Override
	public void addListener(ILabelProviderListener listener) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {

	}

	@Override
	public void decorate(Object element, IDecoration decoration) {
		if (element instanceof IServerModule) {
			IServerModule serverModule = (IServerModule) element;
			IServer server = serverModule.getServer();
			if (server == null) {
				return;
			}
			int moduleState = server.getModuleState(serverModule.getModule());
			ImageDescriptor image = null;

			ISharedImages sharedImages = Activator.getDefault().getWorkbench().getSharedImages();
			ImageDescriptor errorImage = ImageDescriptor
					.createFromImage(sharedImages.getImage(ISharedImages.IMG_DEC_FIELD_ERROR));

			switch (moduleState) {
			case IServer.STATE_STOPPED:
				image = errorImage;
				break;
			}

			if (image != null) {
				decoration.addOverlay(image);
			}
		}
	}

}
