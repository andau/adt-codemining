package abap.codemining.general;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.codemining.ICodeMining;
import org.eclipse.jface.text.codemining.ICodeMiningProvider;

import abap.codemining.method.AbapMethodBody;
import abap.codemining.method.AbapMethodDefinitionExtractor;
import abap.codemining.method.AbapMethodHeader;
import abap.codemining.method.AbapMethodInformation;

public class AbapClassCodeMining {

	public void evaluateCodeMinings(List<ICodeMining> minings, ITextViewer viewer, ICodeMiningProvider provider,
			IDocument doc) {

		AbapMethodDefinitionExtractor abapMethodDefinitionExtractor = new AbapMethodDefinitionExtractor();

		AbapMethodInformation methodInformation = abapMethodDefinitionExtractor.getMethodInformation(doc);

		for (AbapMethodBody methodBody : methodInformation.getAbapMethodBodies()) {
			try {
				String methodLabel = buildMethodLabel(methodBody.getMethodname(),
						methodInformation.getAbapMethodHeaders());
				minings.add(new AbapMethodHeaderCodeMining(methodBody.getLinenumber(), viewer.getDocument(), provider,
						methodLabel));
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String buildMethodLabel(String methodname, Set<AbapMethodHeader> methodHeaders) {
		Set<AbapMethodHeader> matchingMethodHeaders = methodHeaders.stream()
				.filter(item -> item.getMethodname().equals(methodname)).collect(Collectors.toSet());
		return matchingMethodHeaders.size() == 1 ? matchingMethodHeaders.iterator().next().getMethodHeader()
				: "<method undefined>";
	}
}
