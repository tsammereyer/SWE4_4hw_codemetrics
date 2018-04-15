package swe4;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.lang.reflect.Method;

public class Codemetrics {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		importJarFile();
	}

	public static void importJarFile() throws IOException, ClassNotFoundException {
		String jarFileName = "heap.jar";
		JarFile jf = new JarFile(new File(jarFileName));

		ArrayList<String> classNames = new ArrayList<>();

		String workingDir = System.getProperty("user.dir");
		URL urlToJarFile = new URL("file://" + workingDir + "/" + jarFileName);
		URLClassLoader cl = new URLClassLoader(new URL[] { urlToJarFile });

		createArrayListOfClasses(jf, classNames);

		for (int i = 0; i < classNames.size(); i++) {

			Class<?> javaClass = cl.loadClass(classNames.get(i));
			System.out.println("Class: " + javaClass.getName());

			getAndCountSuperClasses(javaClass);
			getAndCountInterfaces(javaClass);
			getAndCountMethods(javaClass);

		}

		cl.close();
	}

	private static void getAndCountSuperClasses(Class<?> javaClass) {
		int superClassCounter = -1; // because java.lang.Object wont be counted
		Class<?> temp = javaClass;

		while (temp.getSuperclass() != null) {
			superClassCounter++;
			temp = temp.getSuperclass();
		}

		// for (JavaClass jc : javaClass.getSuperClasses()) {
		// System.out.println(jc.toString());
		// superClassCounter++;
		// }
		System.out.println("    Nr of Super Classes: " + superClassCounter);
	}

	private static void getAndCountInterfaces(Class<?> javaClass) {
		int interfaceCounter = 0;
		for (Class<?> jc : javaClass.getInterfaces()) {
			interfaceCounter++;
		}
		System.out.println("    Nr of Interfaces: " + interfaceCounter);
	}

	private static void getAndCountMethods(Class<?> javaClass) {
		int publicCounter = 0;
		int protectedCounter = 0;
		int privateCounter = 0;
		int params = 0;

		Method[] allMethods = javaClass.getDeclaredMethods();

		System.out.println("  Methods:");

		for (int i = 0; i < allMethods.length; i++) {
			if (allMethods[i].toString().startsWith("public"))
				publicCounter++;
			if (allMethods[i].toString().startsWith("protected"))
				protectedCounter++;
			if (allMethods[i].toString().startsWith("private"))
				privateCounter++;

			params = getAndCountParams(params, allMethods[i]);

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

	private static void createArrayListOfClasses(JarFile jf, ArrayList<String> classNames) throws IOException {
		Enumeration<JarEntry> classes = jf.entries();
		JarEntry element;
		while (classes.hasMoreElements()) {
			element = classes.nextElement();
			if (element.getName().endsWith(".class")) { // !element.isDirectory()
				System.out.println(element.getName());
				String classFileName = element.getName();
				classFileName = classFileName.substring(0, classFileName.length() - 6);
				classFileName = classFileName.replace("/", ".");
				classNames.add(classFileName);
			}

		}
		jf.close();
	}
}
