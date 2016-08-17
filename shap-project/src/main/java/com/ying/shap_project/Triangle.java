package com.ying.shap_project;

import java.util.List;

import com.ying.shap_project.exception.IllegalShapeArgumentException;
import com.ying.shap_project.utils.ObjectUtils;
import com.ying.shap_project.utils.math.MathUtils;
import com.ying.shap_project.utils.math.Point;

public class Triangle extends Shape {

	private Point vertex1;
	private Point vertex2;

	public Triangle() {

	}

	public Triangle(List<String> args) {
		initializedShape(args);
	}

	@Override
	public double getArea() {
		return triangleArea(getLocatedPoint(), getVertex1(), getVertex2());
	}

	private double triangleArea(Point vertexA, Point vertexB, Point vertexC) {
		double side1, side2, side3;
		side1 = MathUtils.distance(vertexA, vertexB);
		side2 = MathUtils.distance(vertexB, vertexC);
		side3 = MathUtils.distance(vertexC, vertexA);
		double p = (side1 + side2 + side3) / 2;
		return Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
	}

	@Override
	public boolean isInclude(Point point) {
		if (triangleArea(getLocatedPoint(), getVertex1(),
				getVertex2()) < triangleArea(getLocatedPoint(), getVertex1(), point)
						+ triangleArea(getVertex1(), getVertex2(), point)
						+ triangleArea(getVertex2(), point, getLocatedPoint()))
			return false;
		return true;
	}

	@Override
	public boolean validateArgs(List<String> args) {
		if (args.size() != 7) {
			throw new IllegalShapeArgumentException(
					"Wrong number of triangle arguments. Needs 7 but " + args.size() + " " + args);
		}

		for (int i = 1; i < args.size(); i++) {
			if (!ObjectUtils.isNumber(args.get(i))) {
				throw new IllegalShapeArgumentException(args.get(i) + " is not a number. " + args);
			}
		}
		return true;
	}

	@Override
	public void convertArgs(List<String> args) {
		setLocatedPoint(new Point(Double.valueOf(args.get(1)), Double.valueOf(args.get(2))));
		setVertex1(new Point(Double.valueOf(args.get(3)), Double.valueOf(args.get(4))));
		setVertex2(new Point(Double.valueOf(args.get(5)), Double.valueOf(args.get(6))));
	}

	@Override
	public String printShape() {
		return getName() + ": triangle with a vertex at(" + getLocatedPoint().getX() + ", " + getLocatedPoint().getY()
				+ ") and other 2 vertices (" + getVertex1().getX() + ", " + getVertex1().getY() + ") and ("
				+ getVertex2().getX() + ", " + getVertex2().getY() + ")";
	}

	public Point getVertex1() {
		return vertex1;
	}

	public void setVertex1(Point vertex1) {
		this.vertex1 = vertex1;
	}

	public Point getVertex2() {
		return vertex2;
	}

	public void setVertex2(Point vertex2) {
		this.vertex2 = vertex2;
	}

}
