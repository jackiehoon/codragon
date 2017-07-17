package WebSocketEx;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dto.MainDAO;


 
 
@ServerEndpoint("/broadsocket")
public class broadsocket {
    //유저 집합 리스트
	static HashMap<String, Integer> map = new HashMap<>();

    static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
     
    /**
     * 웹 소켓이 접속되면 유저리스트에 세션을 넣는다.
     * @param userSession 웹 소켓 세션
     */
    @OnOpen
    public void handleOpen(Session userSession){
        sessionUsers.add(userSession);
       
        
    }
    /**
     * 웹 소켓으로부터 메시지가 오면 호출한다.
     * @param message 메시지
     * @param userSession
     * @throws IOException
     * @throws ParseException 
     */
    @OnMessage
    public void handleMessage(String message,Session userSession) throws ParseException, IOException{

        
        
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj=(JSONObject) jsonParser.parse(message);
	
        
        String result ="default result";
        
        
        String messageType =jsonObj.get("type").toString();
        String party_id =jsonObj.get("party_id").toString();
       
        
        
        
        if(messageType.equals("enter")){
        	
        	String person_id =jsonObj.get("person_id").toString();
            userSession.getUserProperties().put("party_id", party_id);
            userSession.getUserProperties().put("person_id", person_id);
            int onlineNum =0;
            Iterator<Session> tempIterator = sessionUsers.iterator();
            while(tempIterator.hasNext()){
            	Session targetUser = tempIterator.next();
            	String targetUserPartyId = (String)targetUser.getUserProperties().get("party_id");
            	String targetUserPersonId = (String)targetUser.getUserProperties().get("person_id");
            	if(targetUserPartyId.equals(party_id)&&!targetUserPersonId.equals("0")){
            		onlineNum++;
            	}
            }
        	
            
        	result =Json.createObjectBuilder()
                    .add("type", "online")
                    .add("num", onlineNum)
                    .add("party_id", party_id)
                    .build()
                    .toString(); 
        			
        }else if(messageType.equals("call")){
        	MainDAO dao = new MainDAO();
        	Gson gson = new Gson();
        	ArrayList<String[]> list = dao.getCallPerson(party_id);
        	
        	
        	Type listType = new TypeToken<ArrayList<String[]>>(){}.getType();
    		
    		String result2 = gson.toJson(list, listType);
    		
        	result =Json.createObjectBuilder()
                    .add("type", "call")
                    .add("list", result2)
                    .add("party_id", party_id)
                    .build()
                    .toString(); 
        	
        }else if(messageType.equals("done")){
        	MainDAO dao = new MainDAO();
        	
        	int completeNum = dao.getCompleteNum(party_id);
        	
        	result =Json.createObjectBuilder()
                    .add("type", "done")
                    .add("num", completeNum)
                    .add("party_id", party_id)
                    .build()
                    .toString(); 
        	
        }
        

        Iterator<Session> iterator = sessionUsers.iterator();
        //username이 있으면 전체에게 메시지를 보낸다.
        while(iterator.hasNext()){
        	Session targetUser = iterator.next();
        	String targetUserPartyId = (String)targetUser.getUserProperties().get("party_id");
        	System.out.println(targetUserPartyId);
        	if(targetUserPartyId.equals(party_id)){        		
				targetUser.getBasicRemote().sendText(result);				
        	}
        }
    }
    /**
     * 웹소켓을 닫으면 해당 유저를 유저리스트에서 뺀다.
     * @param userSession
     */
    @OnClose
    public void handleClose(Session userSession){
    	

    	String party_id =(String)userSession.getUserProperties().get("party_id");
    	 int onlineNum =0;
    	 
    	Iterator<Session> iterator = sessionUsers.iterator();        
        while(iterator.hasNext()){
        	Session targetUser = iterator.next();
        	String targetUserPartyId = (String)targetUser.getUserProperties().get("party_id");
        	String targetUserPersonId = (String)targetUser.getUserProperties().get("person_id");
        	if(targetUserPartyId.equals(party_id)&&!targetUserPersonId.equals("0")){
        		onlineNum++;
        	}
        }
        
        String result =Json.createObjectBuilder()
                .add("type", "online")
                .add("num", onlineNum)
                .add("party_id", party_id)
                .build()
                .toString();
        
        while(iterator.hasNext()){
        	Session targetUser = iterator.next();
        	String targetUserPartyId = (String)targetUser.getUserProperties().get("party_id");
        	if(targetUserPartyId.equals(party_id)){
        		try {
					targetUser.getBasicRemote().sendText(result);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
    	
    	sessionUsers.remove(userSession);
    }
    /**
     * json타입의 메시지 만들기
     * @param username
     * @param message
     * @return
     */
    public String buildJsonData(String username,String message){
        JsonObject jsonObject = Json.createObjectBuilder().add("message", username+" : "+message).build();
        
        StringWriter stringwriter =  new StringWriter();
        try(JsonWriter jsonWriter = Json.createWriter(stringwriter)){
                jsonWriter.write(jsonObject);
        };
        return stringwriter.toString();
    	
    }
  
   

}

