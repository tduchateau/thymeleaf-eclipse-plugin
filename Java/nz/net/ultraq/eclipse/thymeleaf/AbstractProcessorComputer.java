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

/**
 * Common code between the various processors in this plugin that pick out
 * Thymeleaf processors from the document.
 * 
 * @author Emanuel Rabina
 */
public abstract class AbstractProcessorComputer {

	/**
	 * Returns whether or not the given character is a valid processor name
	 * character.
	 * 
	 * @param c
	 * @return <tt>true</tt> if <tt>char</tt> is an alphanumeric character, or
	 * 		   one of the following symbols: <tt>: - </tt>
	 */
	protected static boolean isProcessorChar(char c) {

		return Character.isLetterOrDigit(c) || c == ':' || c == '-';
	}
}
