package Adaptive;

import java.sql.*;
import java.util.Scanner;

public class DB {
  

	//Connection conn = getConnection();
	Connection con1;
	
	   public Connection getConnection(){

			try{	   
	      
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				 con1 = DriverManager.getConnection("jdbc:mysql://localhost/idbms", "root", "harshini");
				 //System.out.println("worked");
				 		}
			catch(Exception e){
				System.out.println(e);
			}
			return con1;
	}

	   public static void main (String args[]){
		
		DB o1 = new DB();
		Connection conn = o1.getConnection();
		
		try
		{
			Statement st = conn.createStatement();
						
			int a= 3;
			double score = 0;
			double prob = 0;
			int elapsedSeconds = 0;
		   
			for(int i=0; i<3;i++)
			{
			String query = "Select * from quiz where Difflev =' "+a+"' and counter = 0 ";
			ResultSet rs = st.executeQuery(query);
			long startTime = System.currentTimeMillis();
			//System.out.println("in");
			System.out.println(" Difficulty level ="+a);
			rs.next();
			String Ques = rs.getString(2);
			System.out.println(Ques);
			System.out.println(rs.getString(3));
			System.out.println(rs.getString(4));
						
			Scanner in = new Scanner(System.in);
			
			  System.out.println("Enter answer: a or b :");
		     String s = in.nextLine();
			 
		     long estimatedTime = System.currentTimeMillis() - startTime;
		     long elapsedTime = estimatedTime/1000;
		     System.out.println("ELapsed time= :"+elapsedTime);

		     if(s.equals(rs.getString(5))){
		    	 System.out.println(rs.getString(5));
		    	 a= a+1;
		    	 System.out.println("correct answer");
		    	
		    	 if(elapsedTime <= 20)
		    	 {
		    		 a= a+1;
		    		 System.out.println(a);
		    		 prob = ((0.5)*(0.333)/(0.5));
		    		  score = score + (prob*10*3);
		    		  System.out.println(score);
		    		 }
		    	 else{
		    		 a= a;
		    		 System.out.println(a);
		    		 prob = ((0.5)*(0.6666)/(0.5));
		    		 score = score + (prob*10*1);
		    		 System.out.println(score);
		    	 }
		     } 
		     else{
		    	 a=a-1;
		    		System.out.println("wrong answer");
		    		System.out.println(score);
		    		
		    	 }
		     String query2 = "Update quiz set counter = 1 where Question ='"+Ques+"'";
				PreparedStatement st2 = conn.prepareStatement(query2);
				st2.executeUpdate();
		     
		     }
			String query3 = "Update quiz set Counter = 0 where Counter =1";
			PreparedStatement st3 = conn.prepareStatement(query3);
			st3.executeUpdate();
		    int finalscore = (int) score; 
			System.out.println(" Score for the test is :" +finalscore);
		   				     
		}
		
		catch(Exception e){
			e.printStackTrace();
				}
			
	}
	

}



