package com.bike.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bike.dao.BikeDao;
import com.bike.dto.BikeDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/BikeController")
public class BikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				doPost(request, response);
	}

	//파싱 : 어떤 페이지에서 내가 원하는데이터를 특정 패턴이나 순서로 추출해서 가공하는것 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command= request.getParameter("command");
		System.out.println("["+command+"]");
		
		if (command.equals("view")) {
			System.out.println("1");
			response.sendRedirect("view.html");
			
		}else if (command.equals("getdata")) {
			BikeDao dao=new BikeDao();
			
			if (dao.delete()) {
				System.out.println("db 초기화 성공");
			}else {
				System.out.println("db 초기화 실패");
			}
			String data =request.getParameter("mydata");
			//파싱을 위한 json객체 생성후 파싱 해준다. 
			JsonElement element=JsonParser.parseString(data);
			
			JsonObject jsonData=element.getAsJsonObject();
			
			JsonElement records=jsonData.get("records");
			
			JsonArray recordsArray=records.getAsJsonArray();
			
			List<BikeDto> list=new ArrayList<BikeDto>();
			JsonArray resultArray=new JsonArray();
			
			for (int i=0; i<recordsArray.size(); i++) {
				/*
				 * JsonElement tempElement = recordsArray.get(i);
				 * JsonObject tempObject = tempElement.getAsJsonObject();
				 * JsonElement nameElement = tempObject.get("자전거대여소명");
				 * String name = nameElement.getAsString();
				 * 위에 4줄이 밑에 name 한줄로 압축되어 있는 거. 
				 */
				String name =recordsArray.get(i).getAsJsonObject().get("자전거대여소명").getAsString();
				//일단 선언만하고 
				String addr=null;
				//소재지도로명주소 랑 소재지지번주소 같은항목이라 둘중 어떤걸로 적혀있을지 모름 그래서 if문으로 소재지도로명주소없으면 
				//소재지지번주소 로 addr값 저장해서 출력하기 위해서 if 문 사용했음 
				if (recordsArray.get(i).getAsJsonObject().get("소재지도로명주소") != null) {
					addr =recordsArray.get(i).getAsJsonObject().get("소재지도로명주소").getAsString();
				}else {
					addr=recordsArray.get(i).getAsJsonObject().get("소재지지번주소").getAsString();
				}
				double latitude=recordsArray.get(i).getAsJsonObject().get("위도").getAsDouble();
				double longitude=recordsArray.get(i).getAsJsonObject().get("경도").getAsDouble();
				
				int bike_count=recordsArray.get(i).getAsJsonObject().get("자전거보유대수").getAsInt();
				
				BikeDto dto =new BikeDto(name,addr,latitude,longitude,bike_count);
				list.add(dto);
				
				Gson gson=new Gson();
				
				String jsonString = gson.toJson(dto);
				resultArray.add(JsonParser.parseString(jsonString));
				
			}
			if (dao.insert(list)) {
				System.out.println("db저장 성공");
			}else {
				System.out.println("db저장 실패");
			}
			JsonObject result=new JsonObject();
			result.add("result", resultArray);
			
			response.getWriter().append(result+"");
		}
	}

}
