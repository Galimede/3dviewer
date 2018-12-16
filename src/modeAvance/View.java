package modeAvance;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import IHM.ControllerAvance;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import main.Face;
import main.Model;

public class View implements Observer{
	
	Model model;
	ControllerAvance controller; 
	
	GraphicsContext gc;
	Canvas canvas;
	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	
	public View(Model model, ControllerAvance controller) {
		this.model=model;
		model.addObserver(this);
		this.controller=controller;
		controller.addView(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
