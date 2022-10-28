package com.tom.ws.soap.auth;

import java.io.IOException;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;
import org.springframework.stereotype.Component;

import com.tom.ws.soap.DAO.UserDAO;
import com.tom.ws.soap.helper.SessionHelper;



@Component
public class UTPasswordCallback implements CallbackHandler {
	
	EntityManager entityManager = SessionHelper.getEntityManager();
	
	UserDAO userDAO = new UserDAO();
	
	Map<String, String> passwords = null;
	
	
	
	public UTPasswordCallback() {
		super();
		passwords = userDAO.getUsers();
	}
	

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

	

		for (Callback callback : callbacks) {

			WSPasswordCallback passwordCallback = (WSPasswordCallback) callback;

			String password = passwords.get(passwordCallback.getIdentifier());

			if (password != null) {
				passwordCallback.setPassword(password);
				return;
			}
		}

	}

}
