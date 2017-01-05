package com.abhsinh2.server.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.abhsinh2.server.Activator;

public class CustomServerPreferenceInitializer extends AbstractPreferenceInitializer {

	public CustomServerPreferenceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore preferences = Activator.getDefault().getPreferenceStore();
	}

}
