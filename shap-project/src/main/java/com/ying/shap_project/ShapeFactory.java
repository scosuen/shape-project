package com.ying.shap_project;

import java.util.List;
import java.util.UUID;

import com.ying.shap_project.exception.NoSuchShapeTypeException;

public class ShapeFactory {

	public static Shape createShape(List<String> args) {
		String shapeType = args.get(0);
		Shape shape = null;
		if ("circle".equals(shapeType))
			shape = new Circle(args);
		else if ("rectangle".equals(shapeType))
			shape = new Rectangle(args);
		else if ("triangle".equals(shapeType))
			shape = new Triangle(args);

		if (shape != null)
			shape.setName(args.get(0) + "_" + UUID.randomUUID());
		else
			throw new NoSuchShapeTypeException("No such type : " + shapeType);

		return shape;
	}
}
