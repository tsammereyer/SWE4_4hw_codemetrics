package swe4;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.sun.org.apache.bcel.internal.classfile.ClassParser;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.util.ClassLoader;

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

				getAndCountSuperClasses(javaClass);
				getAndCountMethods(javaClass);
				getAndCountInterfaces(javaClass);

			}

		}
	}

	private static void getAndCountSuperClasses(JavaClass javaClass) {
		int superClassCounter = 0;
		JavaClass temp = javaClass;

		while (temp.getSuperClass() != null) {
			superClassCounter++;
			// System.out.println(javaClass.getSuperclassName());
			temp = temp.getSuperClass();
		}

		// for (JavaClass jc : javaClass.getSuperClasses()) {
		// System.out.println(jc.toString());
		// superClassCounter++;
		// }
		System.out.println("    Nr of Super Classes: " + superClassCounter);
	}

	private static void getAndCountInterfaces(JavaClass javaClass) {
		System.out.println("  Interfaces:");
		int interfaceCounter = 0;
		for (JavaClass jc : javaClass.getInterfaces()) {
			interfaceCounter++;
		}
		System.out.println("    Nr of Interfaces: " + interfaceCounter);
	}

	private static void getAndCountMethods(JavaClass javaClass) {
		int publicCounter = 0;
		int protectedCounter = 0;
		int privateCounter = 0;
		int params = 0;

		System.out.println("  Methods:");

		for (Method method : javaClass.getMethods()) {
			if (method.toString().startsWith("public"))
				publicCounter++;
			if (method.toString().startsWith("protected"))
				protectedCounter++;
			if (method.toString().startsWith("private"))
				privateCounter++;

			params = getAndCountParams(params, method);

		}
		System.out.println("    Public: " + publicCounter);
		System.out.println("    Protected: " + protectedCounter);
		System.out.println("    Private: " + privateCounter);

		double result = 0.0;
		if (params > 0) {
			result = (publicCounter + protectedCounter + privateCounter * 1.0) / params;
			System.out.println("    Avg. Params: " + result);
		} else {
			System.out.println("    Avg. Params: " + result);
		}
	}

	private static int getAndCountParams(int params, Method method) {
		int idxOpenParenthesis = method.toString().indexOf('(');
		if (method.toString().charAt(idxOpenParenthesis + 1) != ')') {
			// count the occurrences of ,
			if (method.toString().indexOf(',') != -1) {
				// count , -> number of params is 1 plus
				params += method.toString().chars().filter(ch -> ch == ',').count() + 1;
			} else {
				// no , in string --> 1 param
				params++;
			}
		}
		// stuff below for testing reasons
		// System.out.println(" " + method); //name of the method
		// System.out.println("Params: "+params);
		return params;
	}

}
