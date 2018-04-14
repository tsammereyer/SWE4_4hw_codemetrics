package swe4;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.sun.org.apache.bcel.internal.classfile.ClassParser;
import com.sun.org.apache.bcel.internal.classfile.Field;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;

public class Codemetrics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		importJarFile();

	}

	public static void importJarFile() throws IOException {
		String jarFileName = "heap.jar";
		JarFile jarfile = new JarFile(jarFileName);
		JarFile jf = new JarFile(new File(jarFileName));

		Enumeration<JarEntry> entries = jf.entries();
		JarEntry element;
		while (entries.hasMoreElements()) {
			element = entries.nextElement();
			if (element.getName().endsWith(".class")) { // !element.isDirectory()
				System.out.println(element.getName());

				ClassParser parser = new ClassParser(jarFileName, element.getName());
				JavaClass javaClass = parser.parse();

				System.out.println("Class: " + javaClass.getClassName());

				int publicCounter = 0;
				int protectedCounter = 0;
				int privateCounter = 0;
				System.out.println("  Methods:");
				for (Method method : javaClass.getMethods()) {
					if (method.toString().startsWith("public"))
						publicCounter++;
					if (method.toString().startsWith("protected"))
						protectedCounter++;
					if (method.toString().startsWith("private"))
						privateCounter++;

					//System.out.println("    " + method); //name of the method
				}
				System.out.println("    Public: " + publicCounter);
				System.out.println("    Protected: " + protectedCounter);
				System.out.println("    Private: " + privateCounter);
				

				// javaClass.getin
			}

		}
	}

}
