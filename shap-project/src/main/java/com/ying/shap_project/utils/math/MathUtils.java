package com.ying.shap_project.utils.math;

/**
 * 
 * @author Ying
 *
 */
public class MathUtils {

	public static double distance(Point d1, Point d2) {
		double x, y;
		x = Math.abs(d1.getX() - d2.getX());
		y = Math.abs(d1.getY() - d2.getY());
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

}
