package ad.processor;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;
import javax.tools.JavaFileObject;

import ad.annotation.GenerateImpl;
import ad.appenders.CodeAppender;
import ad.model.ClassToGenerate;
import ad.model.MethodModel;
import ad.subprocessors.MethodSubProcessor;

import com.google.auto.service.AutoService;

@SupportedAnnotationTypes("ad.annotation.GenerateImpl")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor
{
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
	{
		List<MethodModel> methods = new ArrayList<MethodModel>();
		roundEnv.getElementsAnnotatedWith(GenerateImpl.class).forEach((oneOfElements) -> {
			if (oneOfElements instanceof TypeElement)
			{
				ClassToGenerate classToGenerate = new ClassToGenerate();
				TypeElement typeElement = (TypeElement) oneOfElements;
				classToGenerate.setClassName(typeElement.getSimpleName() + "Impl");
				PackageElement packagelement = (PackageElement) oneOfElements.getEnclosingElement();
				classToGenerate.setPackageStatement(packagelement.getQualifiedName().toString());
				typeElement.getEnclosedElements().forEach((enclosedElement) -> {
					if (enclosedElement.getKind().toString().equalsIgnoreCase("METHOD"))
						MethodSubProcessor.processMethod(enclosedElement, methods);
				});
				classToGenerate.setMethods(methods);
				generateImpl(classToGenerate);
			}
		});
		return true;
	}

	private void generateImpl(ClassToGenerate classToGenerate)
	{
		try
		{
			final JavaFileObject fileObject = processingEnv.getFiler().createSourceFile(classToGenerate.getPackageStatement() + "." + classToGenerate.getClassName());
			try (Writer writter = fileObject.openWriter())
			{
				writter.append("package " + classToGenerate.getPackageStatement() + ";");
				writter.append('\n');
				CodeAppender.appendImports(writter, classToGenerate);
				writter.append('\n');
				writter.append("public class " + classToGenerate.getClassName() + " {");
				writter.append('\n');
				CodeAppender.appendMethods(writter, classToGenerate);
				writter.append("}");
			}
		} catch (final IOException ex)
		{
			processingEnv.getMessager().printMessage(Kind.ERROR, ex.getMessage());
		}
	}
}
