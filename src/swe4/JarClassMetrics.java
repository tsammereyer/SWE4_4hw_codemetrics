package swe4;

public class JarClassMetrics {
	private String className;
	private int publicMethods;
	private int protectedMethods;
	private int privateMethods;
	private int numberOfInterfaces;
	private int numberOfSuperclasses;
	private double averageParametersPerMethod;

	public JarClassMetrics(String className) {
		this.className = className;
	}

	public JarClassMetrics(String className, int publicMethods, int protectedMethods, int privateMethods,
			int numberOfInterfaces, int numberOfSuperclasses, double averageParametersPerMethod) {
		this.className = className;
		this.publicMethods = publicMethods;
		this.protectedMethods = protectedMethods;
		this.privateMethods = privateMethods;
		this.numberOfInterfaces = numberOfInterfaces;
		this.numberOfSuperclasses = numberOfSuperclasses;
		this.averageParametersPerMethod = averageParametersPerMethod;

	}

	public int getPublicMethods() {
		return publicMethods;
	}

	public void setPublicMethods(int publicMethods) {
		this.publicMethods = publicMethods;
	}

	public int getProtectedMethods() {
		return protectedMethods;
	}

	public void setProtectedMethods(int protectedMethods) {
		this.protectedMethods = protectedMethods;
	}

	public int getPrivateMethods() {
		return privateMethods;
	}

	public void setPrivateMethods(int privateMethods) {
		this.privateMethods = privateMethods;
	}

	public int getNumberOfInterfaces() {
		return numberOfInterfaces;
	}

	public void setNumberOfInterfaces(int numberOfInterfaces) {
		this.numberOfInterfaces = numberOfInterfaces;
	}

	public int getNumberOfSuperclasses() {
		return numberOfSuperclasses;
	}

	public void setNumberOfSuperclasses(int numberOfSuperclasses) {
		this.numberOfSuperclasses = numberOfSuperclasses;
	}

	public double getAverageParametersPerMethod() {
		return averageParametersPerMethod;
	}

	public void setAverageParametersPerMethod(double averageParametersPerMethod) {
		this.averageParametersPerMethod = averageParametersPerMethod;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Class: " + className + "\n");
		sb.append("  Nr of Interfaces: " + numberOfInterfaces + "\n");
		sb.append("  Nr of Superclasses: " + numberOfSuperclasses + "\n");
		sb.append("  Methods:\n");
		sb.append("    Public: " + publicMethods + "\n");
		sb.append("    Protected: " + protectedMethods + "\n");
		sb.append("    Private: " + privateMethods + "\n");
		sb.append("    Avg. Paramters per Method: " + averageParametersPerMethod + "\n");
		return sb.toString();
	}

}