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
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Auto-completion proposal generator for the Thymeleaf Standard attribute
 * processors.
 * 
 * @author Emanuel Rabina
 */
@SuppressWarnings("restriction")
public class StandardAttributeCompletionProposalComputer implements ICompletionProposalComputer {

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List computeCompletionProposals(CompletionProposalInvocationContext context, IProgressMonitor monitor) {

		ArrayList<ICompletionProposal> proposals = new ArrayList<>();
		try {
			// Get the text entered before the cursor of this auto-completion invocation
			String pattern = getPattern(context.getDocument(), context.getInvocationOffset());

			Node node = (Node)ContentAssistUtils.getNodeAt(context.getViewer(), context.getInvocationOffset());
			if (node instanceof IDOMElement) {
				List<AttributeProcessor> processors = StandardAttributeCache.getAttributeProcessors(pattern);
				for (AttributeProcessor processor: processors) {
					proposals.add(new StandardAttributeCompletionProposal(
							processor.getDialect().getPrefix(), processor.getName(),
							pattern.length(), context.getInvocationOffset()));
				}
			}
		}
		catch (BadLocationException ex) {
			// Do nothing, return the empty list of proposals
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
	 * Return the pattern to match a processor against, if the text before the
	 * current document position constitutes a processor name.
	 * 
	 * @param document
	 * @param offset
	 * @return The text entered up to the document offset, if the text could
	 * 		   constitute a processor name.
	 * @throws BadLocationException
	 */
	private static String getPattern(IDocument document, int offset) throws BadLocationException {

		// Trace backwards from the cursor until we hit a character that can't be in a processor name
		int position = offset;
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
