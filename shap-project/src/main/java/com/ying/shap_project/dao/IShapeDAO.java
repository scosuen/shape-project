package com.ying.shap_project.dao;

import java.util.List;

import com.ying.shap_project.Shape;
import com.ying.shap_project.utils.math.Point;

public interface IShapeDAO {
	
	public void add (Shape shape);
	
	public List<Shape> searchByPoint (Point point);

}
