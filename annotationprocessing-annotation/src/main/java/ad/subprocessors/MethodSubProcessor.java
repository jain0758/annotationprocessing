package ad.subprocessors;

import static ad.subprocessors.Constants.BLANK;
import static ad.subprocessors.Constants.COMMA;
import static ad.subprocessors.Constants.SPACE;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.ReferenceType;
import javax.lang.model.type.TypeMirror;

import ad.model.MethodModel;

public class MethodSubProcessor {
	
	public static void processMethod(Element element, List<MethodModel> methods)
	{
		MethodModel methodModel = new MethodModel();
		ExecutableElement executableElement = (ExecutableElement) element;
		methodModel.setReturnTypeMirror(executableElement.getReturnType());
		methodModel.setMethodReturnType(processImport(executableElement.getReturnType(),methodModel));		
		methodModel.setMethodName(executableElement.getSimpleName().toString());
		if (executableElement.getParameters().size() != 0)
		{
			methodModel.setMethodParams(parseParams(executableElement.getParameters(),methodModel));
		}		
		if (executableElement.getThrownTypes().size() != 0)
		{
			methodModel.setMethodThrows(parseThrows(executableElement.getThrownTypes(), methodModel));
		}
		System.out.println(methodModel);
		methods.add(methodModel);		
	}

	private static String parseParams(List<? extends VariableElement> elements, MethodModel methodModel)
	{
		if (elements.size() != 0)
		{
			StringBuffer sf = new StringBuffer();
			elements.forEach((variableEle) -> {				
				sf.append(processImport(variableEle.asType(),methodModel));
				sf.append(SPACE);
				sf.append(variableEle.getSimpleName().toString());
				sf.append(COMMA);
			});
			return sf.toString().substring(0, sf.length() - 1);
		}
		return BLANK;
	}

	private static String parseThrows(List<? extends TypeMirror> elements,MethodModel methodModel)
	{
		if (elements.size() != 0)
		{
			StringBuffer sf = new StringBuffer();
			elements.forEach((variableEle) -> {
				sf.append(processImport(variableEle,methodModel));
				sf.append(COMMA);
			});
			return sf.toString().substring(0, sf.length() - 1);
		}
		return BLANK;
	}
	
	private static String processImport(TypeMirror element, MethodModel methodModel) {
		if(element instanceof ReferenceType && !(element.toString().startsWith("java.lang"))) {
			methodModel.setImportStatements(element.toString());
		}
		return element.toString();
	}
}