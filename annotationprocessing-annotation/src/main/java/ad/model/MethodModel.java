package ad.model;

import javax.lang.model.type.TypeMirror;

public class MethodModel
{
	private String importStatements;
	
	private String methodAccessSpecifier;
	
	private String methodReturnType;
	
	private String methodName;
	
	private String methodParams;
	
	private String methodThrows;
	
	private TypeMirror returnTypeMirror;

	public String getImportStatements()
	{
		return importStatements;
	}

	public void setImportStatements(String importStatements)
	{
		this.importStatements = importStatements;
	}

	public String getMethodAccessSpecifier()
	{
		return methodAccessSpecifier;
	}

	public void setMethodAccessSpecifier(String methodAccessSpecifier)
	{
		this.methodAccessSpecifier = methodAccessSpecifier;
	}

	public String getMethodReturnType()
	{
		return methodReturnType;
	}

	public void setMethodReturnType(String methodReturnType)
	{
		this.methodReturnType = methodReturnType;
	}

	public String getMethodName()
	{
		return methodName;
	}

	public void setMethodName(String methodName)
	{
		this.methodName = methodName;
	}

	public String getMethodParams()
	{
		return methodParams;
	}

	public void setMethodParams(String methodParams)
	{
		this.methodParams = methodParams;
	}

	public String getMethodThrows()
	{
		return methodThrows;
	}

	public void setMethodThrows(String methodThrows)
	{
		this.methodThrows = methodThrows;
	}

	public TypeMirror getReturnTypeMirror()
	{
		return returnTypeMirror;
	}

	public void setReturnTypeMirror(TypeMirror returnTypeMirror)
	{
		this.returnTypeMirror = returnTypeMirror;
	}

	@Override
	public String toString()
	{
		return "MethodModel [importStatements=" + importStatements + ", methodAccessSpecifier=" + methodAccessSpecifier + ", methodReturnType=" + methodReturnType + ", methodName=" + methodName + ", methodParams=" + methodParams + ", methodThrows=" + methodThrows + "]";
	}
}
