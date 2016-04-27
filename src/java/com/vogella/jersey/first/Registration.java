/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vogella.jersey.first;

import dao.UserDAO;
import dto.UserDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author islam
 */
@Path("/Registration")
public class Registration {

    
    
    //http://localhost:8080/MedReminderServerSide/rest/Registration/signup
    @Path("signup")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String signUp(@FormParam("email") String email, @FormParam("password") String password) {

        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.insertUser(email, password);

        if (user == null)
            return "something is wrong";

        JSONObject json = createJsonObject(user);
        return json.toString();
    }

    @Path("/login/{email}/{password}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@PathParam("email") String email, @PathParam("password") String password) {

        UserDAO userDAO = new UserDAO();
        UserDTO user = userDAO.getUser(email, password);

        if (user == null) {
            return "wrong password or email";
        }

        JSONObject json = createJsonObject(user);
        

        return json.toString();
    }

    @Path("/birthdate/{id}/{birthdate}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String setBirthdate(@PathParam("id") int id, @PathParam("birthdate") long birthdate) {

        UserDAO userDAO = new UserDAO();
        return userDAO.updateBirthdate(id, birthdate);

    }

    @Path("/gender/{id}/{gender}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String setGender(@PathParam("id") int id, @PathParam("gender") String gender) {

        UserDAO userDAO = new UserDAO();
        return userDAO.updateGender(id, gender);

    }

    private JSONObject createJsonObject(UserDTO user) {

        JSONObject json = new JSONObject();
        
        try {
            json.put("email", user.getEmail());
            json.put("password", user.getPassword());
            json.put("gender", user.getGender());
            json.put("birthdate", user.getBirthdate());
            json.put("id", user.getId());
            
        } catch (JSONException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("json is: " + json.toString());
        return json;
    }

}
