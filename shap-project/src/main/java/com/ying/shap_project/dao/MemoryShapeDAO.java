package com.ying.shap_project.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ying.shap_project.Shape;
import com.ying.shap_project.ShapePool;
import com.ying.shap_project.utils.math.Point;

public class MemoryShapeDAO implements IShapeDAO{
	
	@Override
	public void add(Shape shape) {
		ShapePool.getInstance().add(shape);
	}

	@Override
	public List<Shape> searchByPoint(Point point) {
		Iterator<Shape> iterator = ShapePool.getInstance().getIterator();
		List<Shape> result = new ArrayList<Shape>();
		
		while (iterator.hasNext()) {
			Shape shape = iterator.next();
			
			if (shape.isInclude(point)) 
				result.add(shape);
		}
		
		return result;
	}

}
