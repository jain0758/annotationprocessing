package ad.appenders;

import static ad.subprocessors.Constants.IMPORT;
import static ad.subprocessors.Constants.SEMICOLON;
import static ad.subprocessors.Constants.NEXT_LINE;
import static ad.subprocessors.Constants.SPACE;
import static ad.subprocessors.Constants.OPEN_BRACE;
import static ad.subprocessors.Constants.CLOSE_BRACE;
import static ad.subprocessors.Constants.OPEN_METHOD_BRACE;
import static ad.subprocessors.Constants.CLOSING_METHOD_BRACE;
import static ad.subprocessors.Constants.METHOD_BRACE;
import static ad.subprocessors.Constants.THROWS;
import static ad.subprocessors.Constants.BLANK;

import java.io.Writer;

import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.ReferenceType;
import javax.lang.model.type.TypeMirror;

import ad.model.ClassToGenerate;

public class CodeAppender
{
	public static void appendImports(Writer writter, ClassToGenerate classToGenerate)
	{
		classToGenerate.getMethods().forEach((method) -> {
			try
			{
				if (method.getImportStatements() != null)
				{
					writter.append(IMPORT + method.getImportStatements() + SEMICOLON);
					writter.append(NEXT_LINE);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		});
	}

	public static void appendMethods(Writer writter, ClassToGenerate classToGenerate)
	{
		classToGenerate.getMethods().forEach((method) -> {
			try
			{
				writter.append(notNull(method.getMethodReturnType()) + SPACE);
				writter.append(notNull(method.getMethodName()) + SPACE);
				if (method.getMethodParams() != null)
				{
					writter.append(OPEN_BRACE);
					writter.append(notNull(method.getMethodParams()));
					writter.append(CLOSE_BRACE);
				}
				else
				{
					writter.append(METHOD_BRACE);
				}
				if(method.getMethodThrows() != null) {
					writter.append(THROWS);
					writter.append(notNull(method.getMethodThrows()));
				}
				writter.append(OPEN_METHOD_BRACE);
				writter.append(NEXT_LINE);
				writter.append(getReturnType(method.getReturnTypeMirror()));
				writter.append(NEXT_LINE);
				writter.append(CLOSING_METHOD_BRACE);
				writter.append(NEXT_LINE);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		});
	}
	
	private static String getReturnType(TypeMirror mirror) {
		if(mirror instanceof PrimitiveType) {
			return "return 0;";
		} else if(mirror instanceof ReferenceType) {
			return "return null;";
		}
		return "";
	}

	private static String notNull(String s)
	{
		if (s != null)
			return s;
		return BLANK;
	}
}
