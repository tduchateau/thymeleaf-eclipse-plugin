/*
 * Copyright 2013, Emanuel Rabina (http://www.ultraq.net.nz/)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.net.ultraq.eclipse.thymeleaf;

import nz.net.ultraq.eclipse.thymeleaf.contentassist.StandardAttributeCache;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author Emanuel Rabina
 */
public class ThymeleafPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "nz.net.ultraq.eclipse.thymeleaf"; //$NON-NLS-1$

	private static final String[] DIALECT_FILES = {
			"nz/net/ultraq/eclipse/thymeleaf/Standard-Dialect.xml"
	};

	private static ThymeleafPlugin plugin;

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static ThymeleafPlugin getDefault() {

		return plugin;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(BundleContext context) throws Exception {

		super.start(context);

		StandardAttributeCache.initialize(DIALECT_FILES);
		plugin = this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(BundleContext context) throws Exception {

		plugin = null;
		super.stop(context);
	}
}
