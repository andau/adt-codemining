package org.eclipse.jface.text.revisions.provisionnal;

import org.eclipse.jface.text.revisions.RevisionRange;

public interface IRevisionRangeProvider {

	boolean isInitialized();
	
	/**
	 * Returns the revision range that contains the given line, or <code>null</code>
	 * if there is none.
	 *
	 * @param line the line of interest
	 * @return the corresponding <code>RevisionRange</code> or <code>null</code>
	 */
	RevisionRange getRange(int line);
}