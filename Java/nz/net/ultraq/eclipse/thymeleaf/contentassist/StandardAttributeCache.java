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

import nz.net.ultraq.eclipse.thymeleaf.xml.AttributeProcessor;
import nz.net.ultraq.eclipse.thymeleaf.xml.Dialect;
import nz.net.ultraq.eclipse.thymeleaf.xml.Processor;
import nz.net.ultraq.jaxb.XMLReader;

import java.util.ArrayList;
import java.util.List;

/**
 * A basic in-memory store of all the Thymeleaf processors.
 * 
 * @author Emanuel Rabina
 */
public class StandardAttributeCache {

	private static ArrayList<Processor> processors = new ArrayList<>();

	/**
	 * Retrieve all attribute processors whose names match the given start
	 * pattern.
	 * 
	 * @param pattern Start-of-string pattern to match.
	 * @return List of all attribute processors in the standard dialect.
	 */
	public static List<AttributeProcessor> getAttributeProcessors(String pattern) {

		ArrayList<AttributeProcessor> attributeprocessors = new ArrayList<>();
		for (Processor processor: processors) {
			if (processor instanceof AttributeProcessor && processorMatchesPattern(processor, pattern)) {
				attributeprocessors.add((AttributeProcessor)processor);
			}
		}
		return attributeprocessors;
	}

	/**
	 * Initialize the processor cache.
	 * 
	 * @param files An array of XML file paths describing the dialects and their
	 * 				processors to store.
	 */
	public static void initialize(String... files) {

		XMLReader<Dialect> xmlreader = new XMLReader<>(Dialect.class);

		for (String file: files) {
			Dialect dialect = xmlreader.readXMLData(StandardAttributeCache.class.getClassLoader()
					.getResourceAsStream(file));

			// Link the processor with the dialect
			for (Processor processor: dialect.getProcessors()) {
				processor.setDialect(dialect);
				processors.add(processor);
			}
		}
	}

	/**
	 * Checks if the processor name (prefix : name) matches the given
	 * start-of-string pattern.
	 * 
	 * @param processor
	 * @param pattern
	 * @return <tt>true</tt> if the pattern matches against the processor prefix
	 * 		   and name.
	 */
	private static boolean processorMatchesPattern(Processor processor, String pattern) {

		return pattern != null && (processor.getDialect().getPrefix() + ":" + processor.getName()).startsWith(pattern);
	}
}
