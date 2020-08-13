/**
 * 
 */
package com.onboard.sso.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.onboard.sso.model.ActiveSessionResponse;

/**
 * @author Anish Singh(anish2u2@gmail.com)
 *
 *         13-Aug-2020
 */
@Component
public class SessionUtils {

	private static final Map<String, Object> tokenMap = new ConcurrentHashMap<String, Object>();

	public boolean checkItsValid(String userName) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -5);
		return tokenMap.get(userName) != null
				? ((ActiveSessionResponse) tokenMap.get(userName)).getLastAccessTime().before(cal.getTime())
				: false;
	}

	public List<ActiveSessionResponse> getListOfActiveSession() {
		List<ActiveSessionResponse> list = new ArrayList<>();
		tokenMap.forEach((key, value) -> {

			list.add((ActiveSessionResponse) value);
		});
		return list;
	}

	public void putTokenInMap(String user, String token) {
		tokenMap.put(user, new ActiveSessionResponse(user, token, new Date()));
	}

	public Object getTokenAssociatedWithUser(String user) {
		return tokenMap.get(user) != null ? ((ActiveSessionResponse) tokenMap.get(user)).getToken() : null;
	}

	public void blackListUserToken(String user) {
		tokenMap.remove(user);
	}

}
