import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

package com.component.dao.impl;

public class GoogleDAOAuthImpl {


	String CLIENT_ID = "1013823904187-21ofld8vn99hljc0t7omqhtvrtd6lv9p.apps.googleusercontent.com";


	private Object jsonFactory;
	
	
	GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HttpTransport transport, JsonFactory jsonFactory)
	    // Specify the CLIENT_ID of the app that accesses the backend:
	    .setAudience(Collections.singletonList(CLIENT_ID))
	    .build();


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
	  String avatar = (String) payload.get("picture");
	  String locale = (String) payload.get("locale");
	  String familyName = (String) payload.get("family_name");
	  String givenName = (String) payload.get("given_name");

      // we will insert the data to the database

	} else {
	  System.out.println("Invalid ID token.");
	}
}

