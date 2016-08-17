package com.ying.shap_project;

import java.io.Serializable;
import java.util.List;

import com.ying.shap_project.exception.IllegalShapeArgumentException;
import com.ying.shap_project.utils.math.Point;

public abstract class Shape implements Serializable {
	
	private String name;
	private Point locatedPoint;
	
	public abstract double getArea();
	public abstract boolean isInclude(double x, double y);
	
	public abstract boolean validateArgs (List<String> args) throws IllegalShapeArgumentException;
	public abstract void convertArgs (List<String> args);
	public abstract String printShape ();
	
	public void initializedShape (List<String> args) throws IllegalShapeArgumentException {
		validateArgs(args);
		convertArgs(args);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Point getLocatedPoint() {
		return locatedPoint;
	}
	public void setLocatedPoint(Point locatedPoint) {
		this.locatedPoint = locatedPoint;
	}
}
