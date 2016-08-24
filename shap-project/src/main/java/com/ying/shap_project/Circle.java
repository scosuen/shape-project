package com.ying.shap_project;

import java.util.List;

import com.ying.shap_project.exception.IllegalShapeArgumentException;
import com.ying.shap_project.utils.ObjectUtils;
import com.ying.shap_project.utils.math.MathUtils;
import com.ying.shap_project.utils.math.Point;

public class Circle extends Shape {
	
	private double radius;

	public Circle() {

	}
	
	public Circle(Point point, double raidus) {
		setLocatedPoint(point);
		this.radius = raidus;
	}

	public Circle(List<String> args) {
		initializedShape(args);
	}

	@Override
	public double getArea() {
		return Math.PI * getRadius() * getRadius();
	}

	@Override
	public boolean isInclude(Point point) {
		double distance = MathUtils.distance(getLocatedPoint(), point);
		if (radius >= distance)
			return true;
		return false;
	}

	@Override
	public boolean validateArgs(List<String> args) {
		if (args.size() != 4) {
			throw new IllegalShapeArgumentException("Wrong number of circle arguments. Needs 4 but " + args.size() + " " + args);
		}

		for (int i = 1; i < args.size(); i++) {
			if (!ObjectUtils.isNumber(args.get(i))) {
				throw new IllegalShapeArgumentException(args.get(i) + " is not a number. " + args);
			}
		}

		if (Double.valueOf(args.get(3)) <= 0)
			throw new IllegalShapeArgumentException("Radius(" + args.get(3) + ") can not be negative or 0. " + args);

		return true;
	}

	@Override
	public void convertArgs(List<String> args) {
		setLocatedPoint(new Point(Double.valueOf(args.get(1)), Double.valueOf(args.get(2))));
		setRadius(Double.valueOf(args.get(3)));
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public String printShape() {
		return getName() + ": circle with centre at(" + getLocatedPoint().getX() + ", " + getLocatedPoint().getY() + ") and radius " + getRadius();
	}

}
