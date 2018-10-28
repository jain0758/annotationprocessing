package ad.model;

import java.util.List;

public class ClassToGenerate
{
	private String packageStatement;
	
	private String className;
	
	private List<MethodModel> methods;

	public String getPackageStatement()
	{
		return packageStatement;
	}

	public void setPackageStatement(String packageStatement)
	{
		this.packageStatement = packageStatement;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public List<MethodModel> getMethods()
	{
		return methods;
	}

	public void setMethods(List<MethodModel> methods)
	{
		this.methods = methods;
	}

	@Override
	public String toString()
	{
		return "ClassToGenerate [packageStatement=" + packageStatement + ", className=" + className + ", methods=" + methods + "]";
	}
}
