package com.ying.shap_project;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ShapePool {
	private volatile static ShapePool INSTANCE = null;

	private List<Shape> pool = new CopyOnWriteArrayList<Shape>();
	
	private ShapePool() {

	}

	public static ShapePool getInstance() {
		if (INSTANCE == null) {
			synchronized (ShapePool.class) {
				if (INSTANCE == null) {
					INSTANCE = new ShapePool();
				}
			}
		}
		return INSTANCE;
	}
	
	public void add (Shape shape) {
		pool.add(shape);
	}
	
	public Iterator<Shape> getIterator () {
		return pool.iterator();
	}
}
