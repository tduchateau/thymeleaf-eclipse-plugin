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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.wst.sse.ui.contentassist.CompletionProposalInvocationContext;
import org.eclipse.wst.sse.ui.contentassist.ICompletionProposalComputer;
import org.eclipse.wst.sse.ui.internal.contentassist.ContentAssistUtils;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

/**
 * Auto-completion proposal generator for the Thymeleaf Standard attribute
 * processors.
 * 
 * @author Emanuel Rabina
 * @since 0.1
 */
@SuppressWarnings("restriction")
public class ProcessorCompletionProposalComputer implements ICompletionProposalComputer {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List computeCompletionProposals(CompletionProposalInvocationContext context, IProgressMonitor monitor) {

		ArrayList<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();

		try {
			IDocument document = context.getDocument();
			int cursorposition = context.getInvocationOffset();

			// Get all the known namespaces for this point in the document
			Node node = (Node)ContentAssistUtils.getNodeAt(context.getViewer(), cursorposition);
			ArrayList<QName> namespaces = getNodeNamespaces(node);

			// Get the text entered before the cursor of this auto-completion invocation
			String pattern = getPattern(document, cursorposition);

			// Collect attribute processors if we're in an HTML element
			if (node instanceof IDOMElement) {

				// Make sure there's some whitespace before new attribute suggestions
				if (!pattern.isEmpty() || (pattern.isEmpty() && (cursorposition == 0 ||
						Character.isWhitespace(document.getChar(cursorposition - 1))))) {

					List<AttributeProcessor> processors = ProcessorCache.getAttributeProcessors(namespaces, pattern);
					for (AttributeProcessor processor: processors) {
						proposals.add(new AttributeProcessorCompletionProposal(
								processor.getDialect().getPrefix(), processor.getName(),
								pattern.length(), cursorposition));
					}
				}

			}
		}
		catch (BadLocationException ex) {
			ex.printStackTrace();
			return null;
		}

		return proposals;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List computeContextInformation(CompletionProposalInvocationContext context, IProgressMonitor monitor) {

		return new ArrayList<IContextInformation>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getErrorMessage() {

		return null;
	}

	/**
	 * Return a list of the namespaces valid at the given node.
	 * 
	 * @param node
	 * @return List of namespaces known to this node.
	 */
	private static ArrayList<QName> getNodeNamespaces(Node node) {

		ArrayList<QName> namespaces = new ArrayList<QName>();

		if (node instanceof Element) {
			NamedNodeMap attributes = node.getAttributes();
			for (int i = 0; i < attributes.getLength(); i++) {
				String name = ((Attr)attributes.item(i)).getName();
				if (name.startsWith("xmlns:")) {
					namespaces.add(new QName(((Element)node).getAttribute(name), "", name.substring(6)));
				}
			}
		}
		Node parent = node.getParentNode();
		if (parent != null) {
			namespaces.addAll(getNodeNamespaces(parent));
		}

		return namespaces;
	}

	/**
	 * Return the pattern to match a processor against, if the text before the
	 * current document position constitutes a processor name.
	 * 
	 * @param document
	 * @param cursorposition
	 * @return The text entered up to the document offset, if the text could
	 * 		   constitute a processor name.
	 * @throws BadLocationException
	 */
	private static String getPattern(IDocument document, int cursorposition) throws BadLocationException {

		// Trace backwards from the cursor until we hit a character that can't be in a processor name
		int position = cursorposition;
		int length = 0;
		while (--position > 0 && isProcessorChar(document.getChar(position))) {
			length++;
		}
		return document.get(position + 1, length);
	}

	/**
	 * Returns whether or not the given character is a valid processor name
	 * character.
	 * 
	 * @param c
	 * @return <tt>true</tt> if <tt>char</tt> is an alphanumeric character, or
	 * 		   one of the following symbols: <tt>: - </tt>
	 */
	private static boolean isProcessorChar(char c) {

		return Character.isLetterOrDigit(c) || c == ':' || c == '-';
	}

	/**
	 * Do nothing.
	 */
	@Override
	public void sessionEnded() {
	}

	/**
	 * Do nothing.
	 */
	@Override
	public void sessionStarted() {
	}
}
