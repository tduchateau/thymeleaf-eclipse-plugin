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

import nz.net.ultraq.eclipse.thymeleaf.contentassist.ProcessorCache;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 * 
 * @author Emanuel Rabina
 */
public class ThymeleafPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "nz.net.ultraq.eclipse.thymeleaf";

	private static final String[] DIALECT_FILES = {
			"dialects/Standard-Dialect.xml",
			"dialects/Layout-Dialect.xml",
	};
	private static final String THYMELEAF_ATTRIBUTE_IMAGE = "thymeleaf-attribute-image";
	private static final String THYMELEAF_ATTRIBUTE_IMAGE_FILENAME = "Thymeleaf-Leaf.png";

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
	 * Return the image used for Thymeleaf attributes.
	 * 
	 * @return Thymeleaf attribute image.
	 */
	public Image getAttributeImage() {

		return plugin.getImageRegistry().get(THYMELEAF_ATTRIBUTE_IMAGE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {

		super.initializeImageRegistry(reg);
		reg.put(THYMELEAF_ATTRIBUTE_IMAGE, new ThymeleafImageDescriptor(THYMELEAF_ATTRIBUTE_IMAGE_FILENAME));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(BundleContext context) throws Exception {

		super.start(context);

		ProcessorCache.initialize(DIALECT_FILES);
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

	/**
	 * Image descriptor to retrieve Thymeleaf plugin images.
	 * 
	 * @author Emanuel Rabina
	 */
	private class ThymeleafImageDescriptor extends ImageDescriptor {

		private static final String IMAGE_DIRECTORY = "images/";

		private final String imagefilename;

		/**
		 * Constructor, set the name of the image to retrieve from the
		 * resources/images directory.
		 * 
		 * @param imagefilename
		 */
		private ThymeleafImageDescriptor(String imagefilename) {

			this.imagefilename = imagefilename;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ImageData getImageData() {

			return new ImageData(ThymeleafPlugin.class.getClassLoader().getResourceAsStream(
					IMAGE_DIRECTORY + imagefilename));
		}
	}
}
