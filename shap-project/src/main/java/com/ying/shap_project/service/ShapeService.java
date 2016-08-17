package com.ying.shap_project.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ying.shap_project.ImportFileThread;
import com.ying.shap_project.Shape;
import com.ying.shap_project.ShapeFactory;
import com.ying.shap_project.dao.IShapeDAO;
import com.ying.shap_project.dao.MemoryShapeDAO;
import com.ying.shap_project.utils.math.Point;

public class ShapeService {
	
	public IShapeDAO shapeDAO;
	
	public ShapeService () {
		shapeDAO = new MemoryShapeDAO();
	}

	public Shape inputByCommand(List<String> args) {
		Shape shape = ShapeFactory.createShape(args);

		shapeDAO.add(shape);
		
		return shape;
	}
	
	public void inputByFile(String file) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(new ImportFileThread(file, shapeDAO));
	}
	
	public List<Shape> searchByPoint(double x, double y) {
		return shapeDAO.searchByPoint(new Point(x, y));
	}
}
