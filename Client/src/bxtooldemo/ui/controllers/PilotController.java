package bxtooldemo.ui.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bxtooldemo.adapter.uimodels.Circle;
import bxtooldemo.adapter.uimodels.Rectangle;
import bxtooldemo.adapter.uimodels.Workspace;


/**
 * Servlet implementation class PilotController
 */
@WebServlet("/PilotController")
public class PilotController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int blockSize = 0;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PilotController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Initializer initializer = new Initializer();
//		this.blockSize = initializer.initialize(500, 500);
//		
//		request.setAttribute("blocksize", this.blockSize);
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("GUI.jsp");
//		requestDispatcher.forward(request, response);
		Gson gson = new Gson();
		String json;
		Circle data = null;
		if (request.getParameter("loadProds") != null) {
		
			json = request.getParameter("jsonData");
			 
			 data = gson.fromJson(json, Circle.class);
		}
		
		Rectangle rect = new Rectangle();
		rect.setxIndex((int) Math.floor(data.getPosX()/100));
		rect.setyIndex((int) Math.floor(data.getPosY()/100));
		if(rect.getyIndex() < 0) rect.setyIndex(0);
		if(rect.getxIndex() < 0) rect.setxIndex(0);
		rect.setId("button_"+ rect.getxIndex() +"_"+ rect.getyIndex());
		rect.setFillColor("#ffff00");
		
		//System.out.println(rect.id);
		response.setContentType("application/json;charset=UTF-8"); 
		response.getWriter().println( gson.toJson(rect));
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   Circle c1 = new Circle();
	   c1.setId("sink1");
	   c1.setPosX(180);
	   c1.setPosY(270);
	   c1.setFillColor("#f55");
	   
	   Workspace w1 = new Workspace();
	   
	   w1.setObjects(c1);

     if (w1.getObjects() != null){
	
	   //ServletOutputStream outputStream = response.getOutputStream();
    	//outputStream.print(new Gson().toJson(w1.objects));
    	 
	   response.setContentType("application/json;charset=UTF-8");
	   response.getWriter().println( new Gson().toJson( w1.getObjects()));
     }
	  
	}
	
	

}