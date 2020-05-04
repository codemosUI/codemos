package com.component.dao.impl;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import java.util.*;


public class GoogleDAOAuthImpl {
	  private HttpTransport transport;

	 private JsonFactory jsonFactory;
	 private String idTokenString;
    public void Signup() throws GeneralSecurityException {
		  String CLIENT_ID = "1013823904187-21ofld8vn99hljc0t7omqhtvrtd6lv9p.apps.googleusercontent.com";
	
			// will be received from FE Sign IN request
			
			
			
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				    // Specify the CLIENT_ID of the app that accesses the backend:
				    .setAudience(Collections.singletonList(CLIENT_ID))
				 
				    .build();
			try {
				GoogleIdToken idToken = verifier.verify(idTokenString); 
				if (idToken != null) {
					  Payload payload = idToken.getPayload();

					  // Print user identifier
					  String userId = payload.getSubject();
					  System.out.println("User ID: " + userId);

					  // Get profile information from payload
					  String email = payload.getEmail();
					  boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
					  String name = (String) payload.get("name");
					  String pictureUrl = (String) payload.get("picture");
					  String locale = (String) payload.get("locale");
					  String familyName = (String) payload.get("family_name");
					  String givenName = (String) payload.get("given_name");
					  System.out.println("locale: " + locale);
					  System.out.println("email: " + email);
					  System.out.println("pictureUrl: " + pictureUrl);
					  System.out.println("familyName: " + familyName);
					  System.out.println("givenName: " + givenName);
					  System.out.println("emailVerified: " + emailVerified);
					  System.out.println("name: " + name);

					} else {
					  System.out.println("Invalid ID token.");
					}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			  
	  }
}

