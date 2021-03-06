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

package nz.net.ultraq.eclipse.thymeleaf.contentassist;

import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.swt.graphics.Image;

/**
 * Additional information for Thymeleaf processors.
 * 
 * @author Emanuel Rabina
 */
public class ProcessorContextInformation implements IContextInformation {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getContextDisplayString() {

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getImage() {

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getInformationDisplayString() {

		return null;
	}
}
