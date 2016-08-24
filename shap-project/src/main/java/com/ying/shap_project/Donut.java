package com.ying.shap_project;

import java.util.List;

import com.ying.shap_project.exception.IllegalShapeArgumentException;
import com.ying.shap_project.utils.ObjectUtils;
import com.ying.shap_project.utils.math.MathUtils;
import com.ying.shap_project.utils.math.Point;

public class Donut extends Shape {

	private Circle smallCircle;
	private Circle bigCircle;
	
	public Donut () {
		
	}
	
	public Donut (List<String> args) {
		initializedShape(args);
	}

	@Override
	public double getArea() {
		return bigCircle.getArea() - smallCircle.getArea();
	}

	@Override
	public boolean isInclude(Point point) {
		double distance = MathUtils.distance(getLocatedPoint(), point);
		
		if (distance >= smallCircle.getRadius() && distance <= bigCircle.getRadius())
			return true;
		
		return false;
	}

	@Override
	public boolean validateArgs(List<String> args) {
		if (args.size() != 5) {
			throw new IllegalShapeArgumentException(
					"Wrong number of donut arguments. Needs 5 but " + args.size() + " " + args);
		}

		for (int i = 1; i < args.size(); i++) {
			if (!ObjectUtils.isNumber(args.get(i))) {
				throw new IllegalShapeArgumentException(args.get(i) + " is not a number. " + args);
			}
		}

		if (Double.valueOf(args.get(3)) <= 0)
			throw new IllegalShapeArgumentException("Radius(" + args.get(3) + ") can not be negative or 0. " + args);
		if (Double.valueOf(args.get(4)) <= 0)
			throw new IllegalShapeArgumentException("Radius(" + args.get(4) + ") can not be negative or 0. " + args);
		return true;
	}

	@Override
	public void convertArgs(List<String> args) {
		Point center = new Point(Double.valueOf(args.get(1)), Double.valueOf(args.get(2)));
		
		setLocatedPoint(center);
		setSmallCircle(new Circle(center, Double.valueOf(args.get(3))));
		setBigCircle(new Circle(center, Double.valueOf(args.get(4))));
	}

	@Override
	public String printShape() {
		return getName() + ": donut with centre at(" + getLocatedPoint().getX() + ", " + getLocatedPoint().getY() + ") and radiuses "
				+ getSmallCircle().getRadius() + ", " + getBigCircle().getRadius();
	}

	public Circle getSmallCircle() {
		return smallCircle;
	}

	public void setSmallCircle(Circle smallCircle) {
		this.smallCircle = smallCircle;
	}

	public Circle getBigCircle() {
		return bigCircle;
	}

	public void setBigCircle(Circle bigCircle) {
		this.bigCircle = bigCircle;
	}

}
