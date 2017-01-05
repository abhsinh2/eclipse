package com.abhsinh2.server.wizards;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.osgi.util.NLS;
import org.eclipse.rse.core.IRSESystemType;
import org.eclipse.rse.core.PasswordPersistenceManager;
import org.eclipse.rse.core.RSECorePlugin;
import org.eclipse.rse.core.model.SystemSignonInformation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.ui.wizard.IWizardHandle;
import org.eclipse.wst.server.ui.wizard.WizardFragment;

import com.abhsinh2.server.Activator;

/**
 * This extension point provides a way to add pages or logic into wizards for a
 * specific runtime, server, or server configuration type.
 * 
 * A wizard fragment used to collect additional attributes required when
 * creating custom runtime server.
 * 
 * @author abhsinh2
 *
 */
public class CustomServerWizardFragment extends WizardFragment {
		
	private IWizardHandle wizardHandle;
	
	private Text userName;
	private Text password;
	
	@Override
	public boolean hasComposite() {
		return true;
	}
	
	@Override
	public Composite createComposite(Composite parent, IWizardHandle handle) {
		this.wizardHandle = handle;

		handle.setTitle("Enter SSH Details");
		handle.setDescription("Enter SSH Details");

		ModifyListener modifyListener = new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				String message = validate();
				if (!StringUtils.isEmpty(message)) {
					wizardHandle.setMessage(message, IWizardHandle.ERROR);
				} else {
					wizardHandle.setMessage(null, IWizardHandle.ERROR);
				}
			}
		};

		Composite container = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(1).equalWidth(false).margins(5, 5).applyTo(container);
		
		Group group = new Group(container, SWT.NONE);
		group.setText("Enter SSH Details");
		GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(false).margins(5, 5).applyTo(group);
		group.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		
		Label usernameLabel = new Label(group, SWT.BORDER);
		usernameLabel.setText("Username:");
		
		userName = new Text(group, SWT.SINGLE | SWT.BORDER);
		userName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		userName.addModifyListener(modifyListener);		
		userName.setToolTipText("The username of the server.");
		
		Label passwordLabel = new Label(group, SWT.BORDER);
		passwordLabel.setText("Password:");

		password = new Text(group, SWT.PASSWORD | SWT.BORDER);
		password.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		password.addModifyListener(modifyListener);		
		password.setToolTipText("The password of the server.");
		
		return container;
	}
	
	@Override
	public void enter() {
		setDefaults();
		super.enter();
	}
	
	protected void setDefaults() {
		if (userName == null) {
			return;
		}
		userName.setText("root");		
	}
	
	@Override
	public boolean isComplete() {
		return StringUtils.isEmpty(validate());
	}
	
	protected String validate() {
		if (StringUtils.isEmpty(userName.getText())) {
			return NLS.bind("{0} cannot be empty.", "Username");
		}

		if (StringUtils.isEmpty(password.getText())) {
			return NLS.bind("{0} cannot be empty.", "Password");
		}

		return null;
	}
	
	@Override
	public void performFinish(IProgressMonitor monitor) throws CoreException {
		super.performFinish(monitor);
		monitor.beginTask("Creating Server", 2);
		final Object serverObject = getTaskModel().getObject("server");

		if (serverObject instanceof IServerWorkingCopy) {
			Display.getDefault().syncExec(new Runnable() {

				@Override
				public void run() {
					IServerWorkingCopy server = (IServerWorkingCopy) serverObject;

					String username = userName == null ? "root" : userName.getText();
					server.setAttribute(CustomServerConstants.SERVER_USERNAME, username);

					server.setAttribute(CustomServerConstants.SERVER_PASSWORD, password.getText());
					setupSSHAccess(server);					

					server.setAttribute(CustomServerConstants.SERVER_STARTUP_SCRIPT, "startup.sh");
					server.setAttribute(CustomServerConstants.SERVER_SHUTDOWN_SCRIPT, "shutdown.sh");
					server.setAttribute(CustomServerConstants.SERVER_STARTUP_LOG, "catalina.log");
					server.setAttribute(CustomServerConstants.SERVER_SHUTDOWN_LOG, "catalina.log");		
					server.setAttribute(CustomServerConstants.SERVER_APPLICATION_HOME, "/root");
				}
			});

		} else {
			throw new CoreException(
					new Status(IStatus.ERROR, Activator.PLUGIN_ID, "An error occurred"));
		}
		monitor.worked(1);
	}	
	
	protected void setupSSHAccess(IServerWorkingCopy server) {
		try {
			String user = userName.getText();
			String pw = password.getText();
			createSshAndFtpLogins(server.getHost(), user, pw);
		} catch (Throwable e) {
			Activator.logError(
					"An error occurred configuring SSH and FTP access for the server: " + server.getHost(), e);
		}
	}
	
	public static void createSshAndFtpLogins(String host, String user, String password) {
		persistLoginCredentials(IRSESystemType.SYSTEMTYPE_SSH_ONLY_ID, host, user, password);
		persistLoginCredentials(IRSESystemType.SYSTEMTYPE_FTP_ONLY_ID, host, user, password);
	}
	
	public static SystemSignonInformation persistLoginCredentials(String typeId, String host, String user,
			String password) {
		SystemSignonInformation data = new SystemSignonInformation();
		data.setHostname(host);
		data.setSystemType(RSECorePlugin.getTheCoreRegistry().getSystemTypeById(typeId));
		data.setPassword(password);
		data.setUserId(user);
		PasswordPersistenceManager.getInstance().add(data, true);
		return data;
	}

}
