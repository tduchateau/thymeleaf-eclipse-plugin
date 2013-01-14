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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.namespace.QName;

/**
 * A basic in-memory store of all the Thymeleaf processors.
 * 
 * @author Emanuel Rabina
 */
public class ProcessorCache {

	private static ArrayList<Processor> processors = new ArrayList<Processor>();

	/**
	 * Retrieve the processor with the matching dialect prefix and name.
	 * 
	 * @param prefix Dialect prefix.
	 * @param name	 Name of the processor.
	 * @return Processor for the given prefix and name, or <tt>null</tt> if no
	 * 		   processor matches.
	 */
	public static Processor getProcessor(String prefix, String name) {

		for (Processor processor: processors) {
			if (processor.getDialect().getPrefix().equals(prefix) && processor.getName().equals(name)) {
				return processor;
			}
		}
		return null;
	}

	/**
	 * Retrieve all attribute processors whose names match the given start
	 * pattern.
	 * 
	 * @param namespaces List of namespaces to filter the results down to.
	 * @param pattern	 Start-of-string pattern to match.
	 * @return List of all attribute processors in the standard dialect.
	 */
	public static List<AttributeProcessor> getAttributeProcessors(List<QName> namespaces, String pattern) {

		ArrayList<AttributeProcessor> attributeprocessors = new ArrayList<AttributeProcessor>();
		for (Processor processor: processors) {
			if (processor instanceof AttributeProcessor &&
				processorInNamespace(processor, namespaces) &&
				processorMatchesPattern(processor, pattern)) {
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

		XMLReader<Dialect> xmlreader = new XMLReader<Dialect>(Dialect.class);

		for (String file: files) {
			Dialect dialect = xmlreader.readXMLData(ProcessorCache.class.getClassLoader()
					.getResourceAsStream(file));

			// Link the processor with it's dialect
			for (Processor processor: dialect.getProcessors()) {
				processor.setDialect(dialect);
				processors.add(processor);
			}

			// Ensure processors are in alphabetical order
			Collections.sort(processors, new Comparator<Processor>() {
				@Override
				public int compare(Processor p1, Processor p2) {
					return p1.getName().compareTo(p2.getName());
				}
			});
		}
	}

	/**
	 * Checks if the processor's dialect is in the list of given namespaces.
	 * 
	 * @param processor
	 * @param namespaces
	 * @return <tt>true</tt> if the processor's dialect prefix and namespace are
	 * 		   listed in the <tt>namespaces</tt> collection.
	 */
	private static boolean processorInNamespace(Processor processor, List<QName> namespaces) {

		Dialect dialect = processor.getDialect();
		for (QName namespace: namespaces) {
			if (dialect.getPrefix().equals(namespace.getPrefix()) &&
				dialect.getNamespaceUri().equals(namespace.getNamespaceURI())) {
				return true;
			}
		}
		return false;
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
