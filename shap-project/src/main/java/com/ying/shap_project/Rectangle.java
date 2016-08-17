package com.ying.shap_project;

import java.util.List;

import com.ying.shap_project.exception.IllegalShapeArgumentException;
import com.ying.shap_project.utils.ObjectUtils;
import com.ying.shap_project.utils.math.Point;

public class Rectangle extends Shape {

	private double sideXLength;
	private double sideYLength;

	public Rectangle() {

	}

	public Rectangle(List<String> args) {
		initializedShape(args);
	}

	@Override
	public double getArea() {
		return getSideXLength() * getSideYLength();
	}

	@Override
	public boolean isInclude(Point point) {
		return point.getX() >= getLocatedPoint().getX() && 
				point.getX() <= (getLocatedPoint().getX() + sideXLength) && 
				point.getY() >= getLocatedPoint().getY() && 
				point.getY() <= (getLocatedPoint().getY() + sideYLength);
	}

	@Override
	public boolean validateArgs(List<String> args) {
		if (args.size() != 5) {
			throw new IllegalShapeArgumentException("Wrong number of rectangle arguments. Needs 5 but " + args.size() + " " + args);
		}
		
		for (int i = 1 ; i < args.size() ; i ++) {
			if (!ObjectUtils.isNumber(args.get(i))) {
				throw new IllegalShapeArgumentException(args.get(i) + " is not a number. " + args);
			}
		}
		if (Double.valueOf(args.get(3)) <= 0) 
			throw new IllegalShapeArgumentException("Side X length (" + args.get(3) + ") can not be negative or 0. " + args);
		if (Double.valueOf(args.get(4)) <= 0) 
			throw new IllegalShapeArgumentException("Side Y length (" + args.get(4) + ") can not be negative or 0. " + args);
		
		return true;
	}

	@Override
	public void convertArgs(List<String> args) {
		setLocatedPoint(new Point(Double.valueOf(args.get(1)), Double.valueOf(args.get(2))));
		setSideXLength(Double.valueOf(args.get(3)));
		setSideYLength(Double.valueOf(args.get(4)));
	}

	@Override
	public String printShape() {
		return getName() + ": rectangle with one corner at("  + getLocatedPoint().getX() + ", " + getLocatedPoint().getY()+ ") and sides " + getSideXLength() + ", " + getSideYLength();
	}

	public double getSideXLength() {
		return sideXLength;
	}

	public void setSideXLength(double sideXLength) {
		this.sideXLength = sideXLength;
	}

	public double getSideYLength() {
		return sideYLength;
	}

	public void setSideYLength(double sideYLength) {
		this.sideYLength = sideYLength;
	}

}
