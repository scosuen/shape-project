package com.ying.shap_project;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.ying.shap_project.exception.IllegalShapeArgumentException;

public class ShapeService {

	public Shape createShape(List<String> args) {
		String shapeType = args.get(0);
		Shape shape = null;
		if ("circle".equals(shapeType))
			shape = new Circle(args);

		if (shape != null)
			shape.setName(args.get(0) + "_" + UUID.randomUUID());

		return shape;
	}

	public Shape inputByCommand(List<String> args) {
		Shape shape = createShape(args);
		if (shape == null)
			return null;
//		ShapePool.getInstance().add(shape);

		return shape;
	}
	
	public void inputByFile(String file)  throws IOException {
		
	}
	
	public List<Shape> searchByPoint(double x, double y) {
//		return ShapePool.getInstance().searchByPoint(x, y);
		return null;
	}
}
