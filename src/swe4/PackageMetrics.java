package swe4;

import java.util.ArrayList;

public class PackageMetrics {
	private String packageName;

	private int minPublicMethods;
	private int maxPublicMethods;
	private double avgPublicMethods;

	private int minProtectedMethods;
	private int maxProtectedMethods;
	private double avgProtectedMethods;

	private int minPrivateMethods;
	private int maxPrivateMethods;
	private double avgPrivateMethods;

	private int minNumberOfInterfaces;
	private int maxNumberOfInterfaces;
	private double avgNumberOfInterfaces;

	private int minNumberOfSuperclasses;
	private int maxNumberOfSuperclasses;
	private double avgNumberOfSuperclasses;

	private double minAverageParametersPerMethod;
	private double maxAverageParametersPerMethod;
	private double avgAverageParametersPerMethod;

	private int amountClasses;

	public PackageMetrics(String packageName, ArrayList<JarClassMetrics> jarClassMetricsList) {
		this.packageName = packageName;
		minPublicMethods = Integer.MAX_VALUE;
		maxPublicMethods = Integer.MIN_VALUE;

		minProtectedMethods = Integer.MAX_VALUE;
		maxProtectedMethods = Integer.MIN_VALUE;

		minPrivateMethods = Integer.MAX_VALUE;
		maxPrivateMethods = Integer.MIN_VALUE;

		minNumberOfInterfaces = Integer.MAX_VALUE;
		maxNumberOfInterfaces = Integer.MIN_VALUE;

		minNumberOfSuperclasses = Integer.MAX_VALUE;
		maxNumberOfSuperclasses = Integer.MIN_VALUE;

		minAverageParametersPerMethod = Double.MAX_VALUE;
		maxAverageParametersPerMethod = Double.MIN_VALUE;

		int sumPublicMethods = 0;
		int sumProtectedMethods = 0;
		int sumPrivateMethods = 0;
		int sumNumberOfInterfaces = 0;
		int sumNumberOfSuperclasses = 0;
		double sumAverageParametersPerMethod = 0;

		for (JarClassMetrics jcm : jarClassMetricsList) {
			sumPublicMethods += jcm.getPublicMethods();
			if (minPublicMethods > jcm.getPublicMethods())
				minPublicMethods = jcm.getPublicMethods();
			if (maxPublicMethods < jcm.getPublicMethods())
				maxPublicMethods = jcm.getPublicMethods();

			sumProtectedMethods += jcm.getProtectedMethods();
			if (minProtectedMethods > jcm.getProtectedMethods())
				minProtectedMethods = jcm.getProtectedMethods();
			if (maxProtectedMethods < jcm.getProtectedMethods())
				maxProtectedMethods = jcm.getProtectedMethods();

			sumPrivateMethods += jcm.getPrivateMethods();
			if (minPrivateMethods > jcm.getPrivateMethods())
				minPrivateMethods = jcm.getPrivateMethods();
			if (maxPrivateMethods < jcm.getPrivateMethods())
				maxPrivateMethods = jcm.getPrivateMethods();

			sumNumberOfInterfaces += jcm.getNumberOfInterfaces();
			if (minNumberOfInterfaces > jcm.getNumberOfInterfaces())
				minNumberOfInterfaces = jcm.getNumberOfInterfaces();
			if (maxNumberOfInterfaces < jcm.getNumberOfInterfaces())
				maxNumberOfInterfaces = jcm.getNumberOfInterfaces();

			sumNumberOfSuperclasses += jcm.getNumberOfSuperclasses();
			if (minNumberOfSuperclasses > jcm.getNumberOfSuperclasses())
				minNumberOfSuperclasses = jcm.getNumberOfSuperclasses();
			if (maxNumberOfSuperclasses < jcm.getNumberOfSuperclasses())
				maxNumberOfSuperclasses = jcm.getNumberOfSuperclasses();

			sumAverageParametersPerMethod += jcm.getAverageParametersPerMethod();
			if (minAverageParametersPerMethod > jcm.getNumberOfSuperclasses())
				minAverageParametersPerMethod = jcm.getNumberOfSuperclasses();
			if (maxAverageParametersPerMethod < jcm.getNumberOfSuperclasses())
				maxAverageParametersPerMethod = jcm.getNumberOfSuperclasses();

			amountClasses++;
		}

		avgPublicMethods = sumPublicMethods / amountClasses;

		avgProtectedMethods = sumProtectedMethods / amountClasses;

		avgPrivateMethods = sumPrivateMethods / amountClasses;

		avgNumberOfInterfaces = sumNumberOfInterfaces / amountClasses;

		avgNumberOfSuperclasses = sumNumberOfSuperclasses / amountClasses;

		avgAverageParametersPerMethod = sumAverageParametersPerMethod / amountClasses;

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Package: " + packageName + "\n");
		sb.append("Nr of Classes: " + amountClasses + "\n");
		sb.append("-----------------------------------------------------\n");
		sb.append("  Min PublicMethods: " + minPublicMethods + "\n");
		sb.append("  Max PublicMethods: " + maxPublicMethods + "\n");
		sb.append("  Avg PublicMethods: " + avgPublicMethods + "\n");
		sb.append("-----------------------------------------------------\n");
		sb.append("  Min ProtectedMethods: " + minProtectedMethods + "\n");
		sb.append("  Max ProtectedMethods: " + maxProtectedMethods + "\n");
		sb.append("  Avg ProtectedMethods: " + avgProtectedMethods + "\n");
		sb.append("-----------------------------------------------------\n");
		sb.append("  Min PrivateMethods: " + minPrivateMethods + "\n");
		sb.append("  Max PrivateMethods: " + maxPrivateMethods + "\n");
		sb.append("  Avg PrivateMethods: " + avgPrivateMethods + "\n");
		sb.append("-----------------------------------------------------\n");
		sb.append("  Min NumberOfInterfaces: " + minNumberOfInterfaces + "\n");
		sb.append("  Max NumberOfInterfaces: " + maxNumberOfInterfaces + "\n");
		sb.append("  Avg NumberOfInterfaces: " + avgNumberOfInterfaces + "\n");
		sb.append("-----------------------------------------------------\n");
		sb.append("  Min NumberOfSuperclasses: " + minNumberOfSuperclasses + "\n");
		sb.append("  Max NumberOfSuperclasses: " + maxNumberOfSuperclasses + "\n");
		sb.append("  Avg NumberOfSuperclasses: " + avgNumberOfSuperclasses + "\n");
		sb.append("-----------------------------------------------------\n");
		sb.append("  Min AverageParametersPerMethod: " + minAverageParametersPerMethod + "\n");
		sb.append("  Max AverageParametersPerMethod: " + maxAverageParametersPerMethod + "\n");
		sb.append("  Avg AverageParametersPerMethod: " + avgAverageParametersPerMethod + "\n");
		sb.append("-----------------------------------------------------\n");

		return sb.toString();
	}
}
