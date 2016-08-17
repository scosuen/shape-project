package com.ying.shap_project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import com.ying.shap_project.dao.IShapeDAO;
import com.ying.shap_project.service.ShapeService;

public class ImportFileThread implements Runnable {
	
	private String file;
	
	private FileInputStream inputStream = null;
	private Scanner scanner = null;
	private ShapeService shapeService;
	public IShapeDAO shapeDAO;
	
	public ImportFileThread (String file, ShapeService shapeService, IShapeDAO shapeDAO) {
		this.file = file;
		this.shapeService = shapeService;
		this.shapeDAO = shapeDAO;
	}

	@Override
	public void run() {
		loadFile();
	}
	
	private void loadFile () {
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		scanner = new Scanner(inputStream, "UTF-8");
		
		while (scanner.hasNextLine()) {
			createShapeByCommand(scanner.nextLine());
		}
	}
	
	private Shape createShapeByCommand (String shapeCommand) {
		Shape shape = shapeService.createShape(Arrays.asList(shapeCommand.split(",")));
		if (shape != null)
			
			System.out.println("insert shape." + shape.printShape());
		
		return shape;
	}

}
