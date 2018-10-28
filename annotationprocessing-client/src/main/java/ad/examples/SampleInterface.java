package ad.examples;

import java.io.IOException;
import ad.annotation.GenerateImpl;

@GenerateImpl
public interface SampleInterface
{
	String alterName(int a, double b) throws NullPointerException, IOException;
	
	SampleReturnType processName();
	
	int getAge();
}
